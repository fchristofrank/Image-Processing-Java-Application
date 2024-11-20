package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;
import ime.model.pixel.RGBPixel;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class contains the operation to generate the histogram of the given image but calculating
 * the frequency of the red, green and blue channels. It uses the Bresenham's algorithm to generate
 * the lines for the histogram.
 */
public class Histogram implements ImageOperation {

  private static final int PIXEL_UPPER_LIMIT = 255;
  private final CommonOperation countFrequencyOperation;

  /**
   * Injects the dependencies in the constructor to be able to use the count frequency.
   *
   * @param countFrequencyOperation the object containing the count method.
   */
  public Histogram(CommonOperation countFrequencyOperation) {
    this.countFrequencyOperation = countFrequencyOperation;
  }

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    Map<String, Map<Integer, Integer>> frequency;

    frequency = countFrequencyOperation.calculateFrequencies(inputImage);

    if (!frequency.containsKey("red")
        || frequency.get("red").isEmpty()
        || !frequency.containsKey("green")
        || frequency.get("green").isEmpty()
        || !frequency.containsKey("blue")
        || frequency.get("blue").isEmpty()) {
      throw new IllegalStateException("Frequency maps could not be calculated.");
    }

    Pixel[][] pixels = new Pixel[256][256];

    createHistogramImage(
        pixels, frequency.get("red"), frequency.get("green"), frequency.get("blue"));

    return new SimpleImage(256, 256, inputImage.getType(), pixels);
  }

  private void createHistogramImage(
      Pixel[][] outputPixels,
      Map<Integer, Integer> frequencyRed,
      Map<Integer, Integer> frequencyGreen,
      Map<Integer, Integer> frequencyBlue) {

    int histogramWidth = 256;
    int histogramHeight = 256;

    fillBackground(outputPixels, histogramHeight, histogramWidth);

    drawGridLines(outputPixels, histogramWidth, histogramHeight);

    drawFrequencyCurves(outputPixels, frequencyRed, frequencyGreen, frequencyBlue);
  }

  private void fillBackground(Pixel[][] pixels, int height, int width) {
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        pixels[x][y] = PixelFactory.createPixel(ImageType.RGB, 255, 255, 255); // WhiteBG
      }
    }
  }

  private void drawGridLines(Pixel[][] pixels, int width, int height) {
    for (int j = 0; j < width; j += 16) {
      drawLine(pixels, 0, j, width - 1, j, new RGBPixel(200, 200, 200));
    }

    for (int i = 0; i < height; i += 16) {
      drawLine(pixels, i, 0, i, height - 1, new RGBPixel(200, 200, 200));
    }
  }

  private int findMaxFrequency(List<Map<Integer, Integer>> frequencyMaps) {
    int maxFrequency = 0;
    for (Map<Integer, Integer> frequencyMap : frequencyMaps) {
      maxFrequency =
          Math.max(maxFrequency, frequencyMap.values().stream().max(Integer::compare).orElse(1));
    }
    return maxFrequency;
  }

  private void drawFrequencyCurves(
      Pixel[][] pixels,
      Map<Integer, Integer> frequencyRed,
      Map<Integer, Integer> frequencyGreen,
      Map<Integer, Integer> frequencyBlue) {

    int maxFrequency = findMaxFrequency(Arrays.asList(frequencyRed, frequencyGreen, frequencyBlue));

    for (int i = 0; i <= 255; i++) {
      drawCurveSegment(pixels, i, frequencyRed, maxFrequency, new RGBPixel(255, 0, 0));
      drawCurveSegment(pixels, i, frequencyGreen, maxFrequency, new RGBPixel(0, 255, 0));
      drawCurveSegment(pixels, i, frequencyBlue, maxFrequency, new RGBPixel(0, 0, 255));
    }
  }

  private void drawCurveSegment(
      Pixel[][] pixels,
      int pixelValue,
      Map<Integer, Integer> frequencyMap,
      int maxFrequency,
      Pixel pixel) {

    int currentFrequency =
        pixels.length
            - (int)
            ((double) frequencyMap.getOrDefault(pixelValue, 0) / maxFrequency * pixels.length);
    int nextFrequency =
        pixels.length
            - (int)
            ((double) frequencyMap.getOrDefault(pixelValue + 1, 0)
                / maxFrequency
                * pixels.length);

    if (pixelValue < PIXEL_UPPER_LIMIT
        && currentFrequency >= 0
        && currentFrequency < pixels[0].length
        && nextFrequency >= 0
        && nextFrequency < pixels.length) {

      drawLine(pixels, pixelValue, currentFrequency, pixelValue + 1, nextFrequency, pixel);
    }
  }

  /**
   * Implements the Bresenham's algorithm as described here. <a
   * href="https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm">Link to Wiki</a> Variable
   * naming are done as per the common convention used in the algorithm derivation.
   */
  private void drawLine(Pixel[][] pixels, int x1, int y1, int x2, int y2, Pixel pixel) {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int computedD = dx - dy;

    while (true) {

      if (x1 >= 0 && x1 < pixels[0].length && y1 >= 0 && y1 < pixels.length) {
        pixels[y1][x1] = pixel;
      }

      if (x1 == x2 && y1 == y2) {
        break;
      }

      int updatedD = 2 * computedD;
      if (updatedD > -dy) {
        /*This Condition would mean that the difference in Y direction is minimal
         * and that we should move towards X.
         * updatedD is proportional to D which is in turn proportional to dx.
         *  */
        computedD -= dy;
        x1 += sx;
      }
      if (updatedD < dx) {
        computedD += dx;
        y1 += sy;
      }
    }
  }
}
