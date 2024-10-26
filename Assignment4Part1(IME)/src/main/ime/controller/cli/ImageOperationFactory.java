package ime.controller.cli;

import ime.controller.operation.Brighten;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.CombineRGB;
import ime.controller.operation.Darken;
import ime.controller.operation.Filter;
import ime.controller.operation.HorizontalFlip;
import ime.controller.operation.Load;
import ime.controller.operation.RGBSplit;
import ime.controller.operation.Save;
import ime.controller.operation.Sepia;
import ime.controller.operation.VerticalFlip;
import ime.controller.operation.Visualize;
import ime.controller.operation.repository.image.ImageLibrary;

/**
 * A class for creating CLI operations in an image processing application.
 * This class creates CLI operations to corresponding operation specified in the command.
 */
public class ImageOperationFactory implements OperationCreator {

  private final ImageLibrary imageLibrary;

  /**
   * Contains command names for CLI operations as constants.
   */
  private static class Commands {
    public static final String LOAD = "load";
    public static final String SAVE = "save";
    public static final String RGB_SPLIT = "rgb-split";
    public static final String BRIGHTEN = "brighten";
    public static final String DARKEN = "darken";
    public static final String VERTICAL_FLIP = "vertical-flip";
    public static final String HORIZONTAL_FLIP = "horizontal-flip";
    public static final String SEPIA = "sepia";
    public static final String RGB_COMBINE = "rgb-combine";
    public static final String BLUR = "blur";
    public static final String SHARPEN = "sharpen";
    public static final String RED_COMPONENT = "red-component";
    public static final String GREEN_COMPONENT = "green-component";
    public static final String BLUE_COMPONENT = "blue-component";
    public static final String LUMA_COMPONENT = "luma-component";
    public static final String VALUE_COMPONENT = "value-component";
    public static final String INTENSITY_COMPONENT = "intensity-component";
  }

  /**
   * Constructs a CommandFactory with the specified image library.
   *
   * @param imageLibrary the ImageLibrary to be used by all created operations
   */
  public ImageOperationFactory(ImageLibrary imageLibrary) {
    this.imageLibrary = imageLibrary;
  }

  /**
   * Creates and returns a CLIOperation based on the given command name.
   * <p>
   * This method supports the following commands:
   * <ul>
   *   <li>load</li>
   *   <li>save</li>
   *   <li>rgb-split</li>
   *   <li>brighten</li>
   *   <li>darken</li>
   *   <li>vertical-flip</li>
   *   <li>horizontal-flip</li>
   *   <li>sepia</li>
   *   <li>rgb-combine</li>
   *   <li>blur</li>
   *   <li>sharpen</li>
   *   <li>component (various component visualizations)</li>
   * </ul>
   * </p>
   *
   * @param commandName the name of the operation to create
   * @return a CLIOperation instance corresponding to the given command name
   * @throws IllegalArgumentException if the command name is not recognized
   */
  @Override
  public CLIOperation createOperation(String commandName) {
    switch (commandName.toLowerCase()) {
      case Commands.LOAD:
        return new Load(imageLibrary);
      case Commands.SAVE:
        return new Save(imageLibrary);
      case Commands.RGB_SPLIT:
        return new RGBSplit(imageLibrary);
      case Commands.BRIGHTEN:
        return new Brighten(imageLibrary);
      case Commands.DARKEN:
        return new Darken(imageLibrary);
      case Commands.VERTICAL_FLIP:
        return new VerticalFlip(imageLibrary);
      case Commands.HORIZONTAL_FLIP:
        return new HorizontalFlip(imageLibrary);
      case Commands.SEPIA:
        return new Sepia(imageLibrary);
      case Commands.RGB_COMBINE:
        return new CombineRGB(imageLibrary);
      case Commands.BLUR:
      case Commands.SHARPEN:
        return new Filter(imageLibrary, commandName);
      case Commands.RED_COMPONENT:
      case Commands.GREEN_COMPONENT:
      case Commands.BLUE_COMPONENT:
      case Commands.LUMA_COMPONENT:
      case Commands.VALUE_COMPONENT:
      case Commands.INTENSITY_COMPONENT:
        return new Visualize(imageLibrary, commandName);

      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
