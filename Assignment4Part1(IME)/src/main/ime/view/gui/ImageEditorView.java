package ime.view.gui;

import ime.controller.gui.Features;
import java.awt.image.BufferedImage;

public interface ImageEditorView {

  void addFeatures(Features features);

  void setImage(BufferedImage image);

  void setHistogram(BufferedImage histogram);

  void showErrorMessageDialog(String message, String title);

  void showWarningMessageBeforeLoading(String imagePath);

  void cleanSlate();

}
