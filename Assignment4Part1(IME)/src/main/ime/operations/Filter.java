package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

import static ime.constants.Constants.GAUSSIAN_BLUR_KERNEL;

public class Filter extends ImageOperationManager {

  private final float[][] blurKernel;

  public Filter(ImageLibrary library) {
    super(library);
    this.blurKernel = GAUSSIAN_BLUR_KERNEL;
  }

  public void apply(String[] args) throws IllegalArgumentException {

    validateArgs(args);
    String inputImageName = args[0];
    String outputImageName = args[1];

    Image inputImage = getImage(inputImageName);
    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB);

    for (int i = 1; i < inputImage.getHeight() - 1; i++) {
      for (int j = 1; j < inputImage.getWidth() - 1; j++) {
        Pixel newPixel = applyFilterToPixel(inputImage, i, j);
        outputImage.setPixel(i, j, newPixel);
      }
    }

    addImage(outputImageName, outputImage);
  }

  private Pixel applyFilterToPixel(Image image, int x, int y) {
    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        Pixel neighborPixel = image.getPixel(x + i, y + j);
        double weight = blurKernel[i + 1][j + 1];

        redSum += neighborPixel.getRed() * weight;
        greenSum += neighborPixel.getGreen() * weight;
        blueSum += neighborPixel.getBlue() * weight;
      }
    }

    int newRed = clamp((int) redSum, 0, 255);
    int newGreen = clamp((int) greenSum, 0, 255);
    int newBlue = clamp((int) blueSum, 0, 255);

    return PixelFactory.createPixel(ImageType.RGB, newRed, newGreen, newBlue);
  }

  private int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}
