package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ImageWriter class provides functionality for writing PPM image files to disk.
 */
public class PPMImageWriter implements ImageWriter {
  private static final Logger LOGGER = Logger.getLogger(PPMImageWriter.class.getName());

  /**
   * Writes a BufferedImage to a PPM (P3) format file.
   *
   * @param image          the BufferedImage to be written as a PPM file.
   * @param outputFilename the path and name of the output PPM file.
   */
  @Override
  public void writeImage(BufferedImage image, String outputFilename) throws IOException{
    try (FileWriter writer = new FileWriter(outputFilename)) {
      int width = image.getWidth();
      int height = image.getHeight();

      // Write PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");  // Max color value

      // Write pixel data
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);
          int r = (rgb >> 16) & 0xFF;
          int g = (rgb >> 8) & 0xFF;
          int b = rgb & 0xFF;
          writer.write(r + " " + g + " " + b + " ");
        }
        writer.write("\n");
      }

      LOGGER.log(Level.FINE, "PPM image written to " + outputFilename);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error writing PPM image to file: " + outputFilename, e);
      throw new IOException("Error writing PPM image to file: " + outputFilename, e);
    }
  }
}
