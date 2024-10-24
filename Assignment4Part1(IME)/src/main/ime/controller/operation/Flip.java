package ime.controller.operation;

import ime.model.image.Image;
import ime.model.image.ImageLibrary;
import ime.model.operation.ImageOperation;

/**
 * Abstract controller class for performing image flipping operations.
 * This class is responsible for validating the input arguments and routing them
 * to the appropriate operation for execution.
 */
public abstract class Flip extends AbstractOperation {
  /**
   * This method creates an operation to flip the image.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public Flip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(getOperation());
    addImage(outputName, outputImage);
    System.out.println("Applying horizontal flip. New image created: " + outputName);
  }

  protected abstract ImageOperation getOperation();
}
