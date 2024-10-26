package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;
import ime.model.image.Image;
import ime.model.operation.ApplySepia;

/**
 * Controller class for applying sepia filter on images.
 * This class is responsible for routing the control to the corresponding image operation
 * for execution.
 */
public class Sepia extends AbstractOperation {

  /**
   * This method creates an operation to apply sepia filter to the image.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public Sepia(ImageLibrary library) {
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
    Image outputImage = inputImage.applyOperation(new ApplySepia());
    addImage(outputName, outputImage);
    System.out.println("Applying Sepia filter. New image created: " + outputName);
  }
}
