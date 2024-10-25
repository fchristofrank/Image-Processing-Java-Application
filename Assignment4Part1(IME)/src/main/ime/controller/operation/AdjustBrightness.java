package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;
import ime.model.image.Image;
import ime.model.operation.ApplyBrightness;

/**
 * Abstract controller class for adjusting image brightness operations.
 * This class is responsible for validating the input arguments and routing them
 * to the appropriate operation for execution.
 */
public abstract class AdjustBrightness extends AbstractOperation {
  /**
   * This method creates an operation to adjust brightness.
   *
   * @param library the ImageLibrary to be used for image operations.
   */
  public AdjustBrightness(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    int alpha = Integer.parseInt(args[0]);
    String inputName = args[1];
    String outputName = args[2];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(new ApplyBrightness(), String.valueOf(alpha));
    addImage(outputName, outputImage);
    System.out.println("Adjusting Brightness. New image created: " + outputName);
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    try {
      Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(String.format("Invalid brightness adjustment value '%s'."
              + " It must be an integer.", args[0]));
    }
  }
}
