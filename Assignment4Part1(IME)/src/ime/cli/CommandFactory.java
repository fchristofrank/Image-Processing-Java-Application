package ime.cli;

import ime.operations.ImageOperationManager;
import ime.operations.Load;

public class CommandFactory {

  public ImageOperationManager createCommand(String commandName) {
    switch (commandName.toLowerCase()) {
      case "load":
        return new Load();
      case "save":
      // Add other commands
      case "red-component":
      case "green-component":
      case "blue-component":
      case "brighten":
      case "vertical-flip":
      case "horizontal-flip":
      case "value-component":
      case "rgb-split":
      case "rgb-combine":
      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
