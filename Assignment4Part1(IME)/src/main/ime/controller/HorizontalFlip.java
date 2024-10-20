package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.operations.ApplyHorizontalFlip;
import ime.operations.ImageOperation;

public class HorizontalFlip extends Flip {
  public HorizontalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    super.execute(args);
  }

  @Override
  public ImageOperation getOperation() {
    return new ApplyHorizontalFlip();
  }
}
