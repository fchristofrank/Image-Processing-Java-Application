package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class Darken extends AdjustBrightness {
  public Darken(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    validateArgs(args);
    int darkenValue = Integer.parseInt(args[0]);
    args[0] = String.valueOf(-Math.abs(darkenValue));
    super.apply(args);
  }
}
