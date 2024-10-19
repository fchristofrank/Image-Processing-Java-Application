package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.SimpleImage;

public abstract class ApplyFlip implements ImageOperation {

  @Override
  public Image apply(Image inputImage, String... args) throws IllegalArgumentException, IOException {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage = new SimpleImage(height, width, ImageType.RGB);
    flipImage(inputImage, outputImage, height, width);
    return outputImage;
  }

  protected abstract void flipImage(Image inputImage, Image outputImage, int height, int width);

}
