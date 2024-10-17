package ime.cli;

import ime.imageIO.ImageLibrary;
import ime.operations.Brighten;
import ime.operations.Darken;
import ime.operations.HorizontalFlip;
import ime.operations.ImageOperationManager;
import ime.operations.Load;
import ime.operations.Save;
import ime.operations.VerticalFlip;
import ime.operations.VisualizeBlue;
import ime.operations.VisualizeGreen;
import ime.operations.VisualizeIntensity;
import ime.operations.VisualizeLuma;
import ime.operations.VisualizeRed;

public class CommandFactory {

  private final ImageLibrary imageLibrary;

  public CommandFactory(ImageLibrary imageLibrary) {
    this.imageLibrary = imageLibrary;
  }

  public ImageOperationManager createCommand(String commandName) {
    switch (commandName.toLowerCase()) {
      case "load":
        return new Load(imageLibrary);
      case "save":
        return new Save(imageLibrary);
      case "red-component":
        return new VisualizeRed(imageLibrary);
      case "green-component":
        return new VisualizeGreen(imageLibrary);
      case "blue-component":
        return new VisualizeBlue(imageLibrary);
      case "value-component":
        return new VisualizeBlue(imageLibrary);
      case "luma-component":
        return new VisualizeLuma(imageLibrary);
      case "intensity-component":
        return new VisualizeIntensity(imageLibrary);
      case "rgb-split":
      case "rgb-combine":
        return null;
      case "brighten":
        return new Brighten(imageLibrary);
      case "darken":
        return new Darken(imageLibrary);
      case "vertical-flip":
        return new VerticalFlip(imageLibrary);
      case "horizontal-flip":
        return new HorizontalFlip(imageLibrary);
      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
