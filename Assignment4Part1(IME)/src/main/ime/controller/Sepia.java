package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.operations.ApplySepia;

public class Sepia extends AbstractOperation{
  public Sepia(ImageLibrary library) {
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
    Image outputImage = inputImage.applyOperation(new ApplySepia());
    addImage(outputName, outputImage);
  }
}
