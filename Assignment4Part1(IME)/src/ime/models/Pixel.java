package ime.models;

/**
 * This interface represents a pixel in an image, encapsulating the color components of a pixel,
 * such as red, green, blue, and potentially additional components like alpha or others.
 * Each implementing class should provide functionality to retrieve the values of these components
 * and perform calculations related to color representation.
 */
public interface Pixel {
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
     * This method gets the color component as a single value.
     * This method combines the channel values into a single integer value.
     *
     * @return the method returns the color component as a single value.
     */
    int getColorComponents();

    /**
     * This method gets the value of the pixel.
     *
     * @return the value of the channel.
     */
    int getValue();

    /**
     * This method gets the intensity of the pixel.
     *
     * @return the intensity of the channel.
     */
    float getIntensity();

    /**
     * This method gets the luma of the pixel.
     *
     * @return the luma of the channel.
     */
    double getLuma();
}
