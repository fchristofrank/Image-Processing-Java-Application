package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;
import ime.model.image.Image;

public class AdjustLevel extends AbstractOperation{


  /**
   * Constructs an AbstractOperation with the specified image library.
   *
   * @param library the ImageLibrary to be used for image operations
   */
  public AdjustLevel(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputName = args[3];
    String outputName = args[4];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(new ime.model.operation.AdjustLevel(), args);
    addImage(outputName, outputImage);
    System.out.println("Adjusted the Level. New Image :: " + outputName);
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 5) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
