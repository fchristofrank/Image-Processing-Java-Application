package ime.model.pixel;

import java.util.Objects;

/**
 * This class represents the RGB components of a pixel.
 * It encapsulates the red, green, and blue color components and provides methods to
 * calculate the pixel's value, intensity, and luma.
 */
public class RGBPixel extends AbstractPixel {
  private static final int TOLERANCE = 4;
  private final int red;
  private final int green;
  private final int blue;

  /**
   * This method creates the RGB channel with the corresponding values to the components.
   *
   * @param red   the value of the red component.
   * @param green the value of the green component.
   * @param blue  the value of the blue component.
   */
  public RGBPixel(int red, int green, int blue) {
    super();
    this.red = clamp(red);
    this.green = clamp(green);
    this.blue = clamp(blue);
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

  @Override
  public Pixel shiftComponents(int alpha) {
    return new RGBPixel(this.getRed() + alpha, this.getGreen() + alpha,
            this.getBlue() + alpha);
  }

  @Override
  public Pixel scaleComponents(double[][] factors) {
    double red = factors[0][0] * this.red + factors[0][1] * this.green + factors[0][2] * this.blue;
    double green = factors[1][0] * this.red + factors[1][1] * this.green
            + factors[1][2] * this.blue;
    double blue = factors[2][0] * this.red + factors[2][1] * this.green + factors[2][2] * this.blue;
    return new RGBPixel((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RGBPixel rgbPixel = (RGBPixel) obj;
    return Math.abs(this.getRed() - rgbPixel.getRed()) <= TOLERANCE &&
            Math.abs(this.getGreen() - rgbPixel.getGreen()) <= TOLERANCE &&
            Math.abs(this.getBlue() - rgbPixel.getBlue()) <= TOLERANCE;
  }

  @Override
  public int hashCode() {
    return Objects.hash((getRed() / TOLERANCE) * TOLERANCE,
            (getGreen() / TOLERANCE) * TOLERANCE, (getBlue() / TOLERANCE) * TOLERANCE);
  }

  @Override
  public int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }
}
