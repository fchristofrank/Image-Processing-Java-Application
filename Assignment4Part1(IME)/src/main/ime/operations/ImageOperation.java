package ime.operations;

import java.io.IOException;

/**
 * Interface representing an operation that can be applied to an image.
 * Implementations of this interface define specific image processing
 * operations such as flip an image, adjust brightness of an image etc., that can modify or
 * transform an Image object.
 */
public interface ImageOperation {
  /**
   * Applies the specific image operation to the provided image.
   *
   * @param args the array of commands.
   */
  void apply(String[] args) throws IllegalArgumentException, IOException;

}
