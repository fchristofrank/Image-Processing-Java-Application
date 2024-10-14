package ime.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import ime.Image;
import ime.ImageProcessor;
import ime.ImageType;
import ime.PixelFactory;

public class ImageReader {

  private static final Logger LOGGER = Logger.getLogger(ImageReader.class.getName());

  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.SEVERE, "File " + filename + " not found!", e);
      return;
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
      return;
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    ImageProcessor imageProcessor = new Image(height, width, ImageType.RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imageProcessor.setPixel(i, j, PixelFactory.createPixel(ImageType.RGB, r, g, b));
        LOGGER.info("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  public static void readImage(String filename) {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      if (image == null) {
        LOGGER.log(Level.SEVERE, "Unsupported image format or corrupted file: " + filename);
        return;
      }

      int width = image.getWidth();
      int height = image.getHeight();
      ImageProcessor imageProcessor = new Image(height, width, ImageType.RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int pixel = image.getRGB(j, i);
          int r = (pixel >> 16) & 0xFF;
          int g = (pixel >> 8) & 0xFF;
          int b = pixel & 0xFF;
          imageProcessor.setPixel(i, j, PixelFactory.createPixel(ImageType.RGB, r, g, b));
          LOGGER.info("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        }
      }

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error reading image: " + filename, e);
    }
  }
}

