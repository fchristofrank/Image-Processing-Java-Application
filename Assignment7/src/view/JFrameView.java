package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import util.Constants;

/**
 * JFrameView is the actual implementation of the CustomView, it creates a visual application that
 * is able to modify a single image at a time.
 */
public class JFrameView extends JFrame implements CustomView {

  private JLabel imageLabel;
  private JLabel histogramLabel;
  private JLabel previewLabel;
  private JLabel userFeedback;
  private JRadioButton redComponent;
  private JRadioButton greenComponent;
  private JRadioButton blueComponent;
  private JRadioButton verticalFlip;
  private JRadioButton horizontalFlip;
  private JRadioButton blur;
  private JRadioButton sharpen;
  private JRadioButton luma;
  private JRadioButton sepia;
  private JRadioButton colorCorrect;
  private JRadioButton levelAdjust;
  private JRadioButton compress;
  private JButton fileSaveButton;
  private JButton fileOpenButton;
  private JButton applyOperation;
  private JSlider previewPercentage;
  private JSlider compressionPercentage;
  private JTextField white;
  private JTextField mid;
  private JTextField black;
  private ButtonGroup commandGroup;
  private JRadioButton dither;

  /**
   * Public constructor that builds the GUI.
   */
  public JFrameView(BufferedImage image, BufferedImage imageHistogram) {
    super("");

    setSize(1920, 1080);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Container panel for file open and save panels
    JPanel filePanelContainer = new JPanel();
    filePanelContainer.setLayout(new BoxLayout(filePanelContainer, BoxLayout.X_AXIS));

    // File open panel
    JPanel openPanel = new JPanel(new FlowLayout());
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand(Constants.LOAD_STR);
    openPanel.add(fileOpenButton);
    JLabel fileOpen = new JLabel("File path will appear here");
    openPanel.add(fileOpen);
    filePanelContainer.add(openPanel);

    // File save panel
    JPanel savePanel = new JPanel(new FlowLayout());
    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand(Constants.SAVE_STR);
    savePanel.add(fileSaveButton);
    JLabel fileSave = new JLabel("File path will appear here");
    savePanel.add(fileSave);
    filePanelContainer.add(savePanel);

    // Add file panel container to the top
    this.add(filePanelContainer, BorderLayout.NORTH);

    // Create a panel for the image panels (image and histogram)
    JPanel imagePanelContainer = new JPanel();
    imagePanelContainer.setLayout(new BoxLayout(imagePanelContainer, BoxLayout.X_AXIS));
    imagePanelContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Create image label and add image
    JPanel imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(800, 400));
    imagePanel.setBackground(Color.LIGHT_GRAY);
    imageLabel = new JLabel();
    imagePanel.add(imageLabel);

    // Wrap image panels in JScrollPane to make them scrollable
    JScrollPane scrollableImagePanel = new JScrollPane(imagePanel);
    scrollableImagePanel.setPreferredSize(new Dimension(800, 400));

    // Create histogram panel
    JPanel histogramPanel = new JPanel();
    histogramPanel.setPreferredSize(new Dimension(256, 256));
    histogramPanel.setBackground(Color.DARK_GRAY);
    histogramLabel = new JLabel();
    histogramPanel.add(histogramLabel);

    // Add the image panels to the container
    imagePanelContainer.add(scrollableImagePanel);
    imagePanelContainer.add(Box.createRigidArea(new Dimension(10, 0)));
    imagePanelContainer.add(histogramPanel);
    this.add(imagePanelContainer, BorderLayout.CENTER);

    // Create a panel with BoxLayout for all commands
    JPanel commandBar = new JPanel();
    commandBar.setLayout(new BoxLayout(commandBar, BoxLayout.X_AXIS));

    commandGroup = new ButtonGroup();
    commandBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    redComponent = new JRadioButton("red-component");
    redComponent.setActionCommand(Constants.RED_COMPONENT_STR);
    commandGroup.add(redComponent);
    commandBar.add(redComponent);

    greenComponent = new JRadioButton("green-component");
    greenComponent.setActionCommand(Constants.GREEN_COMPONENT_STR);
    commandGroup.add(greenComponent);
    commandBar.add(greenComponent);

    blueComponent = new JRadioButton("blue-component");
    blueComponent.setActionCommand(Constants.BLUE_COMPONENT_STR);
    commandGroup.add(blueComponent);
    commandBar.add(blueComponent);

    verticalFlip = new JRadioButton("vertical-flip");
    verticalFlip.setActionCommand(Constants.VERTICAL_FLIP_STR);
    commandGroup.add(verticalFlip);
    commandBar.add(verticalFlip);

    horizontalFlip = new JRadioButton("horizontal-flip");
    horizontalFlip.setActionCommand(Constants.HORIZONTAL_FLIP_STR);
    commandGroup.add(horizontalFlip);
    commandBar.add(horizontalFlip);

    blur = new JRadioButton("blur");
    blur.setActionCommand(Constants.BLUR_STR);
    commandGroup.add(blur);
    commandBar.add(blur);

    sharpen = new JRadioButton("sharpen");
    sharpen.setActionCommand(Constants.SHARPEN_STR);
    commandGroup.add(sharpen);
    commandBar.add(sharpen);

    luma = new JRadioButton("luma");
    luma.setActionCommand(Constants.LUMA_COMPONENT_STR);
    commandGroup.add(luma);
    commandBar.add(luma);

    sepia = new JRadioButton("sepia");
    sepia.setActionCommand(Constants.SEPIA_STR);
    commandGroup.add(sepia);
    commandBar.add(sepia);

    colorCorrect = new JRadioButton("color-corrected");
    colorCorrect.setActionCommand(Constants.COLOR_CORRECT_STR);
    commandGroup.add(colorCorrect);
    commandBar.add(colorCorrect);

    dither = new JRadioButton("dither");
    dither.setActionCommand(Constants.DITHER);
    commandGroup.add(dither);
    commandBar.add(dither);

    // Create a levelAdjustment command
    JPanel levelAdjustPanel = new JPanel();
    black = new JTextField(2);
    mid = new JTextField(2);
    white = new JTextField(2);
    levelAdjust = new JRadioButton("level-adjust");
    levelAdjust.setActionCommand(Constants.LEVELS_ADJUST_STR);
    commandGroup.add(levelAdjust);
    levelAdjustPanel.add(new JLabel("Black Level:"));
    levelAdjustPanel.add(black);
    levelAdjustPanel.add(new JLabel("Mid Level:"));
    levelAdjustPanel.add(mid);
    levelAdjustPanel.add(new JLabel("White Level:"));
    levelAdjustPanel.add(white);
    levelAdjustPanel.add(levelAdjust);

    commandBar.add(levelAdjustPanel);

    // Create a previewPercentage slider with range from 0 to 100
    compressionPercentage = new JSlider(0, 100);
    compressionPercentage.setPreferredSize(
        new Dimension(250, 50));
    compressionPercentage.setPaintTicks(true);
    compressionPercentage.setPaintLabels(true);
    compressionPercentage.setMajorTickSpacing(20);
    compressionPercentage.setMinorTickSpacing(5);

    // Add previewPercentage to the command bar
    JPanel compressionPercentagePanel = new JPanel();
    compress = new JRadioButton("compress 50%");
    compress.setActionCommand(Constants.COMPRESS_STR);
    commandGroup.add(compress);
    compress.setActionCommand(Constants.COMPRESS_STR);
    compressionPercentagePanel.add(compress);
    compressionPercentagePanel.add(compressionPercentage);
    commandBar.add(compressionPercentagePanel);

    // Create a previewPercentage slider with range from 0 to 100
    previewPercentage = new JSlider(0, 100);
    previewPercentage.setPreferredSize(
        new Dimension(250, 50));
    previewPercentage.setPaintTicks(true);
    previewPercentage.setPaintLabels(true);
    previewPercentage.setMajorTickSpacing(20);
    previewPercentage.setMinorTickSpacing(5);

    // Add previewPercentage and previewLabel to the command bar
    JPanel previewPercentagePanel = new JPanel();
    previewLabel = new JLabel("Preview Percentage: 50");
    previewPercentagePanel.add(previewLabel);
    previewPercentagePanel.add(previewPercentage);

    // Add applyButton to the command bar
    applyOperation = new JButton("Apply");
    applyOperation.setActionCommand(Constants.APPLY);
    commandGroup.add(applyOperation);
    commandBar.add(applyOperation);

    userFeedback = new JLabel("Press any buttons to get started");
    JPanel actions = new JPanel();
    actions.add(userFeedback);
    actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
    actions.add(previewPercentagePanel);
    actions.add(commandBar, BorderLayout.SOUTH);

    this.add(actions, BorderLayout.SOUTH);

    this.setVisible(true);
    pack();
  }

  @Override
  public void setActionListener(ActionListener listener) {
    fileSaveButton.addActionListener(listener);
    fileOpenButton.addActionListener(listener);
    redComponent.addActionListener(listener);
    blueComponent.addActionListener(listener);
    greenComponent.addActionListener(listener);
    verticalFlip.addActionListener(listener);
    horizontalFlip.addActionListener(listener);
    blur.addActionListener(listener);
    sharpen.addActionListener(listener);
    luma.addActionListener(listener);
    sepia.addActionListener(listener);
    colorCorrect.addActionListener(listener);
    dither.addActionListener(listener);
    levelAdjust.addActionListener(listener);
    compress.addActionListener(listener);
    applyOperation.addActionListener(listener);
    black.addActionListener(listener);
    mid.addActionListener(listener);
    white.addActionListener(listener);
  }

  @Override
  public void setChangeListener(ChangeListener listener) {
    previewPercentage.addChangeListener(listener);
    compressionPercentage.addChangeListener(listener);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public int getCompressionValue() {
    return compressionPercentage.getValue();
  }

  @Override
  public void updateCompressionValue(int value) {
    compress.setText("compress " + value + "%");
  }


  @Override
  public List<String> getBMWValues() {
    return List.of(this.black.getText(), this.mid.getText(), this.white.getText());
  }

  @Override
  public int getPreviewValue() {
    return previewPercentage.getValue();
  }

  @Override
  public void updatePreviewValue(int value) {
    previewLabel.setText("Preview Percentage: " + value);
  }

  @Override
  public String getSelectedRadioCommand() {
    for (Enumeration<AbstractButton> buttons = commandGroup.getElements();
        buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();
      if (button.isSelected()) {
        return button.getActionCommand();
      }
    }
    return null;
  }

  @Override
  public void updateGUIImage(BufferedImage image) {
    if (image != null) {
      imageLabel.setIcon(new ImageIcon(image));
    }
  }

  @Override
  public void updateGUIHistogram(BufferedImage histogram) {
    if (histogram != null) {
      histogramLabel.setIcon(new ImageIcon(histogram));
    }
  }

  @Override
  public void updateUserFeedback(String feedback) {
    userFeedback.setText(feedback);
  }


}
