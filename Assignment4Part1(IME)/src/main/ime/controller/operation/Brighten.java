package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;

/**
 * Controller class for performing image brightening operations.
 * This class is responsible for validating input arguments and routing them
 * to the appropriate brightening operation for execution.
 */
public class Brighten extends AdjustBrightness {
  /**
   * This method creates an operation to brighten the image.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public Brighten(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    super.execute(args);
  }
}
