package ime.cli;

import ime.imageIO.ImageLibrary;
import ime.operations.AdjustBrightness;
import ime.operations.HorizontalFlip;
import ime.operations.ImageOperationManager;
import ime.operations.Load;
import ime.operations.Save;

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
      case "green-component":
      case "blue-component":
      case "brighten":
      case "darken":
        return new AdjustBrightness(imageLibrary);
      case "vertical-flip":
      case "horizontal-flip":
        return new HorizontalFlip(imageLibrary);
      case "value-component":
      case "rgb-split":
      case "rgb-combine":
      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
