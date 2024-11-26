package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

public class ApplyDither implements ImageOperation {

  private final VisualizeRed greyScale;

  public ApplyDither(VisualizeRed greyScale) {
    this.greyScale = greyScale;
  }

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {

    Image greyScaleImage = greyScale.apply(inputImage, args);

    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage;
    int splitWidth = 100;
    if (args.length != 0) {
      if (args[1] != null && !args[1].isEmpty()) {
        splitWidth = (inputImage.getWidth() * Integer.parseInt(args[1])) / 100;
      }
    }

    float[][] errorArray = new float[height][splitWidth];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitWidth; j++) {
        errorArray[i][j] = greyScaleImage.getPixel(i, j).getRed();
      }
    }
    Pixel[][] outputPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        if (j < splitWidth) {
          float oldColor = errorArray[i][j];
          int newColor = (oldColor < 128) ? 0 : 255;
          float error = oldColor - newColor;
          outputPixels[i][j] = PixelFactory.createPixel(ImageType.RGB, newColor, newColor, newColor);
          if (j < splitWidth - 1)
            errorArray[i][j + 1] += error * 7 / 16.0f;
          if (i < height - 1 && j > 0)
            errorArray[i + 1][j - 1] += error * 3 / 16.0f;
          if (i < height - 1)
            errorArray[i + 1][j] += error * 5 / 16.0f;
          if (i < height - 1 && j < splitWidth - 1)
            errorArray[i + 1][j + 1] += error * 1 / 16.0f;
        } else {
          outputPixels[i][j] = inputImage.getPixel(i, j);
        }
      }
    }
    outputImage = new SimpleImage(height, width, ImageType.RGB, outputPixels);
    return outputImage;
  }
}
