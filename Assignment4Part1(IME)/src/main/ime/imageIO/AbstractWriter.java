package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public abstract class AbstractWriter implements ImageWriter {
  private static final Logger LOGGER = Logger.getLogger(JPGImageWriter.class.getName());

  /**
   * Writes a BufferedImage to a specified file in the given format.
   *
   * @param image          the BufferedImage to be written to file.
   * @param outputFilename the path and name of the output file where the image will be saved.
   */
  public void writeImage(BufferedImage image, String outputFilename) {
    try {
      File outputFile = new File(outputFilename);
      ImageIO.write(image, getFormat().getImageFormat(), outputFile);
      LOGGER.log(Level.FINE, "Image written to " + outputFilename);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error writing image to file: " + outputFilename, e);
      throw new RuntimeException(e);
    }

  }
  public abstract ImageFormat getFormat();
}
