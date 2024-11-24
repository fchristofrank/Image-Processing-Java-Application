package image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * This class represents a BasicPixel object, and offers all operations listed in the CustomPixel
 * interface. This class represents the pixel data in a list structure.
 */
public class BasicPixel implements CustomPixel {

  private List<Integer> pixelList;

  /**
   * Initializes a BasicPixel object with a single black pixel with full opacity (255).
   */
  public BasicPixel() {
    this(0, 0, 0, 255);
  }

  /**
   * Initializes a BasicPixel object with input RGB values with full opacity (255).
   *
   * @param red   integer value of red (0-255)
   * @param green integer value of green (0-255)
   * @param blue  integer value of blue (0-255)
   */
  public BasicPixel(int red, int green, int blue) {
    this(red, green, blue, 255);
  }

  /**
   * Initializes a BasicPixel object, initializing a List of integers representing RGBA values.
   *
   * @param red   integer value of red (0-255)
   * @param green integer value of green (0-255)
   * @param blue  integer value of blue (0-255)
   * @param alpha integer value of alpha (opacity) (0-255)
   */
  public BasicPixel(int red, int green, int blue, int alpha) {
    this.pixelList = new ArrayList<>(Arrays.asList(red, green, blue, alpha));
  }

  /**
   * Public constructor for initializing a BasicPixel object.
   *
   * @param pixelArray array containing individual pixel data
   */
  public BasicPixel(List<Integer> pixelArray) {
    if (pixelArray.size() == 3) {
      pixelArray.add(255);
    }
    if (pixelArray.size() == 4) {
      this.pixelList = pixelArray;
    }
  }

  @Override
  public List<Integer> getPixelRGB() {
    return List.of(this.pixelList.get(0), this.pixelList.get(1), this.pixelList.get(2));
  }

  @Override
  public List<Integer> getPixelRGBA() {
    return new ArrayList<>(
        List.of(this.pixelList.get(0), this.pixelList.get(1), this.pixelList.get(2),
            this.pixelList.get(3)));
  }

  @Override
  public void setPixelRGBA(List<Integer> rgbaPixelList) throws IllegalArgumentException {
    if (rgbaPixelList.size() == 3) {
      rgbaPixelList.add(255);
    }
    if (rgbaPixelList.size() == 4) {
      this.pixelList.clear();
      this.pixelList.addAll(rgbaPixelList);
    } else {
      throw new IllegalArgumentException("RGBA pixel must have exactly 3 or 4 values.");
    }
  }

  @Override
  public CustomPixel getValuePixel() {
    int valueInt = Collections.max(this.pixelList.subList(0, 3));
    return new BasicPixel(valueInt, valueInt, valueInt);
  }

  @Override
  public CustomPixel getIntensityPixel() {
    int intensity = (this.pixelList.get(0) + this.pixelList.get(1) + this.pixelList.get(2)) / 3;
    return new BasicPixel(intensity, intensity, intensity);
  }

  @Override
  public CustomPixel getLumaPixel() {
    int lumaInt = (int) Math.round(
        this.pixelList.get(0) * 0.2126 + this.pixelList.get(1) * .7152
            + this.pixelList.get(2) * 0.0722);
    return new BasicPixel(lumaInt, lumaInt, lumaInt);
  }

  @Override
  public CustomPixel getRedPixel() {
    int red = this.getPixelRGBA().get(0);
    return new BasicPixel(red, red, red);
  }

  @Override
  public CustomPixel getGreenPixel() {
    int green = this.getPixelRGBA().get(1);
    return new BasicPixel(green, green, green);
  }

  @Override
  public CustomPixel getBluePixel() {
    int blue = this.getPixelRGBA().get(2);
    return new BasicPixel(blue, blue, blue);
  }
}
