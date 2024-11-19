// TutorialDialog.java
package ime.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialDialog extends JDialog {
  private int currentStep = 0;
  private final String[] tutorialSteps = {
          "<html><b>Welcome to the Image Editor!</b><br><br>" +
                  "This tutorial will guide you through the basic features of the application.<br>" +
                  "Click 'Next' to continue or 'Skip' to exit the tutorial.</html>",

          "<html><b>Loading and Saving Images</b><br><br>" +
                  "• Use File → Load to open an image<br>" +
                  "• Use File → Save to save your edited image<br>" +
                  "• Supported formats: JPG, PNG, PPM</html>",

          "<html><b>Basic Image Operations</b><br><br>" +
                  "• Use the buttons on the left panel for basic operations:<br>" +
                  "• Flip images horizontally or vertically<br>" +
                  "• Apply filters like Blur, Sharpen<br>" +
                  "• Convert to Sepia or Greyscale<br>" +
                  "• Extract color components (Red, Green, Blue)</html>",

          "<html><b>Preview Mode</b><br><br>" +
                  "• Enable Preview Mode to see changes before applying<br>" +
                  "• Use the slider to adjust the split view<br>" +
                  "• Toggle the preview on/off<br>" +
                  "• Click 'Apply' to make changes permanent</html>",

          "<html><b>Advanced Features</b><br><br>" +
                  "• Compress images by specifying compression percentage<br>" +
                  "• Adjust levels using Black, Middle, and White points<br>" +
                  "• View histogram for color distribution<br>" +
                  "• Undo/Redo changes using Edit menu or shortcuts</html>",

          "<html><b>Ready to Start!</b><br><br>" +
                  "You're now ready to use the Image Editor.<br><br>" +
                  "Click 'Finish' to start editing!</html>"
  };

  public TutorialDialog(JFrame parent) {
    super(parent, "Tutorial", true);
    setupDialog();
  }

  private void setupDialog() {
    setSize(400, 300);
    setLocationRelativeTo(getParent());
    setResizable(false);

    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Content panel
    JPanel contentPanel = new JPanel(new BorderLayout());
    JLabel contentLabel = new JLabel(tutorialSteps[currentStep]);
    contentPanel.add(contentLabel, BorderLayout.CENTER);
    mainPanel.add(contentPanel, BorderLayout.CENTER);

    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton skipButton = new JButton("Skip");
    JButton prevButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");
    prevButton.setEnabled(false);

    // Add action listeners
    skipButton.addActionListener(e -> dispose());

    prevButton.addActionListener(e -> {
      currentStep--;
      contentLabel.setText(tutorialSteps[currentStep]);
      updateButtonStates(prevButton, nextButton);
    });

    nextButton.addActionListener(e -> {
      if (currentStep == tutorialSteps.length - 1) {
        dispose(); // Close dialog when "Finish" is clicked
      } else {
        currentStep++;
        contentLabel.setText(tutorialSteps[currentStep]);
        updateButtonStates(prevButton, nextButton);
      }
    });

    buttonPanel.add(skipButton);
    buttonPanel.add(prevButton);
    buttonPanel.add(nextButton);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);
  }

  private void updateButtonStates(JButton prevButton, JButton nextButton) {
    prevButton.setEnabled(currentStep > 0);
    nextButton.setText(currentStep == tutorialSteps.length - 1 ? "Finish" : "Next");
  }
}