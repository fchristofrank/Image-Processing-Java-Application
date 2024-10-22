package ime.imageIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.pixel.PixelFactory;
import ime.model.image.SimpleImage;

/**
 * This class represents a reader for images in PPM format from a specified file name.
 */
public class PPMReader implements Reader {

  @Override
  public Image read(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
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
      }
    }
    return simpleImage;
  }
}

