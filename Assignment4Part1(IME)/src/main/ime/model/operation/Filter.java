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

  protected abstract int getStartIndexForRow();

  protected abstract int getLastIndexForRow(int width);

  protected abstract int getStartIndexForColumn();

  protected abstract int getLastIndexForColumn(int height);

  public Image apply(Image inputImage, String[] args) throws IllegalArgumentException {

    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB);
    System.out.println(inputImage.getWidth());
    System.out.println(inputImage.getHeight());

    int startRow = getStartIndexForRow();
    int endRow = getLastIndexForRow(inputImage.getWidth());
    int startColumn = getStartIndexForColumn();
    int endColumn = getLastIndexForColumn(inputImage.getHeight());

    for (int i = startColumn; i < endColumn; i++) {
      for (int j = startRow; j < endRow; j++) {
        Pixel newPixel = applyFilterToPixel(inputImage, i, j);
        outputImage.setPixel(i, j, newPixel);
      }
    }

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        if (i < startColumn || i >= endColumn || j < startRow || j >= endRow) {
          Pixel borderPixel = inputImage.getPixel(i, j);
          outputImage.setPixel(i, j, borderPixel);
        }
      }
    }

    return outputImage;
  }

  private Pixel applyFilterToPixel(Image image, int x, int y) {

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    float[][] blurKernel = getKernel();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        Pixel newPixel = image.getPixel(x + i, y + j);
        redSum += newPixel.getRed() * blurKernel[i + 1][j + 1];
        greenSum += newPixel.getGreen() * blurKernel[i + 1][j + 1];
        blueSum += newPixel.getBlue() * blurKernel[i + 1][j + 1];
      }
    }
    int newRed = clamp((int) redSum);
    int newGreen = clamp((int) greenSum);
    int newBlue = clamp((int) blueSum);

    return PixelFactory.createPixel(ImageType.RGB, newRed, newGreen, newBlue);
  }

  private int clamp(int value) {
    return Math.max(PIXEL_LOWER_LIMIT, Math.min(PIXEL_UPPER_LIMIT, value));
  }

}