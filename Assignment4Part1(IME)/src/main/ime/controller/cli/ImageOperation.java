package ime.controller.cli;

/**
 * Enum for representing various image processing operations.
 */
public enum ImageOperation {
  LOAD("load"),
  SAVE("save"),
  RGB_SPLIT("rgb-split"),
  BRIGHTEN("brighten"),
  DARKEN("darken"),
  VERTICAL_FLIP("vertical-flip"),
  HORIZONTAL_FLIP("horizontal-flip"),
  SEPIA("sepia"),
  RGB_COMBINE("rgb-combine"),
  BLUR("blur"),
  SHARPEN("sharpen"),
  RED_COMPONENT("red-component"),
  GREEN_COMPONENT("green-component"),
  BLUE_COMPONENT("blue-component"),
  LUMA_COMPONENT("luma-component"),
  VALUE_COMPONENT("value-component"),
  INTENSITY_COMPONENT("intensity-component");

  private final String command;

  ImageOperation(String command) {
    this.command = command;
  }

  public String getCommand() {
    return command;
  }

  public static ImageOperation fromCommand(String command) {
    for (ImageOperation operation : values()) {
      if (operation.command.equalsIgnoreCase(command)) {
        return operation;
      }
    }
    throw new IllegalArgumentException("Unknown command: " + command);
  }
}
