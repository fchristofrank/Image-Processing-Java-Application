package ime.model.operation;

import java.util.HashMap;
import java.util.Map;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.RGBPixel;

public class Histogram implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    System.out.println("Reached Histogram");

    Map<Integer, Integer> frequencyRed = new HashMap<>();
    Map<Integer, Integer> frequencyGreen = new HashMap<>();
    Map<Integer, Integer> frequencyBlue = new HashMap<>();

    calculateFrequencies(inputImage, frequencyRed, frequencyGreen, frequencyBlue);

    int histogramWidth = 256;
    int histogramHeight = 256;
    Image outputImage = new SimpleImage(histogramHeight, histogramWidth, ImageType.RGB);

    createHistogramImage(outputImage, frequencyRed, frequencyGreen, frequencyBlue);

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

  private void createHistogramImage(
      Image outputImage,
      Map<Integer, Integer> frequencyRed,
      Map<Integer, Integer> frequencyGreen,
      Map<Integer, Integer> frequencyBlue) {

    int histogramWidth = 256;
    int histogramHeight = 256;

    // Fill background with white
    fillBackground(outputImage, histogramHeight, histogramWidth);
    System.out.println("Background filled");

    // Draw grid lines
    drawGridLines(outputImage, histogramWidth, histogramHeight);
    System.out.println("Grid lines drawn");

    // Draw frequency curves
    drawFrequencyCurves(outputImage, frequencyRed, frequencyGreen, frequencyBlue, histogramHeight);
    System.out.println("Frequency curves drawn");
  }

  private void fillBackground(Image histogramImage, int height, int width) {
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        histogramImage.setPixel(x, y, new RGBPixel(255, 255, 255)); // White color
      }
    }
  }

  private void drawGridLines(Image histogramImage, int width, int height) {
    for (int j = 0; j < width; j += 16) {
      drawLine(histogramImage, 0, j, width - 1, j, 200, 200, 200); // Horizontal gray lines
    }

    for (int i = 0; i < height; i += 16) {
      drawLine(histogramImage, i, 0, i, height - 1, 200, 200, 200); // Vertical gray lines
    }
  }

  private int findMaxFrequency(Map<Integer, Integer>... frequencyMaps) {
    int maxFrequency = 0;
    for (Map<Integer, Integer> frequencyMap : frequencyMaps) {
      maxFrequency =
          Math.max(maxFrequency, frequencyMap.values().stream().max(Integer::compare).orElse(1));
    }
    return maxFrequency;
  }

  private void drawFrequencyCurves(
          Image histogramImage,
          Map<Integer, Integer> frequencyRed,
          Map<Integer, Integer> frequencyGreen,
          Map<Integer, Integer> frequencyBlue,
          int histogramWidth) {

    int maxFrequency = findMaxFrequency(frequencyRed, frequencyGreen, frequencyBlue);

    for (int i = 0; i < 255; i++) {
      drawCurveSegment(
              histogramImage, i, frequencyRed, maxFrequency, histogramWidth, 255, 0, 0); // Red curve
      drawCurveSegment(
              histogramImage, i, frequencyGreen, maxFrequency, histogramWidth, 0, 255, 0); // Green curve
      drawCurveSegment(
              histogramImage, i, frequencyBlue, maxFrequency, histogramWidth, 0, 0, 255); // Blue curve
    }
  }

  private void drawCurveSegment(
          Image histogramImage,
          int i,
          Map<Integer, Integer> frequencyMap,
          int maxFrequency,
          int histogramWidth,
          int red,
          int green,
          int blue) {

    // Calculate X coordinates based on the frequency value, normalized by max frequency
    int currentX = (int) ((double) frequencyMap.getOrDefault(i, 0) / maxFrequency * histogramWidth);
    int nextX = (int) ((double) frequencyMap.getOrDefault(i + 1, 0) / maxFrequency * histogramWidth);

    // Flip Y coordinates to make the histogram grow upwards
    int yCoord = i;

    // Draw line segment with corrected orientation
    if (yCoord >= 0
            && yCoord < histogramImage.getHeight()
            && currentX >= 0
            && currentX < histogramWidth
            && nextX >= 0
            && nextX < histogramWidth) {
      drawLine(histogramImage, currentX, yCoord, nextX, yCoord - 1, red, green, blue);
    }
  }
  // Helper method to draw a line between two points with specified RGB color
  private void drawLine(Image image, int x1, int y1, int x2, int y2, int red, int green, int blue) {
    if (x1 < 0
        || x1 >= image.getWidth()
        || y1 < 0
        || y1 >= image.getHeight()
        || x2 < 0
        || x2 >= image.getWidth()
        || y2 < 0
        || y2 >= image.getHeight()) {
      return;
    }

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int err = dx - dy;

    while (true) {
      image.setPixel(x1, y1, new RGBPixel(red, green, blue));

      if (x1 == x2 && y1 == y2) break;
      int e2 = 2 * err;
      if (e2 > -dy) {
        err -= dy;
        x1 += sx;
      }
      if (e2 < dx) {
        err += dx;
        y1 += sy;
      }
    }
  }
}
