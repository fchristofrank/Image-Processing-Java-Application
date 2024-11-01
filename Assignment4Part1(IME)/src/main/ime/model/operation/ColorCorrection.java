package ime.model.operation;

import java.util.HashMap;
import java.util.Map;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.RGBPixel;

public class ColorCorrection implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    System.out.println("Reached Color Correction");

    Map<Integer, Integer> frequencyRed = new HashMap<>();
    Map<Integer, Integer> frequencyGreen = new HashMap<>();
    Map<Integer, Integer> frequencyBlue = new HashMap<>();

    calculateFrequencies(inputImage, frequencyRed, frequencyGreen, frequencyBlue);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    Image outputImage = new SimpleImage(height, width, inputImage.getType());

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i,j));
      }
    }

    correctColor(outputImage, frequencyRed, frequencyGreen, frequencyBlue);

    return outputImage;
  }

  private void calculateFrequencies(
          Image inputImage,
          Map<Integer, Integer> frequencyRed,
          Map<Integer, Integer> frequencyGreen,
          Map<Integer, Integer> frequencyBlue) {

    int imageHeight = inputImage.getHeight();
    int imageWidth = inputImage.getWidth();

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        int red = inputImage.getPixel(i, j).getRed();
        int green = inputImage.getPixel(i, j).getGreen();
        int blue = inputImage.getPixel(i, j).getBlue();

        frequencyRed.put(red, frequencyRed.getOrDefault(red, 0) + 1);
        frequencyGreen.put(green, frequencyGreen.getOrDefault(green, 0) + 1);
        frequencyBlue.put(blue, frequencyBlue.getOrDefault(blue, 0) + 1);
      }
    }
  }

  private void correctColor( Image image,
      Map<Integer, Integer> redFrequency,
      Map<Integer, Integer> greenFrequency,
      Map<Integer, Integer> blueFrequency) {

    // Step 1: Find peaks for each color channel
    int peakRed = findPeak(redFrequency);
    int peakGreen = findPeak(greenFrequency);
    int peakBlue = findPeak(blueFrequency);

    // Step 2: Compute the average peak position
    int avgPeak = (peakRed + peakGreen + peakBlue) / 3;

    // Step 3: Calculate offsets for each channel
    int offsetRed = avgPeak - peakRed;
    int offsetGreen = avgPeak - peakGreen;
    int offsetBlue = avgPeak - peakBlue;

    // Step 4: Apply the offsets to each pixel in the image
    for (int x = 0; x < image.getHeight(); x++) {
      for (int y = 0; y < image.getWidth(); y++) {

        System.out.println(x+","+y);
        System.out.println(image.getWidth());
        System.out.println(image.getHeight());
        System.out.println(image.getPixel(x,y));
        int red = image.getPixel(x, y).getRed() + offsetRed;
        int green = image.getPixel(x, y).getGreen() + offsetGreen;
        int blue = image.getPixel(x, y).getBlue() + offsetBlue;

        // Clamping values to stay within RGB range [0, 255]
        red = clamp(red);
        green = clamp(green);
        blue = clamp(blue);

        image.setPixel(x, y, new RGBPixel(red, green, blue));
      }
    }
  }

  private static int findPeak(Map<Integer, Integer> histogram) {
    int peak = 10; // Start checking from value 10
    int maxFrequency = 0;

    // Limit range from 10 to 245 as specified
    for (int i = 10; i <= 245; i++) {
      int frequency = histogram.getOrDefault(i, 0);
      if (frequency > maxFrequency) {
        maxFrequency = frequency;
        peak = i;
      }
    }

    return peak;
  }

  private static int clamp(int value) {
    if (value < 0) return 0;
    return Math.min(value, 255);
  }
}
