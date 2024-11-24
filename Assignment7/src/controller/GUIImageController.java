package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.CustomModel;
import util.Constants;
import view.CustomView;

/**
 * GUIImageController is the custom controller that is used for the GUI-based view. The main purpose
 * of this is just to simply get the argument from an ActionEvent, and pass it to the parent
 * ImageController class.
 */
public class GUIImageController extends ImageController implements ActionListener, ChangeListener {

  CustomView view;

  // Helper function used to get a file from the GUI View
  private File getFileLoadPath() {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("", "ppm", "png", "jpg");
    fileChooser.setFileFilter(filter);
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }
    return null;
  }

  // Helper function used to save a bufferedImage into a file
  private void getFileSavePath() {
    final JFileChooser fChooser = new JFileChooser(".");
    int res = fChooser.showSaveDialog(null);
    try {
      if (res == JFileChooser.APPROVE_OPTION) {
        // Get the image and turn it into a bufferedImage
        File f = fChooser.getSelectedFile();
        saveFunction(f.getPath(), "image");
      }
    } catch (IOException e) {
      System.out.println("Error occurred when trying to save file");
    }

  }

  // Private function that is responsible for passing in the new/updated image
  // and histogram to be displayed by the view
  private void updateViewImage(String imageName) throws IOException {
    // Grab the BufferedImage image and histogram from the model
    BufferedImage image = pixelToImage(imageModel.getImage(imageName));
    createHistogram(imageModel.getImageValues(imageName), "imageHistogram");
    BufferedImage imageHistogram = pixelToImage(imageModel.getImage("imageHistogram"));

    // Update the view
    view.updateGUIImage(image);
    view.updateGUIHistogram(imageHistogram);
  }

  // Private function that checks that the arguments used for levels adjustment are valid.
  private List<Integer> validateLevelsAdjust() {
    List<String> bmwValues = view.getBMWValues();
    try {
      int black = Integer.parseInt(bmwValues.get(0));
      int mid = Integer.parseInt(bmwValues.get(1));
      int white = Integer.parseInt(bmwValues.get(2));
      if (black < mid && mid < white) {
        return List.of(black, mid, white);
      } else {
        return null;
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: One of the values is not a valid integer.");
      return null;
    }
  }

  // Private function that is called whenever there is a new command that wants to be applied,
  // imageName refers to the image that is to be modified on the backend,
  // and should be only either image or preview
  private void updateImage(String imageName) {
    // If we don't have an image stored, just return
    if (imageModel.getImage("image") == null) {
      return;
    }
    try {
      String command = view.getSelectedRadioCommand();
      int split = (Objects.equals(imageName, "image")) ? 100 : view.getPreviewValue();
      if (command != null) {
        switch (command) {
          case Constants.HORIZONTAL_FLIP_STR:
            command += " image " + imageName;
            break;
          case Constants.VERTICAL_FLIP_STR:
            command += " image " + imageName;
            break;
          case Constants.COMPRESS_STR:
            command += " " + view.getCompressionValue() + " image " + imageName;
            break;
          case Constants.LEVELS_ADJUST_STR:
            List<Integer> bmwValues = validateLevelsAdjust();
            if (bmwValues != null) {
              command += " " + bmwValues.get(0) + " " + bmwValues.get(1) + " " + bmwValues.get(2) +
                  " image " + imageName + " split " + split;
            }
            break;
          default:
            command += " image " + imageName + " split " + split;
            break;
        }
        // Run the command and refresh the view
        int statusCode = parse(command);
        view.updateUserFeedback(printStatus(statusCode));
        updateViewImage(imageName);
      }
    } catch (IOException e) {
      System.out.println("Error occurred when trying to reload image");
    }
  }

  /**
   * Public constructor for the controller class which takes in a View which implements the
   * CustomView interface and a imageModel which implements the ImageModel interface.
   *
   * @param imageModel the Model that the Controller is currently bound
   */
  public GUIImageController(CustomModel imageModel, CustomView view) {
    super(imageModel, null, null);
    this.view = view;
    view.setActionListener(this);
    view.setChangeListener(this);
    view.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String command = e.getActionCommand();
      // If we are trying to load an object, we need to get the file path first
      switch (command) {
        case Constants.LOAD_STR:
          File f = getFileLoadPath();
          // If File Exists modify our existing command so the parse function can work correctly
          if (f != null) {
            loadFunction(f.getPath(), "image");
            updateViewImage("image");
          }
          break;
        // If we are trying to save an object, checks to see if we have an existing image
        case Constants.SAVE_STR:
          if (imageModel.getImage("image") != null) {
            getFileSavePath();
          }
          break;
        // If we are actually trying to modify an object, which only occurs when the apply
        // button is hit and an image exists in the view
        case Constants.APPLY:
          updateImage("image");
          break;
        // We are just previewing the object transformation, which occurs whenever we switch
        // between transformations without hitting the apply button and an image exists in the view
        default:
          updateImage("preview");
      }
    } catch (IOException error) {
      view.updateUserFeedback("Error occurred when performing " + e.getActionCommand());
    }
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    updateImage("preview");
    view.updateCompressionValue(view.getCompressionValue());
    view.updatePreviewValue(view.getPreviewValue());
  }
}
