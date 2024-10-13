package ime;

/**
 * This abstract class represents a color channel, holding the values
 * for the red, green, and blue components of a pixel.
 * It serves as a base class for specific channel implementations
 * (such as RGBColorChannel and RGBAColorChannel) that define how color
 * channels are represented and accessed.
 */
public abstract class AbstractColorChannel implements Channel {
  private final int red;
  private final int green;
  private final int blue;

  public AbstractColorChannel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}
