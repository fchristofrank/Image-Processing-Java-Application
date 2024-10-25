package ime.controller.imageio;

/**
 * Enum representing supported image file formats in the image editor application.
 * This enum provides a type-safe way to handle different image formats.
 */
public enum ImageFormat {
  /**
   * Represents the PPM (Portable Pixmap) image format.
   */
  PPM("ppm"),

  /**
   * Represents the PNG (Portable Network Graphics) image format.
   */
  PNG("png"),

  /**
   * Represents the JPG (Joint Photographic Experts Group) image format.
   */
  JPG("jpg");

  /**
   * The string representation of the image format.
   */
  private final String imageFormat;

  /**
   * Constructs an ImageFormat enum constant with the specified string representation.
   *
   * @param imageFormat The string representation of the image format.
   */
  ImageFormat(String imageFormat) {
    this.imageFormat = imageFormat;
  }

  /**
   * Returns the string representation of the image format.
   *
   * @return The string representation of the image format.
   */
  public String getImageFormat() {
    return this.imageFormat;
  }
}