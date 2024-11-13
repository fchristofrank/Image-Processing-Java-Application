package ime.controller;

public interface Features {
  void loadImage(String imagePath);

  void flipImage(String flipType);

  void applyFilter(String filterType, String splitWidth);

  void applyGreyScale(String grayScaleType);

  void saveImage(String imagePath);

  void undo();

  void redo();
}
