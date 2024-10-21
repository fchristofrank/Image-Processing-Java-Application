package ime.controller;

import ime.operations.Combine;
import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import java.util.Arrays;

public class CombineRGB extends AbstractOperation {

  public CombineRGB(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);
    String inputName = args[0];
    Image redImage = getImage(args[1]);
    Image greenImage = getImage(args[2]);
    Image blueImage = getImage(args[3]);
    if (redImage == null || greenImage == null || blueImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = redImage.applyOperation(new Combine(),
        Arrays.asList(redImage,greenImage,blueImage), args);
    addImage(inputName, outputImage);

  }

  @Override
  protected void validateArgs(String[] args) {
    if (args.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
