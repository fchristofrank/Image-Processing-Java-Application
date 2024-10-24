package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Interface for writing images to a file.
 * Implementations of this interface are responsible for saving a
 * {@link BufferedImage} to the specified file path.
 */
public interface ImageWriter {
  /**
   * Writes the given image to the specified file.
   * The output file name should include the desired format extension (e.g., "image.png").
   *
   * @param image          the image to be written
   * @param outputFileName the name of the output file, including the file extension
   * @throws IOException if the file name is invalid or unsupported
   */
  void writeImage(BufferedImage image, String outputFileName) throws IOException;
}
