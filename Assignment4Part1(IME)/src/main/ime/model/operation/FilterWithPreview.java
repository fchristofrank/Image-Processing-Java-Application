package ime.model.operation;

import ime.model.image.Image;
import ime.model.pixel.Pixel;

public abstract class FilterWithPreview extends Filter{

  @Override
  protected void processImage(Image inputImage, Image outputImage, String... args) {
    int widthSplitPercentage = 100;
    int heightSplitPercentage = 100;
    if (args.length != 0) {
      if (args[0] != null && args[0].isEmpty()) {
        widthSplitPercentage = Integer.parseInt(args[0]);
      }
      if (args[1] != null && args[1].isEmpty()) {
        heightSplitPercentage = Integer.parseInt(args[1]);
      }
    }
    int splitWidth = inputImage.getWidth() * widthSplitPercentage / 100;
    int splitHeight = inputImage.getHeight() * heightSplitPercentage / 100;
    outputImage = inputImage.copy();
    for (int i = 0; i < splitHeight; i++) {
      for (int j = 0; j < splitWidth; j++) {
        Pixel newPixel = applyFilterToPixel(inputImage, i, j);
        outputImage.setPixel(i, j, newPixel);
      }
    }
  }
}
