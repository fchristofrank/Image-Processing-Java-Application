package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the RGBCombine Command used by the controller.
 */
public class RGBCombine implements Command {

  List<String> inputArray;

  /**
   * Public constructor class for the RGBCombine command.
   */
  public RGBCombine(List<String> inputArray) {
    if (inputArray.size() != 5) {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    }
    this.inputArray = inputArray;
  }

  @Override
  public void run(CustomModel model) {
    model.combineImageByRGB(inputArray.get(1), inputArray.get(2), inputArray.get(3),
        inputArray.get(4));
  }
}
