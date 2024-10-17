package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.SimpleImage;

public abstract class Flip extends ImageOperationManager {

  public Flip(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Image outputImage = new SimpleImage(height, width, ImageType.RGB);
    flipImage(inputImage, outputImage, height, width);
    addImage(outputName, outputImage);
    System.out.println("Applying " + getFlipType() + " flip. New image created: " + outputName);
  }

  protected abstract void flipImage(Image inputImage, Image outputImage, int height, int width);

  protected abstract String getFlipType();
}
