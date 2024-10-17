package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.Image;


public abstract class ImageOperationManager implements ImageOperation {
  private final ImageLibrary library;

  public ImageOperationManager(ImageLibrary library) {
    this.library = library;
  }

  protected Image getImage(String imageName) {
    return this.library.getImage(imageName);
  }

  protected void addImage(String imageName, Image image) {
    this.library.addImage(imageName, image);
  }

  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
