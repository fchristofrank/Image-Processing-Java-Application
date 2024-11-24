package fileutils;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * This class handles all behaviours associated with PNG images and implements the FileUtil
 * interface.
 */
public class PNGUtil implements FileUtil {

  /**
   * Converts a File object to a CustomImage object.
   *
   * @param filename File object passed as an argument
   * @return a CustomImage object
   */
  @Override
  public CustomImage fileToCustomImage(String filename) {
    File file = new File(filename);
    if (!file.exists()) {
      System.out.println(filename + " doesn't exist, please try again");
      return null;
    }
    try {
      BufferedImage image = ImageIO.read(file);
      // Get image dimensions
      int width = image.getWidth();
      int height = image.getHeight();

      List<List<CustomPixel>> customPixelList = new ArrayList<>();

      // Iterate through each pixel in the image
      for (int y = 0; y < height; y++) {
        List<CustomPixel> curRow = new ArrayList<>();
        for (int x = 0; x < width; x++) {

          // Get pixel color at (x, y)
          int pixel = image.getRGB(x, y);

          // Extract RGBA values
          int alpha = (pixel >> 24) & 0xff;
          int red = (pixel >> 16) & 0xff;
          int green = (pixel >> 8) & 0xff;
          int blue = pixel & 0xff;

          curRow.add(new BasicPixel(red, green, blue, alpha));
        }
        customPixelList.add(curRow);
      }
      // Create our customImage from the customPixelList and add it to the hashMap
      return new BasicImage(customPixelList);
    } catch (IOException e) {
      System.out.println(filename + " was not able to be loaded, try again.");
      return null;
    }
  }


  /**
   * Writes a CustomImage object to a File object.
   *
   * @param image CustomImage object passed to the method as a parameter
   * @param file  File object passed as an argument
   */
  @Override
  public boolean writeCustomImageToFile(CustomImage image, File file) throws IOException {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage bufferedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB);

    // Populate the BufferedImage with RGBA values
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        List<Integer> curPixelRGBA = image.get(i, j).getPixelRGBA();
        int red = curPixelRGBA.get(0);
        int green = curPixelRGBA.get(1);
        int blue = curPixelRGBA.get(2);
        int alpha = curPixelRGBA.get(3);

        // Combine RGBA into an integer
        int rgba = (alpha << 24) | (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(j, i, rgba);
      }
    }
    return ImageIO.write(bufferedImage, "png", file);
  }
}
