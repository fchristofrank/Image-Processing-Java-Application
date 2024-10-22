package ime.model.image;

/**
 * Represents the different types of images supported by the image processing application.
 */
public enum ImageType {
  /**
   * Represents an RGB image type.
   * This type of image has three channels: Red, Green, and Blue.
   */
  RGB(1),
  /**
   * Represents an ARGB image type.
   * This type of image has four channels: Alpha (transparency), Red, Green, and Blue.
   */
  ARGB(2);

  private final int imageType;

  /**
   * Constructs an ImageType enum constant with the specified integer representation.
   *
   * @param imageType the integer representation of the image type
   */
  ImageType(int imageType) {
    this.imageType = imageType;
  }

  /**
   * Returns the integer representation of the image type.
   *
   * @return the integer value associated with this image type
   */
  public int getImageType() {
    return this.imageType;
  }
}