package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.models.ImageType;
import ime.models.SimpleImage;

public class HorizontalFlip extends ImageOperationManager {

  public HorizontalFlip(ImageLibrary imageLibrary) {
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
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.setPixel(i, j, inputImage.getPixel(i, width - 1 - j));
      }
    }
    addImage(outputName, outputImage);
    System.out.println("Applying horizontal flip. New image created: " + outputName);
  }

}
