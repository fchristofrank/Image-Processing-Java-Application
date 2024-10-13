package ime;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and print its contents.
 * It also supports reading and writing images in JPEG and PNG formats.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    // Existing implementation for reading PPM files
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }

    StringBuilder builder = new StringBuilder();
    // Read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    // Now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Read an image file in popular formats (JPEG, PNG) and print the colors of each pixel.
   *
   * @param filename the path of the image file.
   */
  public static void readImage(String filename) {
    try {
      BufferedImage image = ImageIO.read(new File(filename));

      if (image == null) {
        System.out.println("Unsupported image format or corrupted file: " + filename);
        return;
      }

      int width = image.getWidth();
      int height = image.getHeight();
      System.out.println("Width of image: " + width);
      System.out.println("Height of image: " + height);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int pixel = image.getRGB(j, i);
          int a = (pixel >> 24) & 0xff;
          int r = (pixel >> 16) & 0xFF;
          int g = (pixel >> 8) & 0xFF;
          int b = pixel & 0xFF;
          System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        }
      }

    } catch (IOException e) {
      System.out.println("Error reading image: " + filename);
      e.printStackTrace();
    }
  }

  /**
   * Save a BufferedImage to a file in a specified format (JPEG, PNG).
   *
   * @param image          the BufferedImage object.
   * @param format         the format (e.g., "jpg" or "png").
   * @param outputFilename the output filename.
   */
  public static void writeImage(BufferedImage image, String format, String outputFilename) {
    try {
      File outputFile = new File(outputFilename);
      ImageIO.write(image, format, outputFile);
      System.out.println("Image written to " + outputFilename);
    } catch (IOException e) {
      System.out.println("Error writing image to file: " + outputFilename);
      e.printStackTrace();
    }
  }

  // Demo main
  public static void main(String[] args) {
    String ppmFile = "sample.ppm";
    String jpgFile = "sample.jpg";
    String pngFile = "sample.png";

    // Reading PPM file
    System.out.println("Reading PPM:");
    ImageUtil.readPPM(ppmFile);

    // Reading JPEG image
    System.out.println("\nReading JPEG:");
    ImageUtil.readImage(jpgFile);

    // Reading PNG image
    System.out.println("\nReading PNG:");
    ImageUtil.readImage(pngFile);
  }
}
