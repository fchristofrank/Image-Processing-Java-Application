package ime.controller.cli;

import ime.controller.operation.AdjustLevel;
import ime.controller.operation.Brighten;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.ColorCorrection;
import ime.controller.operation.CombineRGB;
import ime.controller.operation.Compress;
import ime.controller.operation.Darken;
import ime.controller.operation.FilterWithPreview;
import ime.controller.operation.Histogram;
import ime.controller.operation.HorizontalFlip;
import ime.controller.operation.Load;
import ime.controller.operation.RGBSplit;
import ime.controller.operation.Save;
import ime.controller.operation.VerticalFlip;
import ime.controller.operation.Visualize;
import ime.controller.operation.repository.ImageLibrary;
import ime.controller.operation.repository.ImageRepo;

/**
 * A class for creating CLI operations in an image processing application. This class creates CLI
 * operations to corresponding operation specified in the command.
 */
public class ImageOperationFactory implements OperationCreator {

  private final ImageRepo imageLibrary;

  /** Contains command names for CLI operations as constants. */
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
    public static final String HISTOGRAM = "histogram";
    public static final String COMPRESS = "compress";
    public static final String COLOR_CORRECTION = "color-correct";
    public static final String LEVELS_ADJUST = "levels-adjust";
  }

  /** Constructs a CommandFactory with the specified image library. */
  public ImageOperationFactory() {
    this.imageLibrary = new ImageLibrary();
  }

  /**
   * Creates and returns a CLIOperation based on the given command name.
   *
   * <p>This method supports the following commands:
   *
   * <ul>
   *   <li>load
   *   <li>save
   *   <li>rgb-split
   *   <li>brighten
   *   <li>darken
   *   <li>vertical-flip
   *   <li>horizontal-flip
   *   <li>sepia
   *   <li>rgb-combine
   *   <li>blur
   *   <li>sharpen
   *   <li>component (various component visualizations)
   * </ul>
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
      case Commands.RGB_COMBINE:
        return new CombineRGB(imageLibrary);
      // filter commands;
      case Commands.BLUR:
      case Commands.SHARPEN:
      case Commands.SEPIA:
        return new FilterWithPreview(imageLibrary, commandName);
      // visualize commands;
      case Commands.RED_COMPONENT:
      case Commands.GREEN_COMPONENT:
      case Commands.BLUE_COMPONENT:
      case Commands.LUMA_COMPONENT:
      case Commands.VALUE_COMPONENT:
      case Commands.INTENSITY_COMPONENT:
        return new Visualize(imageLibrary, commandName);
      case Commands.COMPRESS:
        return new Compress(imageLibrary);
      case Commands.HISTOGRAM:
        return new Histogram(imageLibrary);
      case Commands.COLOR_CORRECTION:
        return new ColorCorrection(imageLibrary);
      case Commands.LEVELS_ADJUST:
        return new AdjustLevel(imageLibrary);

      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
