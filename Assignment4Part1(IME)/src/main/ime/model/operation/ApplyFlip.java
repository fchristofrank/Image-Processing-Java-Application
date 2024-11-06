package ime.model.operation;

import ime.model.image.Image;

/**
 * This abstract class represents an operation to flip an image. It implements the ImageOperation
 * interface, providing functionality to flip an image horizontally or vertically.
 */
public abstract class ApplyFlip implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    return flipImage(inputImage, height, width);
  }

  protected abstract Image flipImage(Image inputImage, int height, int width);
}
