package ime.imageIO;

public enum ImageFormat {
  PPM("ppm"),
  PNG("png"),
  JPG("jpg");

  private final String imageFormat;

  ImageFormat(String imageFormat) {
    this.imageFormat = imageFormat;
  }

  public String getImageFormat() {
    return this.imageFormat;
  }
}
