import ime.controller.cli.ImageProcessorCLI;

/**
 * The Main class serves as the entry point for the image editor application.
 * It initializes and runs the command-line interface for image processing.
 */
public class Main {

  /**
   * The main method that starts the image editor application.
   * It creates an instance of ImageProcessorCLI and calls its run method.
   *
   * @param args the CLI arguments passed to the image editor application.
   */
  public static void main(String[] args) {
    new ImageProcessorCLI(System.in).run();
  }
}
