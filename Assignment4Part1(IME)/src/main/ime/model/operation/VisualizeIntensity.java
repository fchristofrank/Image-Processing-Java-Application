package ime.model.operation;

import ime.model.pixel.Pixel;

public class VisualizeIntensity extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getIntensity();
  }
}
