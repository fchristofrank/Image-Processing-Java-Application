package ime.controller.operation;

import ime.model.image.ImageLibrary;
import ime.model.image.Image;
import ime.model.operation.ImageOperation;

public abstract class Flip extends AbstractOperation {
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
