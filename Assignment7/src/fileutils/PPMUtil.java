package fileutils;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles all behaviours associated with PPM images and implements the FileUtil
 * interface.
 */
public class PPMUtil implements FileUtil {

  /**
   * Converts a File object to a CustomImage object.
   *
   * @param filename File object passed as an argument
   * @return a CustomImage object
   */
  public CustomImage fileToCustomImage(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      return null;
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    List<List<CustomPixel>> newPixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<CustomPixel> pixelRow = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        pixelRow.add(new BasicPixel(red, green, blue, 255));
      }
      newPixels.add(pixelRow);
    }
    return new BasicImage(newPixels);
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

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      // Write the PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n"); // Max color value

      // Write pixel data in ASCII format (RGB)
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          List<Integer> curPixelRGBA = image.get(i, j).getPixelRGBA();
          int red = curPixelRGBA.get(0);
          int green = curPixelRGBA.get(1);
          int blue = curPixelRGBA.get(2);

          // Write RGB values for each pixel
          writer.write(red + " " + green + " " + blue + " ");
        }
        writer.write("\n");
      }
    }
    return true;
  }

}
