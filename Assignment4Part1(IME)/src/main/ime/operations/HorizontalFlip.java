package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;

public class HorizontalFlip extends Flip {

  public HorizontalFlip(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  protected void flipImage(Image inputImage, Image outputImage, int height, int width) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i, width - 1 - j));
      }
    }
  }

  @Override
  protected String getFlipType() {
    return "Horizontal";
  }

}
