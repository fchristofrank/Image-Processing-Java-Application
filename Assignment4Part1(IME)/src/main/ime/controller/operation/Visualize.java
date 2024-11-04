package ime.controller.operation;

import ime.controller.operation.repository.ImageLibrary;
import ime.controller.operation.repository.ImageRepo;
import ime.model.image.Image;
import ime.model.operation.AbstractVisualize;
import ime.model.operation.VisualizeBlue;
import ime.model.operation.VisualizeGreen;
import ime.model.operation.VisualizeIntensity;
import ime.model.operation.VisualizeLuma;
import ime.model.operation.VisualizeRed;
import ime.model.operation.VisualizeValue;

/**
 * The controller class for the visualize operation. This class is responsible to validate the
 * visualize arguments and then route to the intended operation.
 */
public class Visualize extends AbstractOperation {

  private final String command;

  public Visualize(ImageRepo library, String command) {
    super(library);
    this.command = command;
  }

  /**
   * Responsible to interface between CLI and operation model. Performs the validation of arguments
   * and adds image back to library that holds the list of image of processed images by the user.
   *
   * @param args the arguments for an operations.
   * @throws IllegalArgumentException the input image must be valid.
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
    Image outputImage = inputImage.applyOperation(visualizeObjectFactory(this.command), args);
    addImage(outputName, outputImage);
    System.out.println("Extracted given component. New Image :: " + outputName);
  }

  private AbstractVisualize visualizeObjectFactory(String command) {
    switch (command) {
      case "red-component":
        return new VisualizeRed();
      case "green-component":
        return new VisualizeGreen();
      case "blue-component":
        return new VisualizeBlue();
      case "value-component":
        return new VisualizeValue();
      case "luma-component":
        return new VisualizeLuma();
      case "intensity-component":
        return new VisualizeIntensity();
      default:
        throw new IllegalArgumentException("Unknown component: " + command);
    }
  }
}
