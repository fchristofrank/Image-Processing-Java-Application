package ime.controller.operation;

import ime.controller.operation.repository.ImageRepo;
import ime.model.operation.ApplySepiaWithPreview;
import ime.model.operation.Blur;
import ime.model.operation.ImageOperation;
import ime.model.operation.Sharpen;

public class FilterWithPreview extends Filter {
  public FilterWithPreview(ImageRepo library, String command) {
    super(library, command);
  }

  @Override
  protected ImageOperation filterObjectFactory(String command) {

    switch (command) {
      case "blur":
        return new Blur();
      case "sharpen":
        return new Sharpen();
      case "sepia":
        return new ApplySepiaWithPreview();
      default:
        throw new UnsupportedOperationException("Unknown command given.");
    }
  }

  @Override
  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length < 2 || args.length > 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }

    if (args.length == 3) {
      String splitPercentage = args[2];
      try {
        int percentage = Integer.parseInt(splitPercentage);
        if (percentage < 0 || percentage > 100) {
          throw new IllegalArgumentException("Percentage value for split line must be between "
                  + "0 and 100.");
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Percentage value for split line must be a number.");
      }
    }
  }
}
