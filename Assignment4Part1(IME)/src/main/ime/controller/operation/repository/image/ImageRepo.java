package ime.controller.operation.repository.image;

import ime.model.image.Image;

/**
 * The Image Repository defines the contract for managing images during the
 * image manipulation process. It provides methods for adding, retrieving, and checking the
 * existence of images in the repository.
 */
public interface ImageRepo {

  /**
   * Adds a new image or updates an existing one in the repository.
   *
   * @param imageName The name of the image.
   * @param image     The image to be stored.
   */
  void addImage(String imageName, Image image);

  /**
   * Retrieves the image associated with the given name.
   *
   * @param imageName The name of the image to be fetched.
   * @return The image object with the corresponding name, or null if not found.
   */
  Image getImage(String imageName);

}