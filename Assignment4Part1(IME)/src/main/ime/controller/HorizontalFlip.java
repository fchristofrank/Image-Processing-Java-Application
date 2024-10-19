package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.operations.ApplyHorizontalFlip;

public class HorizontalFlip extends AbstractOperation{
  public HorizontalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(new ApplyHorizontalFlip(), args);
    addImage(outputName, outputImage);
    System.out.println("Applying horizontal flip. New image created: " + outputName);
  }
}
