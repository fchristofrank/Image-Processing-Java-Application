package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;

public class CombineRGB extends AbstractOperation {

  public CombineRGB(ImageLibrary library) {
    super(library);
    String redImage = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(filterObjectFactory(this.command), args);
    addImage(outputName, outputImage);
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);

  }
}
