package ime.controller;

import ime.imageIO.ImageLibrary;
import ime.models.Image;

public abstract class AbstractOperation implements CLIOperation{
  private final ImageLibrary library;

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
  public AbstractOperation(ImageLibrary library) {
    this.library = library;
  }
}
