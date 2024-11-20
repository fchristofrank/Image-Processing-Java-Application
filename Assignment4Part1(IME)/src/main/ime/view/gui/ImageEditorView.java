package ime.view.gui;

import ime.controller.gui.Features;
import java.awt.image.BufferedImage;

/**
 * This interface represents the view component of an image editor application. It provides
 * methods to interact with the graphical user interface (GUI), enabling the display of images,
 * histograms, error messages, and other view-related functionalities. The view is also responsible
 * for registering the features that enable user interaction.
 */
public interface ImageEditorView {

  /**
   * Registers the features to be used in the image editor. These features define
   * the operations that users can perform within the application.
   *
   * @param features the {@link Features} object containing all the functionalities
   *                 to be used in the application.
   */
  void addFeatures(Features features);

  /**
   * Sets and displays the given image on the view.
   *
   * @param image the {@link BufferedImage} to be displayed in the image editor.
   */
  void setImage(BufferedImage image);

  /**
   * Sets and displays the given histogram on the view.
   *
   * @param histogram the {@link BufferedImage} representing the histogram to be displayed.
   */
  void setHistogram(BufferedImage histogram);

  /**
   * Displays an error message dialog with the given message and title.
   *
   * @param message the error message to be displayed.
   * @param title   the title of the error dialog.
   */
  void showErrorMessageDialog(String message, String title);

  /**
   * Displays a warning message to the user before loading an image from the specified path.
   *
   * @param imagePath the path of the image that is about to be loaded.
   */
  void showWarningMessageBeforeLoading(String imagePath);

  /**
   * Resets the view to its initial state, clearing any displayed images, histograms,
   * or other data.
   */
  void cleanSlate();

}
