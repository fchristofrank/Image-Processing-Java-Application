package ime.controller;

public interface Features {
  void loadImage(String imagePath);

  void flipImage(String flipType);

  void applyFilter(boolean isPreview, String filterType, String splitWidth);

  void applyGreyScale(boolean isPreview, String grayScaleType, String splitWidth);

  void applyColorCorrect(boolean isPreview, String splitWidth);

  void applyCompress(String compressionRatio);

  void adjustLevels(boolean isPreview, String... args);

  void saveImage(String imagePath);

  void undo();

  void redo();

  void togglePreview(String splitWidth);

  void exitPreviewMode(boolean isEnabled);

}
