package ime.operations;

import ime.imageIO.ImageLibrary;
import ime.models.ImageType;
import ime.models.Pixel;
import ime.models.PixelFactory;
import ime.models.RGBPixel;

public class VisualizeRed extends AbstractVisualize {

  public VisualizeRed(ImageLibrary imageLibrary) {
    super(imageLibrary);
  }

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getRed();
  }
}