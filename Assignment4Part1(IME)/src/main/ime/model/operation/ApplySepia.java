package ime.model.operation;

import ime.constants.FilterConstants;
import ime.model.image.Image;
import ime.model.image.SimpleImage;

/**
 * This abstract class represents an operation to apply sepia filter to an image.
 * It implements the ImageOperation interface, providing functionality to
 * apply sepia filter to an image.
 */
public class ApplySepia implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage = new SimpleImage(height, width, inputImage.getType());
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i, j)
                        .scaleComponents(FilterConstants.SEPIA_COLOR_TRANSFORMATION));
      }
    }
    return outputImage;
  }
}
