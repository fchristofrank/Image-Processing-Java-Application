package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the ColorCorrect Command used by the controller.
 */
public class ColorCorrect implements Command {

  int percentage;
  List<String> inputArray;

  /**
   * Public constructor class for the ColorCorrect command.
   */
  public ColorCorrect(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() == 3) {
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
    model.colorCorrection(inputArray.get(1), inputArray.get(2), percentage);
  }
}
