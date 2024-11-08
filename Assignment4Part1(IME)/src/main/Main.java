import ime.controller.cli.ImageProcessorCLI;
import ime.controller.operation.ImageOperationFactory;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * The Main class serves as the entry point for the image editor application. It initializes and
 * runs the command-line interface for image processing.
 */
public class Main {

  /**
   * The main method that starts the image editor application. It creates an instance of
   * ImageProcessorCLI and calls its run method.
   *
   * @param args the command-line arguments; not used in this application.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;

    if (args.length == 1) {
      String scriptPath = args[0];
      String command = "run " + scriptPath + "\nexit";
      Readable readableInput = new StringReader(command);
      new ImageProcessorCLI(readableInput, ap, new ImageOperationFactory()).run();
      System.out.println("Script Completed.");
      return;
    }

    if (args.length > 1) {
      System.out.println("Unsupported Operation :: Only accepts 1 script file as argument.");
      return;
    }

    new ImageProcessorCLI(rd, ap, new ImageOperationFactory()).run();
  }
}
