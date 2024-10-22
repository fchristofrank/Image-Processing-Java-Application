package ime.controller.operation;

import ime.model.image.ImageLibrary;
import ime.model.operation.ApplyVerticalFlip;
import ime.model.operation.ImageOperation;

public class VerticalFlip extends Flip {

  public VerticalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    super.execute(args);
  }

  @Override
  public ImageOperation getOperation() {
    return new ApplyVerticalFlip();
  }
}
