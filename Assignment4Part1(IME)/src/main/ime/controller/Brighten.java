package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class Brighten extends AdjustBrightness {
  public Brighten(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    validateArgs(args);
    super.execute(args);
  }
}
