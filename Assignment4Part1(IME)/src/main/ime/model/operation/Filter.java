package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;
import ime.model.image.SimpleImage;

import static ime.constants.FilterConstants.PIXEL_LOWER_LIMIT;
import static ime.constants.FilterConstants.PIXEL_UPPER_LIMIT;

public abstract class Filter implements ImageOperation {

  protected abstract float[][] getKernel();

  public Image apply(Image inputImage, String[] args) throws IllegalArgumentException {

    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB);
    System.out.println(inputImage.getHeight());
    System.out.println(inputImage.getWidth());

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        Pixel newPixel = applyFilterToPixel(inputImage, i, j);
        outputImage.setPixel(i, j, newPixel);
      }
    }

    return outputImage;
  }

  private Pixel applyFilterToPixel(Image image, int x, int y) {

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    float[][] blurKernel = getKernel();
    int kernelSize = blurKernel.length;
    int kernelOffset = kernelSize / 2;

    for (int i = -kernelOffset; i <= kernelOffset; i++) {
      for (int j = -kernelOffset; j <= kernelOffset; j++) {
        if (isOutOfBounds(image, x + i, y + j)) {
          continue;
        }
        Pixel pixel = image.getPixel(x+i,y+j);
        redSum += pixel.getRed() * blurKernel[i + kernelOffset][j + kernelOffset];
        greenSum += pixel.getGreen() * blurKernel[i + kernelOffset][j + kernelOffset];
        blueSum += pixel.getBlue() * blurKernel[i + kernelOffset][j + kernelOffset];
      }
    }

    return createClampedPixel((int) redSum, (int) greenSum, (int) blueSum);
  }

  private boolean isOutOfBounds(Image image, int currentX, int currentY) {
    return currentX < 0 || currentX >= image.getHeight() || currentY < 0 || currentY >= image.getWidth();
  }

  private Pixel createClampedPixel(int red, int green, int blue) {
    int clampedRed = clamp(red);
    int clampedGreen = clamp(green);
    int clampedBlue = clamp(blue);
    return PixelFactory.createPixel(ImageType.RGB, clampedRed, clampedGreen, clampedBlue);
  }

  private int clamp(int value) {
    return Math.max(PIXEL_LOWER_LIMIT, Math.min(PIXEL_UPPER_LIMIT, value));
  }
}