package ime.model.image;

import java.util.HashMap;
import java.util.Map;

/**
 * The ImageLibrary class serves as a central repository for managing images during the
 * editing process.
 * It acts as an in-memory buffer, storing images that have been loaded or created through various
 * image processing operations. This allows for efficient access and manipulation of images
 * before they are saved to the file system.
 */
public class ImageLibrary {
  private final Map<String, Image> images;

  /**
   * This method creates a new image library with empty image repository.
   */
  public ImageLibrary() {
    this.images = new HashMap<>();
  }

  /**
   * This method adds a new image or updates an existing one to the library.
   *
   * @param imageName The name of the image.
   * @param image     The image to be stored.
   */
  public void addImage(String imageName, Image image) {
    images.put(imageName, image);
  }

  /**
   * This method gets the image which is associated with the given name.
   *
   * @param imageName the name of the image which has to be fetched.
   * @return the image object with the corresponding name.
   */
  public Image getImage(String imageName) {
    return images.get(imageName);
  }

  /**
   * Checks if an image with the given name exists in the library.
   *
   * @param imageName The name of the image to check.
   * @return true if the image exists in the library, false otherwise.
   */
  public boolean hasImage(String imageName) {
    return images.containsKey(imageName);
  }
}