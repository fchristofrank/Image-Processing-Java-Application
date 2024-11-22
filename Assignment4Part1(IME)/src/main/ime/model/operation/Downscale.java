package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * This class is responsible for downscaling an image to a smaller size based on the provided
 * dimensions. The resulting image will have the specified width and height, while maintaining
 * proportional mapping of pixels.
 */
public class Downscale implements ImageOperation {

  /**
   * Applies the downscaling operation to the given image using the specified dimensions.
   *
   * @param inputImage the original image to be downscaled.
   * @param args the target dimensions: the first argument is the width, and second is the height.
   * @return a new Image with the downscaled dimensions.
   * @throws IllegalArgumentException if the target dimensions are invalid.
   */
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int actualWidth = inputImage.getWidth();
    int actualHeight = inputImage.getHeight();

    int scaledWidth = Integer.parseInt(args[0]);
    int scaledHeight = Integer.parseInt(args[1]);

    if (scaledWidth <= 0 || scaledHeight <= 0 || scaledWidth > actualWidth
        || scaledHeight > actualHeight) {
      throw new IllegalArgumentException(
          "Invalid dimensions for downscaling. Provided dimensions: " + scaledWidth + "x"
              + scaledHeight
              + ", Original dimensions: " + actualWidth + "x" + actualHeight);
    }

    Pixel[][] pixels = new Pixel[scaledHeight][scaledWidth];

    for (int x = 0; x < scaledWidth; x++) {
      for (int y = 0; y < scaledHeight; y++) {
        double originalX = ((double) x / scaledWidth) * actualWidth;
        double originalY = ((double) y / scaledHeight) * actualHeight;

        int lowerX = (int) Math.floor(originalX);
        int upperX = adjustCoordinate(lowerX, (int) Math.ceil(originalX), actualWidth);

        int lowerY = (int) Math.floor(originalY);
        int upperY = adjustCoordinate(lowerY, (int) Math.ceil(originalY), actualHeight);

        pixels[y][x] = computeAverage(originalX, originalY, lowerX, upperX, lowerY, upperY,
            inputImage);
      }
    }

    return new SimpleImage(scaledHeight, scaledWidth, inputImage.getType(), pixels);
  }

  /**
   * Computes the average pixel value for a mapped location by interpolating surrounding pixels.
   *
   * @param originalX the mapped x-coordinate in the original image.
   * @param originalY the mapped y-coordinate in the original image.
   * @param xLow the lower x-coordinate boundary.
   * @param xHigh the upper x-coordinate boundary.
   * @param yLow the lower y-coordinate boundary.
   * @param yHigh the upper y-coordinate boundary.
   * @param image the original image.
   * @return the interpolated pixel for the location.
   */
  private Pixel computeAverage(
      double originalX, double originalY, int xLow, int xHigh, int yLow, int yHigh, Image image) {

    Pixel ca = image.getPixel(yLow, xLow);
    Pixel cb = image.getPixel(yLow, xHigh);
    Pixel cc = image.getPixel(yHigh, xLow);
    Pixel cd = image.getPixel(yHigh, xHigh);

    double xWeightLow = xHigh - originalX;
    double xWeightHigh = originalX - xLow;

    Pixel m = interpolatePixels(ca, cb, xWeightLow, xWeightHigh);
    Pixel n = interpolatePixels(cc, cd, xWeightLow, xWeightHigh);

    double yWeightLow = yHigh - originalY;
    double yWeightHigh = originalY - yLow;

    return interpolatePixels(m, n, yWeightLow, yWeightHigh);
  }

  /**
   * Interpolates two pixels based on their respective weights.
   *
   * @param p1 the first pixel.
   * @param p2 the second pixel.
   * @param weight1 the weight for the first pixel.
   * @param weight2 the weight for the second pixel.
   * @return the resulting after interpolation.
   */
  private Pixel interpolatePixels(Pixel p1, Pixel p2, double weight1, double weight2) {
    int red = (int) Math.round(p1.getRed() * weight1 + p2.getRed() * weight2);
    int green = (int) Math.round(p1.getGreen() * weight1 + p2.getGreen() * weight2);
    int blue = (int) Math.round(p1.getBlue() * weight1 + p2.getBlue() * weight2);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }

  /**
   * Adjusts the coordinate boundary to ensure it stays within the valid range.
   * If the lower and upper bounds are the same and within range, the upper bound is incremented.
   *
   * @param low the lower boundary coordinate.
   * @param high the upper boundary coordinate.
   * @param maxBound the maximum allowed value for the coordinate.
   * @return the adjusted upper boundary coordinate.
   */
  private int adjustCoordinate(int low, int high, int maxBound) {
    if (low == high && high < maxBound - 1) {
      high++;
    }
    return Math.min(high, maxBound - 1);
  }
}