import ime.utils.ImageReader;

/**
 * This class contains utility methods to read a PPM image from file and print its contents.
 * It also supports reading and writing images in JPEG and PNG formats.
 */
public class Main {

  public static void main(String[] args) {
    String ppmFile = "sample.ppm";
    String jpgFile = "sample.jpg";
    String pngFile = "C:\\fall2024\\manhattan-small.png";

    // Reading PPM file
    System.out.println("Reading PPM:");
    ImageReader.readPPM(ppmFile);

    // Reading JPEG image
    System.out.println("\nReading JPEG:");
    ImageReader.readImage(jpgFile);

    // Reading PNG image
    System.out.println("\nReading PNG:");
    ImageReader.readImage(pngFile);
  }
}
