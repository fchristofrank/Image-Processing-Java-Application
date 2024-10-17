package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class ImageWriter {

  private static final Logger LOGGER = Logger.getLogger(ImageWriter.class.getName());

  public static void writeImage(BufferedImage image, String format, String outputFilename) {
    try {
      File outputFile = new File(outputFilename);
      ImageIO.write(image, format, outputFile);
      LOGGER.info("Image written to " + outputFilename);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error writing image to file: " + outputFilename, e);
    }
  }
}
