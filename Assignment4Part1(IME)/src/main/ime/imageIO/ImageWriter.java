package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * The ImageWriter class provides functionality for writing image files to disk.
 * It utilizes the ImageIO class to handle the actual image writing process.
 */
public class ImageWriter {

  private static final Logger LOGGER = Logger.getLogger(ImageWriter.class.getName());

  /**
   * Writes a BufferedImage to a specified file in the given format.
   *
   * @param image          the BufferedImage to be written to file.
   * @param format         the format of the image (e.g., "png", "jpg").
   * @param outputFilename the path and name of the output file where the image will be saved.
   */
  public static void writeImage(BufferedImage image, String format, String outputFilename) {
    try {
      File outputFile = new File(outputFilename);
      ImageIO.write(image, format, outputFile);
      LOGGER.log(Level.FINE, "Image written to " + outputFilename);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error writing image to file: " + outputFilename, e);
    }
  }
}
