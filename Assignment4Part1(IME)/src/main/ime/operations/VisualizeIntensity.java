package ime.operations;

import ime.models.Pixel;

public class VisualizeIntensity extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getIntensity();
  }
}
