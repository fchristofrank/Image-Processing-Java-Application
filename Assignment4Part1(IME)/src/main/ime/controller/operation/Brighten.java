package ime.controller.operation;

import ime.model.image.ImageLibrary;

public class Brighten extends AdjustBrightness {
  public Brighten(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    validateArgs(args);
    super.execute(args);
  }
}
