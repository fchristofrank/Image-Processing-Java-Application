package ime.model.operation;

import ime.model.pixel.Pixel;

public class VisualizeGreen extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getGreen();
  }
}
