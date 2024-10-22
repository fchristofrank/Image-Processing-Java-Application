package ime.model.operation;

import ime.model.pixel.Pixel;

public class VisualizeRed extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return pixel.getRed();
  }
}