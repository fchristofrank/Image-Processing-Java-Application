package ime.cli;

import ime.controller.Brighten;
import ime.controller.CLIOperation;
import ime.controller.Darken;
import ime.controller.HorizontalFlip;
import ime.controller.Load;
import ime.controller.Save;
import ime.controller.VerticalFlip;
import ime.imageIO.ImageLibrary;

public class CommandFactory {

  private final ImageLibrary imageLibrary;

  public CommandFactory(ImageLibrary imageLibrary) {
    this.imageLibrary = imageLibrary;
  }

  public CLIOperation createCommand(String commandName) {
    switch (commandName.toLowerCase()) {
      case "load":
        return new Load(imageLibrary);
      case "save":
        return new Save(imageLibrary);
//      case "red-component":
//        return new VisualizeRed(imageLibrary);
//      case "green-component":
//        return new VisualizeGreen(imageLibrary);
//      case "blue-component":
//        return new VisualizeBlue(imageLibrary);
//      case "value-component":
//        return new VisualizeBlue(imageLibrary);
//      case "luma-component":
//        return new VisualizeLuma(imageLibrary);
//      case "intensity-component":
//        return new VisualizeIntensity(imageLibrary);
//      case "rgb-split":
//      case "rgb-combine":
//        return null;
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
