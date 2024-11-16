package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * This class requires the width and the height of the resulting scaled image as args. If there is
 * no change in either of the width, it wants it to send the original width otherwise a
 * IllegalArgumentException will be raised.
 */
public class Downscale implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    int actualWidth = inputImage.getWidth();
    int actualHeight = inputImage.getHeight();

    int scaledWidth = Integer.parseInt(args[0]);
    int scaledHeight = Integer.parseInt(args[1]);

    System.out.println(scaledHeight);
    System.out.println(scaledWidth);

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

  private Pixel computeMValue(Pixel computedCb, Pixel computedCa, int x, int xLow, int xHigh) {

    int red = computedCb.getRed() * (x - xLow) + computedCa.getRed() * (xHigh - x);
    int green = computedCb.getGreen() * (x - xLow) + computedCa.getGreen() * (xHigh - x);
    int blue = computedCb.getBlue() * (x - xLow) + computedCa.getBlue() * (xHigh - x);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }

  private Pixel computeNValue(Pixel computedCd, Pixel computedCc, int x, int xLow, int xHigh) {

    int red = computedCd.getRed() * (x - xLow) + computedCc.getRed() * (xHigh - x);
    int green = computedCd.getGreen() * (x - xLow) + computedCc.getGreen() * (xHigh - x);
    int blue = computedCd.getBlue() * (x - xLow) + computedCc.getBlue() * (xHigh - x);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }

  private Pixel computeCpValue(Pixel m, Pixel n, int y, int yLow, int yHigh) {

    int red = n.getRed() * (y - yLow) + m.getRed() * (yHigh - y);
    int green = n.getGreen() * (y - yLow) + m.getGreen() * (yHigh - y);
    int blue = n.getBlue() * (y - yLow) + m.getBlue() * (yHigh - y);

    System.out.printf("%d %d %d%n", red, green, blue);
    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }
}