package ime.model.operation;

import ime.model.pixel.Pixel;

public class VisualizeLuma extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getLuma();
  }
}
