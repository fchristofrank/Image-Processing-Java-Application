package ime.models;

/**
 * This abstract class represents a pixel, holding the values for the red, green, and blue
 * components of a pixel. It serves as a base class for specific pixel implementations
 * (such as RGBPixel and RGBAPixel) that define how color pixels are represented and accessed.
 */
public abstract class AbstractPixel implements Pixel {

  protected int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

}
