package ime.operations;

import ime.models.Pixel;

public class VisualizeGreen extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getGreen();
  }
}
