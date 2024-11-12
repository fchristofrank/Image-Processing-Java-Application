package ime.view;

import java.awt.image.BufferedImage;

import ime.controller.Features;

public interface ImageEditorView {
  void addFeatures(Features features);

  void setImage(BufferedImage image);

  void setHistogram(BufferedImage histogram);
}
