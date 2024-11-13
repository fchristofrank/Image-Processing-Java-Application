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

    int scaledWidth = Integer.parseInt(args[2]);
    int scaledHeight = Integer.parseInt(args[3]);

    Pixel[][] pixels = new Pixel[scaledHeight][scaledWidth];
    for (int x = 0; x < actualWidth; x++) {
      for (int y = 0; y < actualHeight; y++) {

        int lowerXCoordinate = (int) Math.floor(((double) x / actualWidth) * scaledWidth);
        int higherXCoordinate = (int) Math.ceil(((double) x / actualWidth) * scaledWidth);
        int lowerYCoordinate = (int) Math.floor(((double) y / actualHeight) * scaledHeight);
        int higherYCoordinate = (int) Math.ceil(((double) x / actualHeight) * scaledHeight);

        pixels[y][x] = computeAverage(x, y, lowerXCoordinate, higherXCoordinate, lowerYCoordinate,
            higherYCoordinate, inputImage);

      }
    }
    return new SimpleImage(scaledHeight, scaledWidth, inputImage.getType(), pixels);
  }

  private Pixel computeAverage(int x, int y, int xLow, int xHigh, int yLow, int yHigh,
      Image image) {

    Pixel computedCaPixel = image.getPixel(xLow, yLow);
    Pixel computedCbPixel = image.getPixel(xLow, yLow);
    Pixel computedCcPixel = image.getPixel(xHigh, yHigh);
    Pixel computedCdPixel = image.getPixel(xLow, yHigh);

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

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }
}

/*public class Downscale implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int actualWidth = inputImage.getWidth();
    int actualHeight = inputImage.getHeight();
    int scaledWidth = Integer.parseInt(args[0]);
    int scaledHeight = Integer.parseInt(args[1]);

    Pixel[][] pixels = new Pixel[scaledHeight][scaledWidth];

    for (int x = 0; x < actualWidth; x++) {
      for (int y = 0; y < actualHeight; y++) {
        int lowerX = (int) Math.floor((double) x / actualWidth * scaledWidth);
        int higherX = (int) Math.ceil((double) x / actualWidth * scaledWidth);
        int lowerY = (int) Math.floor((double) y / actualHeight * scaledHeight);
        int higherY = (int) Math.ceil((double) y / actualHeight * scaledHeight);

        pixels[y][x] = computeInterpolatedPixel(x, y, lowerX, higherX, lowerY, higherY, inputImage);
      }
    }
    return new SimpleImage(scaledHeight, scaledWidth, inputImage.getType(), pixels);
  }

  private Pixel computeInterpolatedPixel(int x, int y, int xLow, int xHigh, int yLow, int yHigh,
      Image image) {
    Pixel ca = image.getPixel(xLow, yLow);
    Pixel cb = image.getPixel(xHigh, yLow);
    Pixel cc = image.getPixel(xLow, yHigh);
    Pixel cd = image.getPixel(xHigh, yHigh);

    Pixel m = interpolate(ca, cb, x, xLow, xHigh);
    Pixel n = interpolate(cc, cd, x, xLow, xHigh);
    return interpolate(m, n, y, yLow, yHigh);
  }

  private Pixel interpolate(Pixel p1, Pixel p2, int pos, int low, int high) {
    int weight1 = high - pos;
    int weight2 = pos - low;

    int red = (p1.getRed() * weight1 + p2.getRed() * weight2) / (high - low);
    int green = (p1.getGreen() * weight1 + p2.getGreen() * weight2) / (high - low);
    int blue = (p1.getBlue() * weight1 + p2.getBlue() * weight2) / (high - low);

    return PixelFactory.createPixel(ImageType.RGB, red, green, blue);
  }
}

 */
