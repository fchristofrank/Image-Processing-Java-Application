package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;

public class VisualizeValue extends AbstractVisualize {

  public VisualizeValue(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getValue();
  }
}