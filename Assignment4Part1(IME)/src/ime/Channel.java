package ime;

/**
 * This interface represents a color channel in an image, encapsulating
 * the color components of a pixel, such as red, green, blue, and
 * potentially additional components like alpha or others.
 * Each implementing class should provide functionality to retrieve
 * the values of these components and perform calculations related
 * to color representation.
 */
public interface Channel {
  /**
   * This method gets the value of the red component.
   *
   * @return the value of the red component.
   */
  int getRed();

  /**
   * This method gets the value of the green component.
   *
   * @return the value of the green component.
   */
  int getGreen();

  /**
   * This method gets the value of the blue component.
   *
   * @return the value of the blue component.
   */
  int getBlue();

  /**
   * This method gets the value of the channel.
   *
   * @return the value of the channel.
   */
  int getChannelValue();

  /**
   * This method gets the intensity of the channel.
   *
   * @return the intensity of the channel.
   */
  float getChannelIntensity();

  /**
   * This method gets the luma of the channel.
   *
   * @return the luma of the channel.
   */
  double getChannelLuma();
}
