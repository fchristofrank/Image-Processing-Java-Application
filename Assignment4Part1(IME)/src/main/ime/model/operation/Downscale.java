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
   * Applies the downscaling operation to the given image.
   *
   * @param inputImage The image to be downscaled.
   * @param args A variable-length argument specifying the scaled width and height.
   * @return A new image that is the downscaled version of the input image.
   * @throws IllegalArgumentException if the provided scaled dimensions are invalid.
   */
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    int actualWidth = inputImage.getWidth();
    int actualHeight = inputImage.getHeight();

    int scaledWidth = Integer.parseInt(args[0]);
    int scaledHeight = Integer.parseInt(args[1]);

    if (scaledHeight <= 0
        || scaledWidth <= 0
        || scaledHeight >= actualHeight
        || scaledWidth >= actualWidth) {
      throw new IllegalArgumentException("Not allowed values for scaled width or height");
    }

    Pixel[][] pixels = new Pixel[scaledHeight][scaledWidth];
    for (int x = 0; x < scaledWidth; x++) {
      for (int y = 0; y < scaledHeight; y++) {

        int lowerXCoordinate = (int) (Math.floor(((double) x / scaledWidth) * actualWidth));
        int higherXCoordinate = (int) Math.ceil(((double) x / scaledWidth) * actualWidth);
        int lowerYCoordinate = (int) Math.floor(((double) y / scaledHeight) * actualHeight);
        int higherYCoordinate = (int) Math.ceil(((double) y / scaledHeight) * actualHeight);

        pixels[y][x] =
            computeAverage(
                x,
                y,
                lowerXCoordinate,
                higherXCoordinate,
                lowerYCoordinate,
                higherYCoordinate,
                inputImage);
      }
    }
    return new SimpleImage(scaledHeight, scaledWidth, inputImage.getType(), pixels);
  }

  /**
   * Computes the average color of the pixel at the specified coordinates by interpolating
   * surrounding pixels.
   *
   * @param x The x-coordinate in the scaled image.
   * @param y The y-coordinate in the scaled image.
   * @param xLow The lower x-coordinate in the original image.
   * @param xHigh The higher x-coordinate in the original image.
   * @param yLow The lower y-coordinate in the original image.
   * @param yHigh The higher y-coordinate in the original image.
   * @param image The original image.
   * @return The computed pixel value for the scaled image.
   */
  private Pixel computeAverage(
      int x, int y, int xLow, int xHigh, int yLow, int yHigh, Image image) {

    Pixel computedCaPixel = image.getPixel(yLow, xLow);
    Pixel computedCbPixel = image.getPixel(yLow, xHigh);
    Pixel computedCcPixel = image.getPixel(yHigh, xLow);
    Pixel computedCdPixel = image.getPixel(yHigh, xHigh);
    Pixel computedMValue = computeMValue(computedCbPixel, computedCaPixel, x, xLow, xHigh);
    Pixel computedNValue = computeNValue(computedCdPixel, computedCcPixel, x, xLow, xHigh);
    return computeCpValue(computedMValue, computedNValue, y, yLow, yHigh);
  }

  /**
   * Computes the interpolated color value along the x-axis.
   *
   * @param computedCb The pixel at the higher x-coordinate.
   * @param computedCa The pixel at the lower x-coordinate.
   * @param x The x-coordinate in the scaled image.
   * @param xLow The lower x-coordinate in the original image.
   * @param xHigh The higher x-coordinate in the original image.
   * @return The computed pixel value along the x-axis.
   */
  private Pixel computeMValue(Pixel computedCb, Pixel computedCa, int x, int xLow, int xHigh) {

    int red = computedCb.getRed() * (x - xLow) + computedCa.getRed() * (xHigh - x);
    int green = computedCb.getGreen() * (x - xLow) + computedCa.getGreen() * (xHigh - x);
    int blue = computedCb.getBlue() * (x - xLow) + computedCa.getBlue() * (xHigh - x);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }

  /**
   * Computes the interpolated color value along the x-axis for another set of pixels.
   *
   * @param computedCd The pixel at the higher x-coordinate.
   * @param computedCc The pixel at the lower x-coordinate.
   * @param x The x-coordinate in the scaled image.
   * @param xLow The lower x-coordinate in the original image.
   * @param xHigh The higher x-coordinate in the original image.
   * @return The computed pixel value along the x-axis.
   */
  private Pixel computeNValue(Pixel computedCd, Pixel computedCc, int x, int xLow, int xHigh) {

    int red = computedCd.getRed() * (x - xLow) + computedCc.getRed() * (xHigh - x);
    int green = computedCd.getGreen() * (x - xLow) + computedCc.getGreen() * (xHigh - x);
    int blue = computedCd.getBlue() * (x - xLow) + computedCc.getBlue() * (xHigh - x);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }

  /**
   * Computes the final interpolated color value along the y-axis.
   *
   * @param m The interpolated pixel value along the x-axis (lower).
   * @param n The interpolated pixel value along the x-axis (higher).
   * @param y The y-coordinate in the scaled image.
   * @param yLow The lower y-coordinate in the original image.
   * @param yHigh The higher y-coordinate in the original image.
   * @return The computed pixel value along the y-axis.
   */
  private Pixel computeCpValue(Pixel m, Pixel n, int y, int yLow, int yHigh) {

    int red = n.getRed() * (y - yLow) + m.getRed() * (yHigh - y);
    int green = n.getGreen() * (y - yLow) + m.getGreen() * (yHigh - y);
    int blue = n.getBlue() * (y - yLow) + m.getBlue() * (yHigh - y);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }
}