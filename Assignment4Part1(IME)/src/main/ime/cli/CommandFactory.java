package ime.cli;

import java.util.Queue;

import ime.controller.Brighten;
import ime.controller.CLIOperation;
import ime.controller.Darken;
import ime.controller.HorizontalFlip;
import ime.controller.Load;
import ime.controller.RGBSplit;
import ime.controller.Save;
import ime.controller.Sepia;
import ime.controller.VerticalFlip;
import ime.controller.Visualize;
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
      case "rgb-split":
        return new RGBSplit(imageLibrary);
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
      case "sepia":
        return new Sepia(imageLibrary);
      default:
        if (commandName.contains("component")) {
          return new Visualize(imageLibrary, commandName);
        }
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
