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
import ime.model.image.ImageLibrary;

/**
 * A class for creating CLI operations in an image processing application.
 * This class creates CLI operations to corresponding operation specified in the command.
 */
public class ImageOperationFactory implements OperationCreator{

  private final ImageLibrary imageLibrary;

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
      case "load":
        return new Load(imageLibrary);
      case "save":
        return new Save(imageLibrary);
      case "rgb-split":
        return new RGBSplit(imageLibrary);
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
      case "rgb-combine":
        return new CombineRGB(imageLibrary);
      case "blur":
      case "sharpen":
        return new Filter(imageLibrary, commandName);
      case "red-component":
      case "green-component":
      case "blue-component":
      case "luma-component":
      case "value-component":
      case "intensity-component":
        return new Visualize(imageLibrary, commandName);

      default:
        throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
