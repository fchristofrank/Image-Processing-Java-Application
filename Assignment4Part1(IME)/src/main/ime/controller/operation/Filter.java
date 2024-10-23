package ime.controller.operation;

import ime.model.image.Image;
import ime.model.operation.Blur;
import ime.model.operation.ImageOperation;
import ime.model.operation.Sharpen;
import ime.model.image.ImageLibrary;

/**
 * The given Filter controller validates the args received from the command line, and it gives the
 * object of the right instance based on blur or sharpen object with which the actual filter
 * operation can be implemented. It also is responsible in maintaining the output of the images
 * inside the library object to maintain the historic operations and objects/
 */
public class Filter extends AbstractOperation {

  private final String command;

  public Filter(ImageLibrary library, String command) {
    super(library);
    this.command = command;
  }

  /**
   * Command line argument verification takes place after which the images on which the user intends
   * to perform the operation is loaded from the library object and the request is routed to the
   * correct and desired filter operations.
   *
   * @param args the arguments for an operations.
   * @throws IllegalArgumentException if the user tries to operate on image that is not loaded yet.
   */
  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(filterObjectFactory(this.command), args);
    addImage(outputName, outputImage);
  }

  /**
   * Responsible for returning the object as per the operation specified.
   *
   * @param command the intended operation is sent as argument for the method.
   * @return returns the object based on the command received.
   */
  private ImageOperation filterObjectFactory(String command) {

    switch (command) {
      case "blur":
        return new Blur();
      case "sharpen":
        return new Sharpen();
      default:
        throw new UnsupportedOperationException("Unknown command given.");
    }
  }
}
