package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class Darken extends AdjustBrightness{
  public Darken(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);
    int darkenValue = Integer.parseInt(args[0]);
    args[0] = String.valueOf(-Math.abs(darkenValue));
    super.execute(args);
  }
}
