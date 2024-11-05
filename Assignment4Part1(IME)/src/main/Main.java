import java.io.InputStreamReader;

import ime.controller.operation.ImageOperationFactory;
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
   * @param args the command-line arguments; not used in this application.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    new ImageProcessorCLI(rd, ap, new ImageOperationFactory()).run();
  }
}
