package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;

/**
 * Controller class for performing image darkening operations.
 * This class is responsible for validating input arguments and routing them
 * to the appropriate darkening operation for execution.
 */
public class Darken extends AdjustBrightness {
  /**
   * This method creates an operation to darken the image.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public Darken(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    int darkenValue = Integer.parseInt(args[0]);
    args[0] = String.valueOf(-Math.abs(darkenValue));
    super.execute(args);
  }
}
