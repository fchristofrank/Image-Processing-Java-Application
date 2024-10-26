package ime.controller.operation.repository.image;

import java.util.HashMap;
import java.util.Map;

import ime.model.image.Image;

/**
 * The ImageLibrary class serves as a central repository for managing images during the
 * editing process.
 * It acts as an in-memory buffer, storing images that have been loaded or created through various
 * image processing operations. This allows for efficient access and manipulation of images
 * before they are saved to the file system.
 */
public class ImageLibrary implements ImageRepo {
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

}