package ime.controller.operation;

import ime.model.image.Image;
import ime.model.operation.Blur;
import ime.model.operation.ImageOperation;
import ime.model.operation.Sharpen;
import ime.model.image.ImageLibrary;

public class Filter extends AbstractOperation {

  private final String command;

  public Filter(ImageLibrary library, String command) {
    super(library);
    this.command = command;
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
    Image outputImage = inputImage.applyOperation(filterObjectFactory(this.command), args);
    addImage(outputName, outputImage);
  }

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
