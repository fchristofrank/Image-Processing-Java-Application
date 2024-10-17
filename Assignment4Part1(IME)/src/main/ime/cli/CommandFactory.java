package ime.cli;

import ime.imageIO.ImageLibrary;
import ime.operations.Brighten;
import ime.operations.Darken;
import ime.operations.HorizontalFlip;
import ime.operations.ImageOperationManager;
import ime.operations.Load;
import ime.operations.Save;
import ime.operations.VerticalFlip;

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
      case "value-component":
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
