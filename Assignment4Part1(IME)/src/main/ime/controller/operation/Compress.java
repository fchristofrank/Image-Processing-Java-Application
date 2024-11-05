package ime.controller.operation;

import ime.controller.operation.repository.ImageRepo;
import ime.model.image.Image;
import ime.model.operation.ApplyCompression;

/**
 * Abstract controller class for compressing image brightness operations.
 * This class is responsible for validating the input arguments and routing them
 * to the appropriate operation for execution.
 */
public class Compress extends AbstractOperation {

  /**
   * Constructs an AbstractOperation with the specified image library.
   *
   * @param library the ImageLibrary to be used for image operations
   */
  public Compress(ImageRepo library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputImageName = args[1];
    String outputImageName = args[2];
    Image inputImage = getImage(inputImageName);
    Image outputImage = inputImage.applyOperation(new ApplyCompression(), args[0]);
    System.out.println("Applied compression to :: " + inputImageName + ". New image created :: " +
            outputImageName);
    addImage(outputImageName, outputImage);
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
