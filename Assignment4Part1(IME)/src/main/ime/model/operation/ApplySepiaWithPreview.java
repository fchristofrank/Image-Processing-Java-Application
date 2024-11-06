package ime.model.operation;

import ime.constants.FilterConstants;
import ime.model.image.Image;
import ime.model.pixel.Pixel;

public class ApplySepiaWithPreview extends ApplySepia {

  @Override
  protected void processImage(Image inputImage, Pixel[][] pixels,
                              String... args) {
    int widthSplitPercentage = 100;
    int heightSplitPercentage = 100;
    if (args.length != 0) {
      if (args[0] != null && !args[0].isEmpty()) {
        widthSplitPercentage = Integer.parseInt(args[0]);
      }
      if (args.length > 1 && args[1] != null && !args[1].isEmpty()) {
        heightSplitPercentage = Integer.parseInt(args[1]);
      }
    }
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    int splitWidth = (inputImage.getWidth() * widthSplitPercentage) / 100;
    int splitHeight = (inputImage.getHeight() * (heightSplitPercentage) / 100);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (i < splitHeight && j < splitWidth) {
          pixels[i][j] = inputImage.getPixel(i, j)
                  .scaleComponents(FilterConstants.SEPIA_COLOR_TRANSFORMATION);
        } else {
          pixels[i][j] = inputImage.getPixel(i, j);
        }
      }
    }
  }
}
