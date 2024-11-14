package ime.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ime.controller.Features;

public class ImageEditorFrame extends JFrame implements ImageEditorView {
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem loadMenuItem;
  private JMenuItem saveMenuItem;
  private JMenuItem undoMenuItem;
  private JMenuItem redoMenuItem;
  private JSlider splitSlider;
  private JSlider compressionSlider;
  private JLabel splitValueLabel;
  private JCheckBox previewCheckBox;
  private JLabel compressionValueLabel;
  private JButton btnHorizontalFlip;
  private JButton btnVerticalFlip;
  private JButton btnBlur;
  private JButton btnSharpen;
  private JButton btnSepia;
  private JButton btnGreyscale;
  private JButton btnColorCorrection;
  private JLabel imageLabel;
  private JLabel histogramLabel;
  private JCheckBox previewMode;

  public ImageEditorFrame(String caption) {
    super(caption);
    setPreferredSize(new Dimension(1000, 750));
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
  }

  private void setupMenuBar() {
    menuBar = new JMenuBar();
    menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(menu);

    loadMenuItem = new JMenuItem("Load");
    loadMenuItem.setMnemonic(KeyEvent.VK_L);
    loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit()
            .getMenuShortcutKeyMaskEx()));
    menu.add(loadMenuItem);

    saveMenuItem = new JMenuItem("Save");
    saveMenuItem.setMnemonic(KeyEvent.VK_S);
    saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit()
            .getMenuShortcutKeyMaskEx()));
    menu.add(saveMenuItem);

    undoMenuItem = new JMenuItem("Undo");
    undoMenuItem.setMnemonic(KeyEvent.VK_Z);
    undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit()
            .getMenuShortcutKeyMaskEx()));
    menu.add(undoMenuItem);

    redoMenuItem = new JMenuItem("Redo");
    redoMenuItem.setMnemonic(KeyEvent.VK_Y);
    redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit()
            .getMenuShortcutKeyMaskEx()));
    menu.add(redoMenuItem);

    this.setJMenuBar(menuBar);
  }

  private JPanel createCenterPanel() {
    // Center panel containing image display with scrolling and histogram
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    centerPanel.setPreferredSize(new Dimension(200, 100));

    // Wrapper panel with GridBagLayout to center the imageLabel
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = wrapLabelInsideScrollPane(imageLabel, 0, 250);

    // Wrapper panel with GridBagLayout to center the histogramLabel
    histogramLabel = new JLabel();
    JScrollPane histogramScrollPane = wrapLabelInsideScrollPane(histogramLabel, 0, 100);

    // Add both imageScrollPane and histogramScrollPane to the center panel
    centerPanel.add(imageScrollPane, BorderLayout.NORTH);
    centerPanel.add(histogramScrollPane, BorderLayout.SOUTH);

    return centerPanel;
  }

  private JScrollPane wrapLabelInsideScrollPane(JLabel label, int width, int height) {
    JPanel imageWrapperPanel = new JPanel(new GridBagLayout());
    imageWrapperPanel.add(label); // Add imageLabel to the center of this panel

    // Wrap imageWrapperPanel in JScrollPane to allow scrolling
    JScrollPane scrollPane = new JScrollPane(imageWrapperPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(width, height));
    return scrollPane;
  }

  private JPanel createLeftPanel() {
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    leftPanel.setPreferredSize(new Dimension(150, 0));

    // Create buttons with fixed size
    Dimension buttonSize = new Dimension(130, 30);

    btnHorizontalFlip = createStyledButton("Horizontal Flip", buttonSize);
    btnHorizontalFlip.setActionCommand("horizontal-flip");
    btnVerticalFlip = createStyledButton("Vertical Flip", buttonSize);
    btnVerticalFlip.setActionCommand("vertical-flip");
    btnBlur = createStyledButton("Blur", buttonSize);
    btnBlur.setActionCommand("blur");
    btnSharpen = createStyledButton("Sharpen", buttonSize);
    btnSharpen.setActionCommand("sharpen");
    btnSepia = createStyledButton("Sepia", buttonSize);
    btnSepia.setActionCommand("sepia");
    btnGreyscale = createStyledButton("Greyscale", buttonSize);
    btnGreyscale.setActionCommand("luma-component");
    btnColorCorrection = createStyledButton("Color Correct", buttonSize);
    btnColorCorrection.setActionCommand("color-correct");

    // Add buttons to panel with spacing
    leftPanel.add(btnHorizontalFlip);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnVerticalFlip);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnBlur);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnSharpen);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnSepia);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnGreyscale);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnColorCorrection);

    // Add glue to push buttons to top
    leftPanel.add(Box.createVerticalGlue());

    return leftPanel;
  }

  private JButton createStyledButton(String text, Dimension size) {
    JButton button = new JButton(text);
    button.setMaximumSize(size);
    button.setPreferredSize(size);
    button.setMinimumSize(size);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    return button;
  }

  private JPanel createRightPanel() {
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    rightPanel.setPreferredSize(new Dimension(200, 0));

    // Split Percentage Slider
    splitValueLabel = new JLabel("Value: 100%");
    splitSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
    JPanel splitPanel = new JPanel();
    splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.Y_AXIS));
    splitPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
    JPanel previewModePanel = new JPanel();
    previewModePanel.setLayout(new BoxLayout(previewModePanel, BoxLayout.Y_AXIS));
    previewModePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    previewMode = new JCheckBox("Enter Preview Mode");
    previewModePanel.add(previewMode);
    splitPanel.add(previewModePanel);
    splitPanel.add(Box.createRigidArea(new Dimension(0, 10)));


    JPanel previewSettings = new JPanel();
    previewSettings.setLayout(new BoxLayout(previewSettings, BoxLayout.X_AXIS));

    JPanel sliderPanel = new JPanel();
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
    sliderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    setupSlider(splitValueLabel, sliderPanel, splitSlider);
    splitSlider.setEnabled(false);
    previewSettings.add(sliderPanel);
    previewSettings.add(Box.createRigidArea(new Dimension(10, 0)));

    JPanel previewTogglePanel = new JPanel();
    previewTogglePanel.setLayout(new BoxLayout(previewTogglePanel, BoxLayout.Y_AXIS));
    previewTogglePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    previewTogglePanel.add(Box.createRigidArea(new Dimension(0, 10)));
    previewCheckBox = new JCheckBox("Enable");
    previewTogglePanel.add(previewCheckBox);
    previewCheckBox.setEnabled(false);
    previewCheckBox.setSelected(true);
    previewSettings.add(previewTogglePanel);

    splitPanel.add(previewSettings);
    rightPanel.add(splitPanel);
    rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    // Compression Slider
    compressionValueLabel = new JLabel("Value: 0%");
    compressionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    JPanel compressionPanel = new JPanel();
    compressionPanel.setLayout(new BoxLayout(compressionPanel, BoxLayout.Y_AXIS));
    compressionPanel.setBorder(BorderFactory.createTitledBorder("Compression"));

    setupSlider(compressionValueLabel, compressionPanel, compressionSlider);

    rightPanel.add(compressionPanel);

    return rightPanel;
  }

  private void setupSlider(JLabel label, JPanel panel, JSlider slider) {
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(label);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    slider.addChangeListener(e -> label.setText("Value: " + slider.getValue() + "%"));
    panel.add(slider);
  }

  @Override
  public void addFeatures(Features features) {
    loadMenuItem.addActionListener(e -> {
      JFileChooser loadFileChooser = new JFileChooser();
      loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Image files", "jpg", "ppm", "png"
      );
      loadFileChooser.setFileFilter(filter);
      int result = loadFileChooser.showOpenDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        String imagePath = loadFileChooser.getSelectedFile().getAbsolutePath();
        features.loadImage(imagePath);
      }
    });
    saveMenuItem.addActionListener(e -> {
      JFileChooser saveFileChooser = new JFileChooser();
      saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Image files", "jpg", "ppm", "png"
      );
      saveFileChooser.setFileFilter(filter);
      int result = saveFileChooser.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
        String savePath = saveFileChooser.getSelectedFile().getAbsolutePath();
        if (!savePath.toLowerCase().endsWith(".jpg") &&
                !savePath.toLowerCase().endsWith(".ppm") &&
                !savePath.toLowerCase().endsWith(".png")) {
          savePath += ".png";
        }
        features.saveImage(savePath);
      }
    });
    undoMenuItem.addActionListener(e -> features.undo());
    redoMenuItem.addActionListener(e -> features.redo());


    btnHorizontalFlip.addActionListener(e -> {
      features.flipImage(e.getActionCommand());
    });

    btnVerticalFlip.addActionListener(e -> {
      features.flipImage(e.getActionCommand());
    });

    btnBlur.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      features.applyFilter(previewMode.isSelected(), e.getActionCommand(), splitWidth);
      btnSharpen.setEnabled(!previewMode.isSelected());
      btnSepia.setEnabled(!previewMode.isSelected());
      btnGreyscale.setEnabled(!previewMode.isSelected());
      btnColorCorrection.setEnabled(!previewMode.isSelected());
    });

    btnSharpen.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      features.applyFilter(previewMode.isSelected(), e.getActionCommand(), splitWidth);
      btnBlur.setEnabled(!previewMode.isSelected());
      btnSepia.setEnabled(!previewMode.isSelected());
      btnGreyscale.setEnabled(!previewMode.isSelected());
      btnColorCorrection.setEnabled(!previewMode.isSelected());
    });

    btnSepia.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      features.applyFilter(previewMode.isSelected(), e.getActionCommand(), splitWidth);
      btnSharpen.setEnabled(!previewMode.isSelected());
      btnBlur.setEnabled(!previewMode.isSelected());
      btnGreyscale.setEnabled(!previewMode.isSelected());
      btnColorCorrection.setEnabled(!previewMode.isSelected());
    });

    btnGreyscale.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      features.applyGreyScale(previewMode.isSelected(), e.getActionCommand(), splitWidth);
      btnSharpen.setEnabled(!previewMode.isSelected());
      btnBlur.setEnabled(!previewMode.isSelected());
      btnSepia.setEnabled(!previewMode.isSelected());
      btnColorCorrection.setEnabled(!previewMode.isSelected());
    });

    btnColorCorrection.addActionListener(e -> {
      String splitWidth = getSplitWidth();
      features.applyColorCorrect(previewMode.isSelected(), splitWidth);
      btnSharpen.setEnabled(!previewMode.isSelected());
      btnBlur.setEnabled(!previewMode.isSelected());
      btnSepia.setEnabled(!previewMode.isSelected());
      btnGreyscale.setEnabled(!previewMode.isSelected());
    });

    previewMode.addActionListener(e -> {
      saveMenuItem.setEnabled(!previewMode.isSelected());
      undoMenuItem.setEnabled(!previewMode.isSelected());
      redoMenuItem.setEnabled(!previewMode.isSelected());
      splitSlider.setEnabled(previewMode.isSelected());
      previewCheckBox.setEnabled(previewMode.isSelected());
      btnHorizontalFlip.setEnabled(!previewMode.isSelected());
      btnVerticalFlip.setEnabled(!previewMode.isSelected());
      compressionSlider.setEnabled(!previewMode.isSelected());
      if(!previewMode.isSelected()) {
        features.exitPreviewMode(previewCheckBox.isSelected());
        enableAllButtons();
        previewCheckBox.setSelected(true);
      }
    });

    previewCheckBox.addActionListener(e -> {
      features.togglePreview(getSplitWidth());
    });

    splitSlider.addChangeListener(e -> {
      if(previewCheckBox.isSelected()) {
        features.togglePreview(getSplitWidth());
      }
    });

  }

  private String getSplitWidth() {
    String splitWidth = "100";
    if (previewMode.isSelected() && previewCheckBox.isSelected()) {
      splitWidth = String.valueOf(splitSlider.getValue());
    }
    return splitWidth;
  }

  @Override
  public void setImage(BufferedImage image) {
    ImageIcon imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
    imageLabel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    imageLabel.revalidate();
    imageLabel.repaint();
  }

  @Override
  public void setHistogram(BufferedImage histogram) {
    ImageIcon imageIcon = new ImageIcon(histogram);
    histogramLabel.setIcon(imageIcon);
    histogramLabel.setPreferredSize(new Dimension(histogram.getWidth(), histogram.getHeight()));
    histogramLabel.revalidate();
    histogramLabel.repaint();
  }

  @Override
  public void showErrorMessageDialog(String message, String title) {
    JOptionPane.showMessageDialog(this, title, message, JOptionPane.ERROR_MESSAGE);
  }

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
  public void enableAllButtons() {
    btnBlur.setEnabled(true);
    btnSharpen.setEnabled(true);
    btnSepia.setEnabled(true);
    btnSepia.setEnabled(true);
    btnGreyscale.setEnabled(true);
    btnColorCorrection.setEnabled(true);
  }
}