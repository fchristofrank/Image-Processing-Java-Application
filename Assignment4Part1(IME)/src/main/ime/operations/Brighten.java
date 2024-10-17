package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class Brighten extends AdjustBrightness {
  public Brighten(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    validateArgs(args);
    super.apply(args);
  }
}
