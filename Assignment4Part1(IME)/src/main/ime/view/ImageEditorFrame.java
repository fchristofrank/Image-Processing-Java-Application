package ime.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ime.controller.Features;

/**
 * The main frame of the image editor application.  Handles the graphical user interface (GUI) elements and interacts with the controller (Features) to perform image editing operations.
 */
public class ImageEditorFrame extends JFrame implements ImageEditorView, WindowListener {

  private JMenuItem loadMenuItem;
  private JMenuItem saveMenuItem;
  private JMenuItem undoMenuItem;
  private JMenuItem redoMenuItem;
  private JSlider splitSlider;
  private JCheckBox previewCheckBox;
  private JButton btnHorizontalFlip;
  private JButton btnVerticalFlip;
  private JButton btnBlur;
  private JButton btnSharpen;
  private JButton btnSepia;
  private JButton btnGreyscale;
  private JButton btnRedComponent;
  private JButton btnGreenComponent;
  private JButton btnBlueComponent;
  private JButton btnColorCorrection;
  private JButton btnDownscale;
  private JLabel imageLabel;
  private JLabel histogramLabel;
  private JCheckBox previewMode;
  private JTextField compressionText;
  private JPanel compressionPanel;
  private JButton btnCompress;
  private JLabel compressionLabel;
  private JPanel levelsAdjustmentPanel;
  private JPanel downscalePanel;
  private JTextField blackLevel;
  private JTextField middleLevel;
  private JTextField whiteLevel;
  private JTextField downscaleWidth;
  private JTextField downscaleHeight;
  private JButton btnAdjustLevels;
  private Features features;
  private JButton btnApplyPreview;

  /**
   * Constructs the ImageEditorFrame.
   *
   * @param caption The title of the frame.
   */
  public ImageEditorFrame(String caption) {
    super(caption);
    setPreferredSize(new Dimension(1000, 750));
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(this);
    setLayout(new BorderLayout(10, 10));

    // Setting up the menu bar
    setupMenuBar();

    // Create main content panel with border padding
    JPanel mainContent = new JPanel(new BorderLayout(10, 10));
    mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    //create central panel for image preview and histogram
    JPanel centerPanel = createCenterPanel();
    mainContent.add(centerPanel, BorderLayout.CENTER);

    // Create left panel for buttons
    JPanel leftPanel = createLeftPanel();
    mainContent.add(leftPanel, BorderLayout.WEST);

    // Create right panel for sliders
    JPanel rightPanel = createRightPanel();
    mainContent.add(rightPanel, BorderLayout.EAST);

    add(mainContent);
    pack();
    setVisible(true);

    SwingUtilities.invokeLater(() -> {
      TutorialDialog tutorial = new TutorialDialog(this);
      tutorial.setVisible(true);
    });
  }

  /**
   * Sets up the menu bar with File menu items (Load, Save, Undo, Redo).
   */
  private void setupMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(menu);

    loadMenuItem = new JMenuItem("Load");
    loadMenuItem.setMnemonic(KeyEvent.VK_L);
    loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    menu.add(loadMenuItem);

    saveMenuItem = new JMenuItem("Save");
    saveMenuItem.setMnemonic(KeyEvent.VK_S);
    saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    menu.add(saveMenuItem);

    undoMenuItem = new JMenuItem("Undo");
    undoMenuItem.setMnemonic(KeyEvent.VK_Z);
    undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    menu.add(undoMenuItem);

    redoMenuItem = new JMenuItem("Redo");
    redoMenuItem.setMnemonic(KeyEvent.VK_Y);
    redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    menu.add(redoMenuItem);

    this.setJMenuBar(menuBar);
  }

  /**
   * Creates the central panel containing the image display (with scrolling) and the histogram display (with scrolling).
   *
   * @return The center panel.
   */
  private JPanel createCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    centerPanel.setPreferredSize(new Dimension(200, 100));

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = wrapLabelInsideScrollPane(imageLabel, 0, 250);

    histogramLabel = new JLabel();
    JScrollPane histogramScrollPane = wrapLabelInsideScrollPane(histogramLabel, 0, 100);

    centerPanel.add(imageScrollPane);
    centerPanel.add(histogramScrollPane);

    return centerPanel;
  }

  /**
   * Wraps a JLabel inside a JScrollPane for scrolling functionality.
   *
   * @param label  The JLabel to wrap.
   * @param width  The preferred width.
   * @param height The preferred height.
   * @return The JScrollPane containing the JLabel.
   */
  private JScrollPane wrapLabelInsideScrollPane(JLabel label, int width, int height) {
    JPanel wrapperPanel = new JPanel(new GridBagLayout());
    wrapperPanel.add(label);

    JScrollPane scrollPane = new JScrollPane(wrapperPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(width, height));
    return scrollPane;
  }

  /**
   * Creates the left panel containing the image operation buttons.
   *
   * @return The left panel.
   */
  private JPanel createLeftPanel() {
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    leftPanel.setPreferredSize(new Dimension(180, 0));

    Dimension buttonSize = new Dimension(150, 30);
    String[][] buttonInfo = {
            {"Horizontal Flip", "horizontal-flip"},
            {"Vertical Flip", "vertical-flip"},
            {"Blur", "blur"},
            {"Sharpen", "sharpen"},
            {"Sepia", "sepia"},
            {"Greyscale", "luma-component"},
            {"Red Component", "red-component"},
            {"Green Component", "green-component"},
            {"Blue Component", "blue-component"},
            {"Color Correct", "color-correct"}
    };

    for (String[] info : buttonInfo) {
      JButton button = createStyledButton(info[0], buttonSize);
      button.setActionCommand(info[1]);
      leftPanel.add(button);
      leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
      assignButtonToField(button, info[0]);
    }
    leftPanel.remove(leftPanel.getComponentCount() - 1);
    leftPanel.add(Box.createVerticalGlue());

    return leftPanel;
  }

  /**
   * Assigns a button to its corresponding class field.
   *
   * @param button     The button.
   * @param buttonName The name of the button.
   */
  private void assignButtonToField(JButton button, String buttonName) {
    switch (buttonName) {
      case "Horizontal Flip":
        btnHorizontalFlip = button;
        break;
      case "Vertical Flip":
        btnVerticalFlip = button;
        break;
      case "Blur":
        btnBlur = button;
        break;
      case "Sharpen":
        btnSharpen = button;
        break;
      case "Sepia":
        btnSepia = button;
        break;
      case "Greyscale":
        btnGreyscale = button;
        break;
      case "Red Component":
        btnRedComponent = button;
        break;
      case "Green Component":
        btnGreenComponent = button;
        break;
      case "Blue Component":
        btnBlueComponent = button;
        break;
      case "Color Correct":
        btnColorCorrection = button;
        break;
    }
  }

  /**
   * Creates a styled button with specified text and size.
   *
   * @param text The text of the button.
   * @param size The size of the button.
   * @return The styled button.
   */
  private JButton createStyledButton(String text, Dimension size) {
    JButton button = new JButton(text);
    button.setMaximumSize(size);
    button.setPreferredSize(size);
    button.setMinimumSize(size);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    return button;
  }

  /**
   * Creates the right panel containing preview, compression, and levels adjustment controls.
   *
   * @return The right panel.
   */
  private JPanel createRightPanel() {
    JPanel rightPanel = createBasePanel();

    rightPanel.add(createPreviewPanel());
    rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    rightPanel.add(createCompressionPanel());
    rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    rightPanel.add(createLevelsAdjustmentPanel());
    rightPanel.add(createDownscalePanel());

    return rightPanel;
  }

  /**
   * Creates a base panel with a border and BoxLayout.
   *
   * @return The base panel.
   */
  private JPanel createBasePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    panel.setPreferredSize(new Dimension(200, 0));
    return panel;
  }

  /**
   * Creates the preview panel with preview mode and settings.
   *
   * @return The preview panel.
   */
  private JPanel createPreviewPanel() {
    JPanel splitPanel = new JPanel();
    splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.Y_AXIS));
    splitPanel.setBorder(BorderFactory.createTitledBorder("Preview"));

    splitPanel.add(createPreviewModePanel());
    splitPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    splitPanel.add(createPreviewSettingsPanel());
    splitPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    btnApplyPreview = createStyledButton("Apply", new Dimension(180, 30));
    btnApplyPreview.setEnabled(previewMode.isSelected());
    splitPanel.add(btnApplyPreview);
    return splitPanel;
  }

  /**
   * Creates the preview mode panel with a checkbox.
   *
   * @return The preview mode panel.
   */
  private JPanel createPreviewModePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);
    previewMode = new JCheckBox("Enter Preview Mode");
    panel.add(previewMode);
    return panel;
  }

  /**
   * Creates the preview settings panel with slider and toggle.
   *
   * @return The preview settings panel.
   */
  private JPanel createPreviewSettingsPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    panel.add(createSliderPanel());
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(createPreviewTogglePanel());

    return panel;
  }

  /**
   * Creates the slider panel with slider and label.
   *
   * @return The slider panel.
   */
  private JPanel createSliderPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel splitValueLabel = new JLabel("Value: 50%");
    splitSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    splitSlider.setEnabled(false);

    setupSlider(splitValueLabel, panel, splitSlider);

    return panel;
  }

  /**
   * Creates the preview toggle panel with a checkbox.
   *
   * @return The preview toggle panel.
   */
  private JPanel createPreviewTogglePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    previewCheckBox = new JCheckBox("Enable");
    previewCheckBox.setEnabled(false);
    previewCheckBox.setSelected(true);
    panel.add(previewCheckBox);

    return panel;
  }

  /**
   * Creates the compression panel with input field and button.
   *
   * @return The compression panel.
   */
  private JPanel createCompressionPanel() {
    compressionPanel = new JPanel();
    compressionPanel.setLayout(new BoxLayout(compressionPanel, BoxLayout.Y_AXIS));
    compressionPanel.setBorder(BorderFactory.createTitledBorder("Compression"));

    compressionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    compressionPanel.add(createCompressionInputPanel());
    compressionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    btnCompress = createStyledButton("Compress", new Dimension(200, 30));
    compressionPanel.add(btnCompress);

    return compressionPanel;
  }

  /**
   * Creates the compression input panel with label and text field.
   *
   * @return The compression input panel.
   */
  private JPanel createCompressionInputPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    compressionLabel = new JLabel("Compression%:");
    compressionText = createFixedSizeTextField("", 3, new Dimension(30, 20));

    panel.add(compressionLabel);
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
    panel.add(compressionText);

    return panel;
  }

  /**
   * Creates the levels adjustment panel with input containers and button.
   *
   * @return The levels adjustment panel.
   */
  private JPanel createLevelsAdjustmentPanel() {
    levelsAdjustmentPanel = new JPanel();
    levelsAdjustmentPanel.setLayout(new BoxLayout(levelsAdjustmentPanel, BoxLayout.Y_AXIS));
    levelsAdjustmentPanel.setBorder(BorderFactory.createTitledBorder("Levels Adjustment"));

    levelsAdjustmentPanel.add(createLevelsInputContainer());
    levelsAdjustmentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    btnAdjustLevels = createStyledButton("Adjust Levels",
            new Dimension(200, 30));
    levelsAdjustmentPanel.add(btnAdjustLevels);
    levelsAdjustmentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    return levelsAdjustmentPanel;
  }

  private JPanel createDownscalePanel() {
    downscalePanel = new JPanel();
    downscalePanel.setLayout(new BoxLayout(downscalePanel, BoxLayout.Y_AXIS));
    downscalePanel.setBorder(BorderFactory.createTitledBorder("Downscale"));

    downscalePanel.add(createDownscaleInputContainer());
    downscalePanel.add(Box.createRigidArea(new Dimension(0, 10)));
    btnDownscale = createStyledButton("Downscale",
            new Dimension(200, 30));
    downscalePanel.add(btnDownscale);
    downscalePanel.add(Box.createRigidArea(new Dimension(0, 10)));

    return downscalePanel;
  }

  /**
   * Creates the levels input container with labels and text fields.
   *
   * @return The levels input container.
   */
  private JPanel createLevelsInputContainer() {
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    container.setAlignmentX(Component.CENTER_ALIGNMENT);

    container.add(createLevelsLabelPanel());
    container.add(Box.createRigidArea(new Dimension(5, 0)));
    container.add(createLevelsValuePanel());

    return container;
  }

  private JPanel createDownscaleInputContainer() {
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    container.setAlignmentX(Component.CENTER_ALIGNMENT);

    container.add(createDownscaleLabelPanel());
    container.add(Box.createRigidArea(new Dimension(5, 0)));
    container.add(createDownscaleValuePanel());

    return container;
  }

  /**
   * Creates the levels label panel with labels for black, middle, and white levels.
   *
   * @return The levels label panel.
   */
  private JPanel createLevelsLabelPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.add(new JLabel("Black:"));
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(new JLabel("Middle:"));
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(new JLabel("White:"));

    return panel;
  }

  private JPanel createDownscaleLabelPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.add(new JLabel("Width:"));
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(new JLabel("Height:"));
    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    return panel;
  }


  /**
   * Creates the levels value panel with text fields for black, middle, and white levels.
   *
   * @return The levels value panel.
   */
  private JPanel createLevelsValuePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    blackLevel = createFixedSizeTextField("", 3, new Dimension(30, 20));
    panel.add(blackLevel);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    middleLevel = createFixedSizeTextField("", 3, new Dimension(30, 20));
    panel.add(middleLevel);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    whiteLevel = createFixedSizeTextField("", 3, new Dimension(30, 20));
    panel.add(whiteLevel);

    return panel;
  }

  /**
   * Creates the downscale value panel with text fields for width and height.
   *
   * @return The levels value panel.
   */
  private JPanel createDownscaleValuePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.CENTER_ALIGNMENT);

    downscaleWidth = createFixedSizeTextField("", 3, new Dimension(30, 20));
    panel.add(downscaleWidth);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    downscaleHeight = createFixedSizeTextField("", 3, new Dimension(30, 20));
    panel.add(downscaleHeight);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    return panel;
  }

  /**
   * Creates a text field with fixed size.
   *
   * @param text    The initial text.
   * @param columns The number of columns.
   * @param size    The size of the text field.
   * @return The fixed-size text field.
   */
  private JTextField createFixedSizeTextField(String text, int columns, Dimension size) {
    JTextField textField = new JTextField(text, columns);
    textField.setPreferredSize(size);
    textField.setMinimumSize(size);
    textField.setMaximumSize(size);
    return textField;
  }

  /**
   * Sets up the slider with listener for value updates.
   *
   * @param label  The label to display the slider value.
   * @param panel  The panel to add the slider to.
   * @param slider The slider.
   */
  private void setupSlider(JLabel label, JPanel panel, JSlider slider) {
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(label);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    slider.addChangeListener(e -> label.setText("Value: " + slider.getValue() + "%"));
    panel.add(slider);
  }

  /**
   * Adds features to the frame, connecting GUI elements to the controller.
   *
   * @param features The Features object (controller).
   */
  @Override
  public void addFeatures(Features features) {
    this.features = features;
    setupFileMenuItems(features);
    setupEditMenuItems(features);
    setupImageOperationButtons(features);
    setupFilterButtons(features);
    setupPreviewControls(features);
  }

  /**
   * Sets up the File menu items (Load and Save) with action listeners.
   *
   * @param features The Features object.
   */
  private void setupFileMenuItems(Features features) {
    loadMenuItem.addActionListener(e -> handleLoadImage(features));
    saveMenuItem.addActionListener(e -> handleSaveImage(features));
  }

  /**
   * Sets up the Edit menu items (Undo and Redo) with action listeners.
   *
   * @param features The Features object.
   */
  private void setupEditMenuItems(Features features) {
    undoMenuItem.addActionListener(e -> features.undo());
    redoMenuItem.addActionListener(e -> features.redo());
  }

  /**
   * Sets up the image operation buttons (Horizontal Flip and Vertical Flip) with action listeners.
   *
   * @param features The Features object.
   */
  private void setupImageOperationButtons(Features features) {
    btnHorizontalFlip.addActionListener(e -> features.flipImage(e.getActionCommand()));
    btnVerticalFlip.addActionListener(e -> features.flipImage(e.getActionCommand()));
    btnApplyPreview.addActionListener(e ->
    {
      features.applyPreviewChanges();
      previewMode.setSelected(false);
      handlePreviewModeChange(features);

    });
  }

  /**
   * Sets up the filter buttons with action listeners.
   *
   * @param features The Features object.
   */
  private void setupFilterButtons(Features features) {
    setupFilterButton(btnBlur, features);
    setupFilterButton(btnSharpen, features);
    setupFilterButton(btnSepia, features);
    setupGreyscaleButton(btnGreyscale, features);
    setupFilterButton(btnRedComponent, features);
    setupFilterButton(btnGreenComponent, features);
    setupFilterButton(btnBlueComponent, features);
    setupColorCorrectButton(btnColorCorrection, features);
    setupCompressButton(btnCompress, features);
    setupAdjustLevelsButton(btnAdjustLevels, features);
    setupDownscaleButton(btnDownscale, features);
  }

  /**
   * Sets up a single filter button with an action listener.
   *
   * @param button   The filter button.
   * @param features The Features object.
   */
  private void setupFilterButton(JButton button, Features features) {
    button.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      if(features.applyFilter(previewMode.isSelected(), e.getActionCommand(), splitWidth)) {
        toggleFilterButtons();
      }
    });
  }

  private void setupGreyscaleButton(JButton button, Features features) {
    button.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      if(features.applyGreyScale(previewMode.isSelected(), e.getActionCommand(), splitWidth)) {
        toggleFilterButtons();
      }
    });
  }

  private void setupColorCorrectButton(JButton button, Features features) {
    button.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      if(features.applyColorCorrect(previewMode.isSelected(), splitWidth)) {
        toggleFilterButtons();
      }
    });
  }

  private void setupCompressButton(JButton button, Features features) {
    button.addActionListener(e -> {
      if(features.applyCompress(compressionText.getText())){
      toggleFilterButtons();
      }
    });
  }

  private void setupAdjustLevelsButton(JButton button, Features features) {
    button.addActionListener(e -> {
      String splitWidth = getSplitWidth();

      if (features.adjustLevels(previewMode.isSelected(), blackLevel.getText(), middleLevel.getText(),
          whiteLevel.getText(), splitWidth)){
        toggleFilterButtons();
      }
    });
  }

  private void setupDownscaleButton(JButton button, Features features) {
    button.addActionListener(e -> {
      if(features.downScale(downscaleWidth.getText(),downscaleHeight.getText())) {
        toggleFilterButtons();
      }
    });
  }

  /**
   * Toggles the enabled state of filter buttons to prevent multiple simultaneous filter applications in preview mode.
   */
  private void toggleFilterButtons() {
    JButton[] filterButtons = {btnBlur, btnSharpen, btnSepia, btnGreyscale, btnRedComponent,
            btnGreenComponent, btnBlueComponent, btnColorCorrection, btnAdjustLevels, btnDownscale};
    for (JButton button : filterButtons) {
      if (!(previewMode.isSelected())) {
        button.setEnabled(true);
        if (button == btnAdjustLevels) {
          enableLevelsAdjustmentFeatures(true);
        }
      } else {
        button.setEnabled(false);
        if (button == btnAdjustLevels) {
          enableLevelsAdjustmentFeatures(false);
        }
      }
    }
  }

  /**
   * Sets up the preview controls (checkbox and slider) with action listeners.
   *
   * @param features The Features object.
   */
  private void setupPreviewControls(Features features) {
    previewMode.addActionListener(e -> handlePreviewModeChange(features));
    previewCheckBox.addActionListener(e -> features.togglePreview(getSplitWidth()));
    splitSlider.addChangeListener(e -> {
      if (previewCheckBox.isSelected()) {
        features.togglePreview(getSplitWidth());
      }
    });
  }

  /**
   * Handles changes in preview mode.
   *
   * @param features The Features object.
   */
  private void handlePreviewModeChange(Features features) {
    boolean isPreviewMode = previewMode.isSelected();
    loadMenuItem.setEnabled(!isPreviewMode);
    saveMenuItem.setEnabled(!isPreviewMode);
    undoMenuItem.setEnabled(!isPreviewMode);
    redoMenuItem.setEnabled(!isPreviewMode);
    splitSlider.setEnabled(isPreviewMode);
    previewCheckBox.setEnabled(isPreviewMode);
    previewMode.setSelected(isPreviewMode);
    btnHorizontalFlip.setEnabled(!isPreviewMode);
    btnVerticalFlip.setEnabled(!isPreviewMode);
    compressionPanel.setEnabled(!isPreviewMode);
    compressionLabel.setEnabled(!isPreviewMode);
    compressionText.setEnabled(!isPreviewMode);
    btnCompress.setEnabled(!isPreviewMode);
    btnDownscale.setEnabled(!isPreviewMode);
    btnApplyPreview.setEnabled(isPreviewMode);

    if (!isPreviewMode) {
      features.exitPreviewMode();
      enableAllButtons();
      splitSlider.setValue(50);
      previewCheckBox.setSelected(true);
    }
  }

  /**
   * Handles loading an image from a file.
   *
   * @param features The Features object.
   */
  private void handleLoadImage(Features features) {
    JFileChooser fileChooser = createImageFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      features.loadImage(imagePath, false);
    }
  }

  /**
   * Handles saving an image to a file.
   *
   * @param features The Features object.
   */
  private void handleSaveImage(Features features) {
    JFileChooser fileChooser = createImageFileChooser();
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      String savePath = fileChooser.getSelectedFile().getAbsolutePath();
      savePath = ensureCorrectFileExtension(savePath);
      features.saveImage(savePath);
    }
  }

  /**
   * Creates a file chooser for image files.
   *
   * @return The image file chooser.
   */
  private JFileChooser createImageFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "ppm", "png");
    fileChooser.setFileFilter(filter);
    return fileChooser;
  }

  /**
   * Ensures that the file path has a correct image extension.
   *
   * @param path The file path.
   * @return The file path with a correct extension.
   */
  private String ensureCorrectFileExtension(String path) {
    if (!path.toLowerCase().endsWith(".jpg") && !path.toLowerCase().endsWith(".ppm")
            && !path.toLowerCase().endsWith(".png")) {
      return path + ".png";
    }
    return path;
  }

  /**
   * Enables all filter buttons.
   */
  private void enableAllButtons() {
    JButton[] buttons = {btnBlur, btnSharpen, btnSepia, btnGreyscale, btnRedComponent,
            btnGreenComponent, btnBlueComponent, btnColorCorrection, btnAdjustLevels};
    for (JButton button : buttons) {
      button.setEnabled(true);
    }
    enableLevelsAdjustmentFeatures(true);
  }

  private void disableAllButtons() {
    JButton[] buttons = {btnBlur, btnSharpen, btnSepia, btnGreyscale, btnRedComponent,
        btnGreenComponent, btnBlueComponent, btnColorCorrection, btnAdjustLevels};
    for (JButton button : buttons) {
      button.setEnabled(false);
    }
    enableLevelsAdjustmentFeatures(false);
  }

  private void enableLevelsAdjustmentFeatures(boolean enable) {
    levelsAdjustmentPanel.setEnabled(enable);
    blackLevel.setEnabled(enable);
    middleLevel.setEnabled(enable);
    whiteLevel.setEnabled(enable);
  }

  /**
   * Gets the split width from the slider, handling preview mode.
   *
   * @return The split width as a string.
   */
  private String getSplitWidth() {
    String splitWidth = "100";
    if (previewMode.isSelected() && previewCheckBox.isSelected()) {
      splitWidth = String.valueOf(splitSlider.getValue());
    }
    return splitWidth;
  }

  /**
   * Sets the image in the image label.
   *
   * @param image The image to set.
   */
  @Override
  public void setImage(BufferedImage image) {
    ImageIcon imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
    imageLabel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    imageLabel.revalidate();
    imageLabel.repaint();
  }

  /**
   * Sets the histogram in the histogram label.
   *
   * @param histogram The histogram image to set.
   */
  @Override
  public void setHistogram(BufferedImage histogram) {
    ImageIcon imageIcon = new ImageIcon(histogram);
    histogramLabel.setIcon(imageIcon);
    histogramLabel.setPreferredSize(new Dimension(histogram.getWidth(), histogram.getHeight()));
    histogramLabel.revalidate();
    histogramLabel.repaint();
  }

  /**
   * Shows an error message dialog.
   *
   * @param message The error message.
   * @param title   The title of the dialog.
   */
  @Override
  public void showErrorMessageDialog(String message, String title) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void showWarningMessageBeforeLoading(String imagePath) {
    int result = JOptionPane.showConfirmDialog(this,
            "Do you want to load an image without saving the current image",
            "Save Changes?", JOptionPane.YES_NO_CANCEL_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      features.loadImage(imagePath, true);
    }
  }

  /**
   * Clears the image and histogram displays.
   */
  @Override
  public void cleanSlate() {
    imageLabel.setIcon(null);
    histogramLabel.setIcon(null);
    imageLabel.revalidate();
    imageLabel.repaint();
    histogramLabel.revalidate();
    histogramLabel.repaint();
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {
    if (!features.isLoadedAndSaved()) {
      int result = JOptionPane.showConfirmDialog(this,
              "The current image has not been saved. Are you sure you want to close?",
              "Unsaved Changes", JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  }

  @Override
  public void windowClosed(WindowEvent e) {

  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }
}