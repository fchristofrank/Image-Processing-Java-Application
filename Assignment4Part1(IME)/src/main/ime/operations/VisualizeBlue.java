package ime.operations;

import ime.models.Pixel;

public class VisualizeBlue extends AbstractVisualize {


  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getBlue();
  }
}
