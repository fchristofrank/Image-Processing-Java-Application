package ime.models;

/**
 * This class represents the RGB components of a pixel.
 * It encapsulates the red, green, and blue color components and provides methods to
 * calculate the pixel's value, intensity, and luma.
 */
public class RGBPixel extends AbstractPixel {

  /**
   * This method creates the RGB channel with the corresponding values to the components.
   *
   * @param red   the value of the red component.
   * @param green the value of the green component.
   * @param blue  the value of the blue component.
   */
  public RGBPixel(int red, int green, int blue) {
    super(red, green, blue);
  }


  @Override
  public int getColorComponents() {
    return (this.getRed() << 16) | (this.getGreen() << 8) | this.getBlue();
  }

  @Override
  public int getValue() {
    return Math.max(this.getRed(), Math.max(this.getGreen(), this.getBlue()));
  }

  @Override
  public float getIntensity() {
    return (this.getRed() + this.getGreen() + this.getBlue()) / 3.0f;
  }

  @Override
  public double getLuma() {
    return 0.2126 * this.getRed() + 0.7152 * this.getGreen() + 0.0722 * this.getBlue();
  }
}
