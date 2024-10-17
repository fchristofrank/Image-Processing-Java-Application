package ime.imageIO;

import java.util.HashMap;
import java.util.Map;

import ime.models.Image;

public class ImageLibrary {
  private final Map<String, Image> images;

  public ImageLibrary() {
    this.images = new HashMap<>();
  }

  public void addImage(String imageName, Image image) {
    images.put(imageName, image);
  }

  public Image getImage(String imageName) {
    return images.get(imageName);
  }

  public boolean hasImage(String imageName) {
    return images.containsKey(imageName);
  }
}