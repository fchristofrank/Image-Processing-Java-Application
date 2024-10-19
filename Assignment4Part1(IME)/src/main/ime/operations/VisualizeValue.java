package ime.operations;

import ime.models.Pixel;

public class VisualizeValue extends AbstractVisualize {

  @Override
  protected int getColorComponent(Pixel pixel) {
    System.out.println("Value");
    return pixel.getValue();
  }
}