package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;

public class ApplyBrightness implements ImageOperation {
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    int alpha = Integer.parseInt(args[0]);
    Image outputImage = new SimpleImage(height, width, inputImage.getType());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i, j).shiftComponents(alpha));
      }
    }
    return outputImage;
  }
}
