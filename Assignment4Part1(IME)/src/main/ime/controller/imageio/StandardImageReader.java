package ime.controller.imageio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.RGBPixel;

/**
 * This class represents a reader for images in JPG and PNG formats from a specified file name.
 */
public class StandardImageReader implements ImageReader {
  @Override
  public Image read(String filename, ImageType imageType) throws IOException {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      if (image == null) {
        throw new IOException("Unsupported image format or corrupted file: " + filename);
      }

      int width = image.getWidth();
      int height = image.getHeight();
      Image simpleImage = new SimpleImage(height, width, imageType);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int pixel = image.getRGB(j, i);
          int r = (pixel >> 16) & 0xFF;
          int g = (pixel >> 8) & 0xFF;
          int b = pixel & 0xFF;
          if(imageType.equals(ImageType.RGB)){
            simpleImage.setPixel(i, j, new RGBPixel(r, g, b));
          }
        }
      }
      return simpleImage;

    } catch (IOException e) {
      throw new IOException("Error reading image: " + filename);
    }
  }
}
