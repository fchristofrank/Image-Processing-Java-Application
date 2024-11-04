package ime.model.operation;

import ime.constants.FilterConstants;
import ime.model.image.Image;
import ime.model.image.SimpleImage;

public class ApplySepiaWithPreview extends ApplySepia {
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();

    Image outputImage = new SimpleImage(height, width, inputImage.getType());
    processImage(inputImage, outputImage, args);
    return outputImage;
  }

  @Override
  protected void processImage(Image inputImage, Image outputImage, String... args) {
    int splitPercentage = 100;
    if (args.length != 0) {
      splitPercentage = Integer.parseInt(args[0]);
    }
    //split width calculated based on the split percentage.
    int splitWidth = inputImage.getWidth() * splitPercentage / 100;
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        if (j < splitWidth) {
          outputImage.setPixel(i, j, inputImage.getPixel(i, j)
                  .scaleComponents(FilterConstants.SEPIA_COLOR_TRANSFORMATION));
        } else {
          outputImage.setPixel(i, j, inputImage.getPixel(i, j));
        }
      }
    }
  }
}
