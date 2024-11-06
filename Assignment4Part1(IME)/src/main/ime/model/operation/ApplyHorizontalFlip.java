package ime.model.operation;

import ime.model.image.Image;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;

/**
 * This abstract class represents an operation to flip an image horizontally. It implements the
 * ImageOperation interface, providing functionality to flip an image horizontally.
 */
public class ApplyHorizontalFlip extends ApplyFlip {

  @Override
  protected Image flipImage(Image inputImage, int height, int width) {
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = inputImage.getPixel(i, width - 1 - j);
      }
    }
    return new SimpleImage(height, width, inputImage.getType(), pixels);
  }
}
