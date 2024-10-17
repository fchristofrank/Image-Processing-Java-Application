import ime.cli.ImageProcessorCLI;

/**
 * This class contains utility methods to read a PPM image from file and print its contents.
 * It also supports reading and writing images in JPEG and PNG formats.
 */
public class Main {

  public static void main(String[] args) {
    new ImageProcessorCLI().run();
  }
}
