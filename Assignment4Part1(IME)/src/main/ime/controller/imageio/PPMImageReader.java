package ime.controller.imageio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.RGBPixel;

/**
 * This class represents a reader for images in PPM format from a specified file name.
 */
public class PPMImageReader implements ImageReader {

  @Override
  public Image read(String filename, ImageType imageType) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IOException("File does not exist: " + filename);
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
    Image simpleImage = new SimpleImage(height, width, imageType);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        if(imageType.equals(ImageType.RGB)){
          simpleImage.setPixel(i, j, new RGBPixel(r, g, b));
        }
      }
    }
    return simpleImage;
  }
}

