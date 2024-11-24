package commands;

import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the Compress Command used by the controller.
 */
public class Compress implements Command {

  List<String> inputArray;
  int percentage;

  /**
   * Public constructor class for the Compress command.
   */
  public Compress(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() != 4) {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    } else {
      int compressRatio = Integer.parseInt(inputArray.get(1));
      if (compressRatio >= 0 && compressRatio <= 100) {
        percentage = compressRatio;
      } else {
        throw new IllegalArgumentException("Compression ratio must be between 0 and 100");
      }
    }
  }

  @Override
  public void run(CustomModel model) {
    model.createCompressImage(percentage, inputArray.get(2), inputArray.get(3));
  }
}
