package ime.model.operation;

import java.util.HashMap;
import java.util.Map;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.RGBPixel;

public class ColorCorrection implements ImageOperation {

  private final CountFrequency countFrequencyOperation;

  public ColorCorrection(CountFrequency countFrequency) {
    this.countFrequencyOperation = countFrequency;
  }

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    System.out.println("Reached Color Correction");

    Map<String,Map<Integer, Integer>> frequency;

    frequency = countFrequencyOperation.calculateFrequencies(inputImage);

    if (!frequency.containsKey("red") || frequency.get("red").isEmpty() ||
        !frequency.containsKey("green") || frequency.get("green").isEmpty() ||
        !frequency.containsKey("blue") || frequency.get("blue").isEmpty()) {
      throw new IllegalStateException("Frequency maps could not be calculated.");
    }

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    Image outputImage = new SimpleImage(height, width, inputImage.getType());

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i,j));
      }
    }

    correctColor(outputImage, frequency.get("red"), frequency.get("green"), frequency.get("blue"));

    return outputImage;
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

        image.setPixel(x, y, new RGBPixel(red, green, blue));
      }
    }
  }

  private static int findPeak(Map<Integer, Integer> histogram) {
    int peak = 10;
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

}
