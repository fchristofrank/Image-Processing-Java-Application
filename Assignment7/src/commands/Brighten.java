package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the Brighten Command used by the controller.
 */
public class Brighten implements Command {

  List<String> inputArray;

  /**
   * Public constructor class for the Brighten command.
   */
  public Brighten(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() != 4) {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    }
  }

  @Override
  public void run(CustomModel model) {
    model.brightenImage(Integer.parseInt(inputArray.get(1)), inputArray.get(2),
        inputArray.get(3));
  }
}
