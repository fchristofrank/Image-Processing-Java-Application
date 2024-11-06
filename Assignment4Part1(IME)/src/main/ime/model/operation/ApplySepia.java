package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;

/**
 * This abstract class represents an operation to apply sepia filter to an image. This operation
 * supports a preview feature where the user can provide a split percentage for which the sepia
 * filter is applied. It implements the ImageOperation interface, providing functionality to apply
 * sepia filter to an image.
 */
public class ApplySepia implements ImageOperation {
  protected static final double[][] SEPIA_COLOR_TRANSFORMATION = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };
  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Pixel[][] pixels = new Pixel[height][width];
    processImage(inputImage, pixels, args);
    return new SimpleImage(height, width, inputImage.getType(), pixels);
  }

  protected void processImage(Image inputImage, Pixel[][] pixels, String... args) {
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        pixels[i][j] =
            inputImage.getPixel(i, j).scaleComponents(SEPIA_COLOR_TRANSFORMATION);
      }
    }
  }
}
