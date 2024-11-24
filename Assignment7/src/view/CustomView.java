package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * CustomView is the GUI that users can use to visually interact with the application.
 */
public interface CustomView {

  /**
   * Set the listener for any actions.
   */
  void setActionListener(ActionListener listener);

  /**
   * Set the listener for any changes to the scroll bars.
   */
  void setChangeListener(ChangeListener listener);

  /**
   * Display this view.
   */
  void display();

  /**
   * Updates the current image that is presented on the GUI.
   */
  void updateGUIImage(BufferedImage image);

  /**
   * Updates the histogram of the currently displayed image.
   */
  void updateGUIHistogram(BufferedImage image);

  /**
   * Return the command that corresponds with the current radio button that is selected on the GUI.
   */
  String getSelectedRadioCommand();

  /**
   * Get the preview value to by used by the controller.
   */
  int getPreviewValue();

  /**
   * Allows the controller to update the view, changing the preview value displayed on the GUI.
   */
  void updatePreviewValue(int value);

  /**
   * Return the compression int value to be used by the controller.
   */

  int getCompressionValue();

  /**
   * Allows the controller to update the view, changing the compression value displayed on the GUI.
   */
  void updateCompressionValue(int value);

  /**
   * Return the black, mid, and white values to be used by the controller for levels-adjust
   * function. The controller needs to parse and validate that the input is correct.
   */
  List<String> getBMWValues();

  /**
   * Update the user feedback label in order to let the user know if the command was run
   * successfully or not.
   */
  void updateUserFeedback(String string);


}
