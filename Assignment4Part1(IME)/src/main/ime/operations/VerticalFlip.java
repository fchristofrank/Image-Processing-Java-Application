package ime.operations;

import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class VerticalFlip extends ImageOperationManager {
  public VerticalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void apply(String[] args) throws IllegalArgumentException, IOException {
    validateArgs(args);
  }
}
