package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.Pixel;

public class VisualizeGreen extends AbstractVisualize {

  public VisualizeGreen(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getGreen();
  }
}
