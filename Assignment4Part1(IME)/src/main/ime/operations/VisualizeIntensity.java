package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;

public class VisualizeIntensity extends AbstractVisualize {

  public VisualizeIntensity(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getIntensity();
  }
}
