package ime.controller.operation;

import ime.model.image.ImageLibrary;
import ime.model.operation.ApplyVerticalFlip;
import ime.model.operation.ImageOperation;

/**
 * Controller class for executing vertical image flipping operations.
 * This class is responsible for validating input arguments specific to
 * the vertical flip operation and routing them to the appropriate
 * image operation for execution.
 */
public class VerticalFlip extends Flip {

  /**
   * This method creates an operation to flip the image vertically.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public VerticalFlip(ImageLibrary library) {
    super(library);
  }


  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    super.execute(args);
  }

  @Override
  protected ImageOperation getOperation() {
    return new ApplyVerticalFlip();
  }
}
