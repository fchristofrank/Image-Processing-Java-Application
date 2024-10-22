package ime.controller.operation;

import ime.model.image.ImageLibrary;

public class Darken extends AdjustBrightness {
  public Darken(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    int darkenValue = Integer.parseInt(args[0]);
    args[0] = String.valueOf(-Math.abs(darkenValue));
    super.execute(args);
  }
}
