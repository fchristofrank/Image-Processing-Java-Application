package ime.controller.operation;

import ime.model.image.ImageLibrary;
import ime.model.image.Image;

/**
 * An abstract base class for CLI operations in an image processing application.
 */
public abstract class AbstractOperation implements CLIOperation {
  /**
   * The image library used for storing and retrieving images.
   */
  private final ImageLibrary library;

  /**
   * Constructs an AbstractOperation with the specified image library.
   *
   * @param library the ImageLibrary to be used for image operations
   */
  public AbstractOperation(ImageLibrary library) {
    this.library = library;
  }

  /**
   * Retrieves an image from the image library.
   *
   * @param imageName the name of the image to retrieve
   * @return the Image object associated with the given name
   */
  protected Image getImage(String imageName) {
    return this.library.getImage(imageName);
  }

  /**
   * Adds an image to the image library.
   *
   * @param imageName the name to associate with the image
   * @param image     the Image object to be added
   */
  protected void addImage(String imageName, Image image) {
    this.library.addImage(imageName, image);
  }

  /**
   * Validates the number of arguments provided to the operation.
   *
   * @param args the array of argument strings to validate
   * @throws IllegalArgumentException if the number of arguments is not exactly two
   */
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
