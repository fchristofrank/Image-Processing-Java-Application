package ime.controller.operation;

import ime.model.image.ImageLibrary;
import ime.model.operation.ApplyHorizontalFlip;
import ime.model.operation.ImageOperation;

public class HorizontalFlip extends Flip {
  public HorizontalFlip(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IllegalArgumentException {
    super.execute(args);
  }

  @Override
  public ImageOperation getOperation() {
    return new ApplyHorizontalFlip();
  }
}
