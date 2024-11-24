package image;

import java.util.List;

/**
 * Creates an interface that encapsulates all behaviours of gathering / altering pixel data. The
 * pixel data is then represented in a List object and can be set if required.
 */
public interface CustomPixel {

  /**
   * Retrieves an individual pixels RBG values.
   *
   * @return a list of RGB integer values of length 3
   */
  List<Integer> getPixelRGB();

  /**
   * Retrieves an individual pixels RBGA values.
   *
   * @return a list of RGB integer values of length 4
   */
  List<Integer> getPixelRGBA();


  /**
   * Sets the values of an individual RGBA pixel.
   *
   * @param rgbaPixelList rgba pixel list passed to method as parameter
   * @throws IllegalArgumentException if the rgbaPixelList does not contain 4 values
   */
  void setPixelRGBA(List<Integer> rgbaPixelList) throws IllegalArgumentException;

  /**
   * Calculates the value greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after value conversion.
   */
  CustomPixel getValuePixel();

  /**
   * Calculates the intensity greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after intensity conversion.
   */
  CustomPixel getIntensityPixel();

  /**
   * Calculates the luma greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after luma conversion.
   */
  CustomPixel getLumaPixel();

  /**
   * Calculates the red greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after red conversion.
   */
  CustomPixel getRedPixel();

  /**
   * Calculates the green greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after green conversion.
   */
  CustomPixel getGreenPixel();

  /**
   * Calculates the blue greyscale component for this pixel.
   *
   * @return new CustomPixel object which represents the pixel after blue conversion.
   */
  CustomPixel getBluePixel();
}
