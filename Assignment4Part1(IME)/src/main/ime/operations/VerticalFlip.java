package ime.operations;
import ime.imageIO.ImageLibrary;
import ime.models.Image;

public class VerticalFlip extends Flip {
  public VerticalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  protected void flipImage(Image inputImage, Image outputImage, int height, int width) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(height - 1 - i, j));
      }
    }
  }

  @Override
  protected String getFlipType() {
    return "Vertical";
  }
}
