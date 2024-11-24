package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the LevelsAdjusts Command used by the controller.
 */
public class LevelsAdjust implements Command {

  int b;
  int m;
  int w;
  int percentage;
  List<String> inputArray;

  /**
   * Public constructor class for the LevelsAdjusts command.
   */
  public LevelsAdjust(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() == 6) {
      int b = Integer.parseInt(inputArray.get(1));
      int m = Integer.parseInt(inputArray.get(2));
      int w = Integer.parseInt(inputArray.get(3));
      if (b < m && m < w) {
        this.b = b;
        this.m = m;
        this.w = w;
        this.percentage = 100;
      } else {
        throw new IllegalArgumentException("Invalid arguments passed");
      }
    } else if (inputArray.size() == 8 && inputArray.get(6).equals("split")) {
      int argument = Integer.parseInt(inputArray.get(7));
      if (argument < 0 || argument > 100) {
        throw new IllegalArgumentException("Percentage argument should be between 0 and 100");
      } else {
        percentage = argument;
      }
      int b = Integer.parseInt(inputArray.get(1));
      int m = Integer.parseInt(inputArray.get(2));
      int w = Integer.parseInt(inputArray.get(3));
      if (b < m && m < w) {
        this.b = b;
        this.m = m;
        this.w = w;
      } else {
        throw new IllegalArgumentException("Invalid arguments passed");
      }
    } else {
      throw new IllegalArgumentException("Invalid arguments passed");
    }
  }

  @Override
  public void run(CustomModel model) {
    model.levelsAdjustImage(b, m, w, inputArray.get(4), inputArray.get(5), percentage);
  }
}
