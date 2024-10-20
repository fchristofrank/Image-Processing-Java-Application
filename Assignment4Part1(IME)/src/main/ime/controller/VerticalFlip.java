package ime.controller;

import java.io.IOException;

import ime.imageIO.ImageLibrary;
import ime.operations.ApplyVerticalFlip;
import ime.operations.ImageOperation;

public class VerticalFlip extends Flip {

  public VerticalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    super.execute(args);
  }

  @Override
  public ImageOperation getOperation() {
    return new ApplyVerticalFlip();
  }
}
