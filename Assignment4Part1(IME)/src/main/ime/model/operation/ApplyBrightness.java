package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;

/**
 * This abstract class represents an operation to apply brightness adjustment to an image. It
 * implements the ImageOperation interface, providing functionality to manipulate the brightness of
 * an input image based on a specified alpha value.
 */
public class ApplyBrightness implements ImageOperation {
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    int alpha = Integer.parseInt(args[0]);
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = inputImage.getPixel(i, j).shiftComponents(alpha);
      }
    }
    return new SimpleImage(height, width, inputImage.getType(), pixels);
  }
}
