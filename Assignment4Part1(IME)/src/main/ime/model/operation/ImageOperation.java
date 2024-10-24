package ime.model.operation;

import ime.model.image.Image;

/**
 * Interface representing an operation that can be applied to an image.
 * <p>
 * Implementations of this interface define specific image processing
 * operations such as flip an image, adjust brightness of an image etc., that can modify or
 * transform an Image object.
 * </p>
 */
public interface ImageOperation {

  /**
   * This method applies the specific operation to the given image and returns a new
   * processed image.
   * This method does not alter or process the input image.
   *
   * @param inputImage the image on which operation has to be performed.
   * @param args       the arguments for the operation.
   * @return the processed image.
   * @throws IllegalArgumentException when the arguments are invalid.
   */
  Image apply(Image inputImage, String... args) throws IllegalArgumentException;

}
