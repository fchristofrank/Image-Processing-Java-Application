package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;

public abstract class ApplyFlip implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage = new SimpleImage(height, width, inputImage.getType());
    flipImage(inputImage, outputImage, height, width);
    return outputImage;
  }

  protected abstract void flipImage(Image inputImage, Image outputImage, int height, int width);

}
