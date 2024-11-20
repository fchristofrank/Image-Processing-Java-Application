package ime.controller.imageio;

import java.io.IOException;

import ime.model.image.Image;
import ime.model.image.ImageType;

/**
 * This interface serves as a reader that reads images in various formats from a given file path.
 * Implementations of this interface are responsible for parsing different image file formats and
 * converting them into the application's internal Image representation.
 */
public interface ImageReader {

  /**
   * This method reads an image of various formats from a given file path and returns a
   * corresponding image object.
   *
   * @param filename  the filename of the image.
   * @param imageType the type of the image.
   * @return the corresponding image object.
   * @throws IOException when the file name is invalid.
   */
  Image read(String filename, ImageType imageType) throws IOException;
}
