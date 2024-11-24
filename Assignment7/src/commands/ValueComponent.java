package commands;

import java.util.List;
import java.util.Objects;
import model.CustomModel;

/**
 * Command class that implements that implement the valueComponent Command used by the controller.
 */
public class ValueComponent implements Command {

  List<String> inputArray;
  int percentage;
  String maskArgument;

  /**
   * Public constructor class for the valueComponent command.
   */
  public ValueComponent(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() == 3) {
      percentage = 100;
    } else if (inputArray.size() == 4) {
      maskArgument = inputArray.get(3);
      percentage = 100;
    } else if (inputArray.size() == 5 && inputArray.get(3).equals("split")) {
      int argument = Integer.parseInt(inputArray.get(4));
      if (argument < 0 || argument > 100) {
        throw new IllegalArgumentException("Percentage argument should be between 0 and 100");
      } else {
        percentage = argument;
      }
    } else {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    }
  }

  @Override
  public void run(CustomModel model) {
    model.createValueImage(inputArray.get(1), inputArray.get(2), percentage);
    if (!Objects.equals(maskArgument, "")) {
      model.applyMask(inputArray.get(2), inputArray.get(1), maskArgument);
    }
  }
}
