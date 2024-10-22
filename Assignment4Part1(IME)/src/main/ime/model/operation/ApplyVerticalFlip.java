package ime.model.operation;

import ime.model.image.Image;

public class ApplyVerticalFlip extends ApplyFlip {

  @Override
  protected void flipImage(Image inputImage, Image outputImage, int height, int width) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(height - 1 - i, j));
      }
    }
  }

}
