import controller.GUIImageController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import controller.ImageController;
import java.util.Objects;
import model.ImageModel;
import view.CustomView;
import view.JFrameView;

/**
 * Class for executing the application.
 */
public class App {

  static ImageController controller;
  static ImageModel model;


  /**
   * Method for executing the application.
   *
   * @param args arguments passed in by user
   * @throws IOException in the event of unforeseen file issues
   */
  public static void main(String[] args) throws IOException {
    // Get the correct type of model
    model = new ImageModel();

    // Gets the correct type of view
    // File-based View
    if (args.length == 2 && Objects.equals(args[0], "-file")) {
      try {
        controller = new ImageController(model, new FileInputStream(args[1]), System.out);
      } catch (IllegalArgumentException e) {
        System.out.println(args[1] + " is not a valid file");
        return;
      } catch (FileNotFoundException e) {
        System.out.println(args[1] + " does not exist");
        return;
      }
      // Start the controller
      controller.start();
    }
    // Shell-based View
    else if (args.length == 1 && Objects.equals(args[0], "-text")) {
      controller = new ImageController(model, System.in, System.out);
      // Start the controller
      controller.start();
    } else {
      // GUI-based View
      CustomView gui = new JFrameView(null, null);
      controller = new GUIImageController(model, gui);
    }
  }
}
