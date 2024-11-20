package ime.model.operation;

import ime.model.image.Image;
import java.util.List;

/**
 * This interface defines the contract for operations that involve multiple images. Implementing
 * classes should provide logic to process a list of images and return a resulting image. It is
 * particularly useful for operations that require combining or comparing images, such as blending
 * or merging.
 */
public interface MultipleImageOperation {

  /**
   * Applies the operation to a list of images and returns the resulting image. Each implementation
   * defines the specific operation to be performed on the images. The method may throw an
   * IllegalArgumentException if the input images or arguments are invalid, such as when images have
   * incompatible dimensions.
   *
   * @param images a list of input images to apply the operation.
   * @param args   additional optional arguments required by the operation.
   * @return the resulting image after the operation is applied.
   * @throws IllegalArgumentException if the input images are invalid.
   */
  Image apply(List<Image> images, String... args) throws IllegalArgumentException;
}
