package ime.models;

public enum ImageType {
  RGB(1),
  ARGB(2);

  private final int imageType;

  ImageType(int imageType) {
    this.imageType = imageType;
  }

  public int getImageType() {
    return this.imageType;
  }
}