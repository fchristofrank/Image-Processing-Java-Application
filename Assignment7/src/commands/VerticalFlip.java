package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the VerticalFlip Command used by the controller.
 */
public class VerticalFlip implements Command {

  List<String> inputArray;

  /**
   * Public constructor class for the VerticalFlip command.
   */
  public VerticalFlip(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() != 3) {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    }
  }

  @Override
  public void run(CustomModel model) {
    model.createVerticallyImage(inputArray.get(1), inputArray.get(2));
  }
}
