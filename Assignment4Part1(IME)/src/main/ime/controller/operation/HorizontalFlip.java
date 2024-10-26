package ime.controller.operation;

import ime.controller.operation.repository.image.ImageLibrary;
import ime.model.operation.ApplyHorizontalFlip;
import ime.model.operation.ImageOperation;

/**
 * Controller class for executing horizontal image flipping operations.
 * This class is responsible for validating input arguments specific to
 * the horizontal flip operation and routing them to the appropriate
 * image operation for execution.
 */
public class HorizontalFlip extends Flip {
  /**
   * This method creates an operation to flip the image horizontally.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public HorizontalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    super.execute(args);
  }

  @Override
  protected ImageOperation getOperation() {
    return new ApplyHorizontalFlip();
  }

  @Override
  protected String getOperationName() {
    return "Horizontal Flip";
  }

}
