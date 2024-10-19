package ime.operations;

import ime.models.Pixel;

public class VisualizeLuma extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    return (int) pixel.getLuma();
  }
}
