package ime.imageIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import ime.models.Image;
import ime.models.ImageType;
import ime.models.PixelFactory;
import ime.models.SimpleImage;

/**
 * This class represents a reader for images in PPM format from a specified file name.
 */
public class PPMReader implements Reader {

  private static final Logger LOGGER = Logger.getLogger(PPMReader.class.getName());

  @Override
  public Image read(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.SEVERE, "File " + filename + " not found!", e);
      throw new IOException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      LOGGER.log(Level.SEVERE, "Invalid PPM file: plain RAW file should begin with P3");
      throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Image simpleImage = new SimpleImage(height, width, ImageType.RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        simpleImage.setPixel(i, j, PixelFactory.createPixel(ImageType.RGB, r, g, b));
        LOGGER.info("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
    return simpleImage;
  }
}

