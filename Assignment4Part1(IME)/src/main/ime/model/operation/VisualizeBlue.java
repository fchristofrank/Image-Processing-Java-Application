package ime.model.operation;

import ime.model.pixel.Pixel;

public class VisualizeBlue extends AbstractVisualize {


  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getBlue();
  }
}
