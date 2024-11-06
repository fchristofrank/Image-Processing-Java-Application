package ime.controller.operation;

import ime.controller.operation.repository.ImageRepo;
import ime.model.image.Image;

/** An abstract base class for CLI operations in an image processing application. */
public abstract class AbstractOperation implements CLIOperation {
  /** The image library used for storing and retrieving images. */
  private final ImageRepo library;

  /**
   * Constructs an AbstractOperation with the specified image library.
   *
   * @param library the ImageLibrary to be used for image operations
   */
  public AbstractOperation(ImageRepo library) {
    this.library = library;
  }

  /**
   * Retrieves an image from the image library.
   *
   * @param imageName the name of the image to retrieve
   * @return the Image object associated with the given name
   * @throws IllegalArgumentException if the image is not found or the library is null
   */
  protected Image getImage(String imageName) {
    if (this.library == null) {
      throw new IllegalArgumentException("Image library is not initialized.");
    }

    Image image = this.library.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found in the library: " + imageName);
    }

    return image;
  }

  /**
   * Adds an image to the image library.
   *
   * @param imageName the name to associate with the image
   * @param image the Image object to be added
   */
  protected void addImage(String imageName, Image image) {
    if (this.library == null) {
      throw new IllegalArgumentException("Image library is not initialized.");
    }

    this.library.addImage(imageName, image);
  }

  /**
   * Validates the number of arguments provided to the operation. This is a basic validation and
   * each operation will override this if necessary.
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
