package ime.controller.imageio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.Pixel;
import ime.model.pixel.PixelFactory;

/**
 * A reader for PPM (Portable Pixmap) image files. This class supports both the P3 (ASCII-based) and
 * P6 (binary) formats of PPM files.
 * <p>
 * The PPM file format is a simple image file format for storing pixel data. P3 files store the data
 * in human-readable ASCII text, while P6 files store the data in a more compact binary
 * representation.
 * </p>
 */
public class PPMImageReader implements ImageReader {

  /**
   * Reads a PPM image from the specified file and returns it as an {@link Image} object.
   *
   * @param filename  the name of the file to read.
   * @param imageType the type of the image (e.g., RGB).
   * @return an {@link Image} object containing the image data.
   * @throws IOException if the file cannot be read or the format is invalid.
   */
  @Override
  public Image read(String filename, ImageType imageType) throws IOException {
    // First try to read the header using a Scanner to determine format
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String magicNumber = readMagicNumber(reader);
    reader.close();

    // Based on magic number, choose appropriate reading method
    if (magicNumber.equals("P3")) {
      return readP3(filename, imageType);
    } else if (magicNumber.equals("P6")) {
      return readP6(filename, imageType);
    } else {
      throw new IOException("Invalid PPM file: file should begin with P3 or P6");
    }
  }

  /**
   * Reads the magic number from the PPM file header.
   *
   * @param reader a {@link BufferedReader} for reading the file.
   * @return the magic number ("P3" or "P6").
   * @throws IOException if the magic number is missing or the file format is invalid.
   */
  private String readMagicNumber(BufferedReader reader) throws IOException {
    String line;
    while ((line = reader.readLine()) != null) {
      line = line.trim();
      if (line.isEmpty() || line.charAt(0) == '#') {
        continue;
      }
      return line;
    }
    throw new IOException("Invalid PPM file: no magic number found");
  }

  /**
   * Reads a P3 (ASCII-based) PPM file.
   *
   * @param filename  the name of the file to read.
   * @param imageType the type of the image (e.g., RGB).
   * @return an {@link Image} object containing the image data.
   * @throws IOException if the file cannot be read or the format is invalid.
   */
  private Image readP3(String filename, ImageType imageType) throws IOException {
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

    sc.next(); // Skip P3
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt(); // Skip maxval

    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = PixelFactory.createPixel(ImageType.RGB, r, g, b);
      }
    }
    sc.close();
    return new SimpleImage(height, width, ImageType.RGB, pixels);
  }

  /**
   * Reads a P6 (binary) PPM file.
   *
   * @param filename  the name of the file to read.
   * @param imageType the type of the image (e.g., RGB).
   * @return an {@link Image} object containing the image data.
   * @throws IOException if the file cannot be read or the format is invalid.
   */
  private Image readP6(String filename, ImageType imageType) throws IOException {
    DataInputStream dis = new DataInputStream(new FileInputStream(filename));
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filename)));

    // Read header
    String magicNumber = readMagicNumber(reader);
    if (!magicNumber.equals("P6")) {
      throw new IOException("Invalid P6 PPM file");
    }

    // Skip comments and read dimensions
    String line;
    String[] dimensions = null;
    while ((line = reader.readLine()) != null) {
      line = line.trim();
      if (line.isEmpty() || line.charAt(0) == '#') {
        continue;
      }
      dimensions = line.split("\\s+");
      break;
    }

    if (dimensions == null || dimensions.length != 2) {
      throw new IOException("Invalid PPM file: missing dimensions");
    }

    int width = Integer.parseInt(dimensions[0]);
    int height = Integer.parseInt(dimensions[1]);

    // Read maximum value
    String maxvalLine;
    while ((maxvalLine = reader.readLine()) != null) {
      maxvalLine = maxvalLine.trim();
      if (maxvalLine.isEmpty() || maxvalLine.charAt(0) == '#') {
        continue;
      }
      break;
    }
    if (maxvalLine == null) {
      throw new IOException("Invalid PPM file: missing maxval");
    }

    int maxVal = Integer.parseInt(maxvalLine);
    if (maxVal > 255) {
      throw new IOException("Unsupported PPM file: maximum value must be 255 or less");
    }

    // Skip to the binary data
    reader.close();
    dis = skipHeader(filename);

    // Read binary pixel data
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = dis.read();
        int g = dis.read();
        int b = dis.read();
        if (r == -1 || g == -1 || b == -1) {
          throw new IOException("Unexpected end of file");
        }
        pixels[i][j] = PixelFactory.createPixel(ImageType.RGB, r, g, b);
      }
    }

    dis.close();
    return new SimpleImage(height, width, ImageType.RGB, pixels);
  }

  /**
   * Skips the header of a P6 PPM file to locate the start of the binary pixel data.
   *
   * @param filename the name of the file.
   * @return a {@link DataInputStream} positioned at the start of the binary data.
   * @throws IOException if the file format is invalid or an error occurs while reading.
   */
  private DataInputStream skipHeader(String filename) throws IOException {
    DataInputStream dis = new DataInputStream(new FileInputStream(filename));
    int newlines = 0;
    boolean inComment = false;

    // Skip until we find the start of binary data (after third newline not in a comment)
    while (newlines < 3) {
      int b = dis.read();
      if (b == -1) {
        throw new IOException("Unexpected end of file in header");
      }

      if (b == '#') {
        inComment = true;
      } else if (b == '\n') {
        if (!inComment) {
          newlines++;
        }
        inComment = false;
      }
    }
    return dis;
  }
}
