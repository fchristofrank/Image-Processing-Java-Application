package ime.operations;

import java.util.HashMap;
import java.util.Map;

import ime.models.Image;


public abstract class ImageOperationManager implements ImageOperation {
  private final Map<String, Image> images;
  public ImageOperationManager() {
    this.images = new HashMap<>();
  }

  protected Image getImage(String imageName) {
    return this.images.get(imageName);
  }

  protected void addImage(String imageName, Image image) {
    this.images.put(imageName, image);
  }

  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 2) {
      System.out.println("Usage: load <image-path> <image-name>");
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
