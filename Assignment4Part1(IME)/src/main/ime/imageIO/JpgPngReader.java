package ime.imageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

public class JpgPngReader implements Reader{
  private static final Logger LOGGER = Logger.getLogger(PPMReader.class.getName());
  @Override
  public Image read(String filename) throws IOException {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      if (image == null) {
        LOGGER.log(Level.SEVERE, "Unsupported image format or corrupted file: " + filename);
        throw new IOException("Unsupported image format or corrupted file: " + filename);
      }

      int width = image.getWidth();
      int height = image.getHeight();
      Image simpleImage = new SimpleImage(height, width, ImageType.RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int pixel = image.getRGB(j, i);
          int r = (pixel >> 16) & 0xFF;
          int g = (pixel >> 8) & 0xFF;
          int b = pixel & 0xFF;
          simpleImage.setPixel(i, j, PixelFactory.createPixel(ImageType.RGB, r, g, b));
          //LOGGER.info("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        }
      }
      return simpleImage;

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error reading image: " + filename, e);
      throw new IOException("Error reading image: " + filename);
    }
  }
}
