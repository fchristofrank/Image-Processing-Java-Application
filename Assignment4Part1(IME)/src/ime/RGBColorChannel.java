package ime;

/**
 * This class represents the RGB channel of a pixel.
 * It encapsulates the red, green, and blue color components and provides methods to
 * calculate the channel's value, intensity, and luma.
 */
public class RGBColorChannel extends AbstractColorChannel {

  /**
   * This method creates the RGB channel with the corresponding values to the components.
   *
   * @param red   the value of the red component.
   * @param green the value of the green component.
   * @param blue  the value of the blue component.
   */
  public RGBColorChannel(int red, int green, int blue) {
    super(red, green, blue);
  }

  @Override
  public int getChannelValue() {
    return Math.max(this.getRed(), Math.max(this.getGreen(), this.getBlue()));
  }

  @Override
  public float getChannelIntensity() {
    return (this.getRed() + this.getGreen() + this.getBlue()) / 3.0f;
  }

  @Override
  public double getChannelLuma() {
    return 0.2126 * this.getRed() + 0.7152 * this.getGreen() + 0.0722 * this.getBlue();
  }
}
