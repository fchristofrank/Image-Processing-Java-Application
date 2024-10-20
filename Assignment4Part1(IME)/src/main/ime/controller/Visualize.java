package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.models.Image;
import ime.operations.AbstractVisualize;
import ime.operations.VisualizeBlue;
import ime.operations.VisualizeGreen;
import ime.operations.VisualizeIntensity;
import ime.operations.VisualizeLuma;
import ime.operations.VisualizeRed;
import ime.operations.VisualizeValue;

public class Visualize extends AbstractOperation {

  private final String command;

  public Visualize(ImageLibrary library, String command) {
    super(library);
    this.command = command;
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);
    String inputName = args[0];
    String outputName = args[1];
    Image inputImage = getImage(inputName);
    if (inputImage == null) {
      throw new IllegalArgumentException("Input image not found");
    }
    Image outputImage = inputImage.applyOperation(visualizeObjectFactory(this.command), args);
    addImage(outputName, outputImage);
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
