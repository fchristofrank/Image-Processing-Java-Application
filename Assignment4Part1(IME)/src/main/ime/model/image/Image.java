package ime.model.image;

import ime.model.operation.ImageOperation;
import ime.model.operation.MultipleImageOperation;
import ime.model.pixel.Pixel;
import java.util.List;

/**
 * Represents an image with pixel manipulation capabilities. This interface defines the fundamental
 * operations that can be performed on an image, including retrieving and modifying pixel data,
 * applying operations, and obtaining image dimensions and type.
 */
public interface Image {

  /**
   * Get the height of the image.
   *
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Get the width of the image.
   *
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Retrieves the pixel at the specified row and column.
   *
   * @param row    the row index of the pixel (0-based).
   * @param column the column index of the pixel (0-based).
   * @return the Pixel object at the specified location.
   * @throws IllegalArgumentException if the specified row or column index is out of bounds.
   */
  Pixel getPixel(int row, int column) throws IllegalArgumentException;

  /**
   * Get the type of the image.
   *
   * @return the ImageType of the image.
   */
  ImageType getType();

  /**
   * Applies a single image operation to this image.
   *
   * @param operation the ImageOperation to be applied.
   * @param args      optional arguments for the operation.
   * @return a new Image instance that results from applying the operation.
   * @throws IllegalArgumentException if the operation cannot be applied or if the arguments are
   *                                  invalid.
   */
  Image applyOperation(ImageOperation operation, String... args) throws IllegalArgumentException;

  /**
   * Applies a multiple image operation to this image using a list of images.
   *
   * @param operation the MultipleImageOperation to be applied.
   * @param images    a list of Image instances to be used in the operation.
   * @param args      optional arguments for the operation.
   * @return a new Image instance that results from applying the operation.
   * @throws IllegalArgumentException if the operation cannot be applied or if the arguments are
   *                                  invalid.
   */
  Image applyOperation(MultipleImageOperation operation, List<Image> images, String... args)
      throws IllegalArgumentException;
}
