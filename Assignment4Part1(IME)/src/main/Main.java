import java.io.InputStreamReader;
import java.io.StringReader;

import ime.controller.cli.ImageProcessorCLI;
import ime.controller.gui.GUIController;
import ime.controller.operation.GUIImageOperationFactory;
import ime.controller.operation.ImageOperationFactory;
import ime.view.gui.ImageEditorFrame;
import ime.view.gui.ImageEditorView;

/**
 * The Main class serves as the entry point for the image editor application. It initializes and
 * runs the command-line interface for image processing.
 */
public class Main {

  /**
   * Entry point for the Image Editor application. Supports both GUI and CLI modes and provides the
   * ability to execute scripts.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;

    if (args.length > 2) {
      System.out.println("Unsupported command.");
      return;
    }

    if (args.length == 2) {
      if (args[0].equals("-file")) {
        executeScript(args[1], ap);
      } else {
        System.out.println("Unsupported command.");
      }
    } else if (args.length == 1) {
      handleSingleArgument(args[0], rd, ap);
    } else {
      launchGUI();
    }

  }

  /**
   * Handles the case where a single command-line argument is provided.
   *
   * @param arg the command-line argument
   * @param rd  the input source for reading commands
   * @param ap  the output destination for writing responses
   */
  private static void handleSingleArgument(String arg, Readable rd, Appendable ap) {
    if (arg.equals("-text")) {
      launchTextMode(rd, ap);
    } else {
      System.out.println("Unsupported command.");
    }
  }

  /**
   * Launches the text-based interface of the application.
   *
   * @param rd the input source for reading commands
   * @param ap the output destination for writing responses
   */
  private static void launchTextMode(Readable rd, Appendable ap) {
    new ImageProcessorCLI(rd, ap, new ImageOperationFactory()).run();
  }

  /**
   * Executes a script provided via a file path. The script is read and executed line by line.
   *
   * @param scriptPath the file path of the script to execute
   * @param ap         the output destination for writing responses
   */
  private static void executeScript(String scriptPath, Appendable ap) {
    String command = "run " + scriptPath + "\nexit";
    Readable readableInput = new StringReader(command);
    new ImageProcessorCLI(readableInput, ap, new ImageOperationFactory()).run();
    System.out.println("Script Completed.");
  }

  /**
   * Launches the GUI version of the application. This provides a graphical user interface for
   * editing images.
   */
  private static void launchGUI() {
    ImageEditorView imageEditorView = new ImageEditorFrame("Image Editor");
    GUIController guiController = new GUIController(
        imageEditorView,
        new GUIImageOperationFactory(imageEditorView)
    );
  }
}
