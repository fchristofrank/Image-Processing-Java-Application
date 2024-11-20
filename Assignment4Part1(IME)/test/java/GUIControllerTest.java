import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import ime.controller.cli.OperationCreator;
import ime.controller.gui.Features;
import ime.controller.gui.GUIController;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.GUIImageOperationFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.view.gui.ImageEditorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the functionalities of the GUI controller and GUI Image Operation Factory.
 */
public class GUIControllerTest {

  @Test
  public void loadImageTest() {
    String expectedString = "Operation with name 'load' has been created\n" +
            "Operation has been executed with the following args: [/test/path/image.png]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.loadImage("/test/path/image.png", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void saveImageTest() {
    String expectedString = "Operation with name 'save' has been created\n" +
            "Operation has been executed with the following args: [/test/path/image.png]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.saveImage("/test/path/image.png");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void horizontalFlipImageTest() {
    String expectedString = "Operation with name 'horizontal-flip' has been created\n" +
            "Operation has been executed with the following args: []\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.flipImage("horizontal-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void verticalFlipImageTest() {
    String expectedString = "Operation with name 'vertical-flip' has been created\n" +
            "Operation has been executed with the following args: []\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.flipImage("vertical-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterImageTestWithoutPreview() {
    String expectedString = "Operation with name 'filter' has been created\n" +
            "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(false, "filter", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterImageTestWithPreview() {
    String expectedString = "Operation with name 'filter' has been created\n" +
            "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(true, "filter", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void compressImageTest() {
    String expectedString = "Operation with name 'compress' has been created\n" +
            "Operation has been executed with the following args: [90]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.applyCompress("90");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void colorCorrectImageTest() {
    String expectedString = "Operation with name 'color-correct' has been created\n" +
            "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.applyColorCorrect(false, "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void downscaleImageTest() {
    String expectedString = "Operation with name 'downscale' has been created\n" +
            "Operation has been executed with the following args: [100, 100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.downScale("100", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void greyScaleImageTest() {
    String expectedString = "Operation with name 'luma' has been created\n" +
            "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "luma", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void invalidGreyScaleImageTest() {
    String expectedString = "luma is not a valid operation.";
    StringBuilder log = new StringBuilder();

    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.applyGreyScale(false, "luma", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void adjustLevelsTest() {
    String expectedString = "Operation with name 'levels-adjust' has been created\n" +
            "Operation has been executed with the following args: [20, 40, 60]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactoryMock(log));
    guiController.adjustLevels(false, "20", "40", "60");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void loadInvalidArgumentsTest() {
    String expectedString = "Invalid image format: /invalid";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.loadImage("/invalid", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void saveInvalidArgumentsTest() {
    String expectedString = "Unable to save the file: /invalid";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.saveImage("/invalid");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterInvalidArgumentsTest() {
    String expectedString = "Image not found in library: Please load the image before accessing";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.applyFilter(false, "horizontal-flip", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void compressInvalidArgumentsTest() {
    String expectedString = "Compression ratio must be between 0 and 100";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyCompress("-20");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterWithPreviewInvalidArgumentsTest() {
    String expectedString = "Percentage value for split line must be between 0 and 100.";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyFilter(true, "sepia", "150");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void visualizeWithPreviewInvalidArgumentsTest() {
    String expectedString = "Percentage value for split line must be between 0 and 100.";
    StringBuilder log = new StringBuilder();
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "luma-component", "150");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void downscaleInvalidArgumentsTest() {
    String expectedString = "Invalid dimensions for downscaling. Provided dimensions: 150x150, " +
            "Original dimensions: 2x2";
    StringBuilder log = new StringBuilder();
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.downScale("150", "150");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void levelsAdjustmentInvalidArgumentsTest() {
    String expectedString = "Values should be in ascending order";
    StringBuilder log = new StringBuilder();
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.adjustLevels(false, "50", "10", "5");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void isLoadedNotSavedTest() {
    String expectedString = "";
    StringBuilder log = new StringBuilder();
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "luma-component", "100");
    assertTrue(guiController.isLoadedAndNotSaved());
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void saveImageAfterApplyingOperationTest() {
    String expectedString = "";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
            new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyCompress("10");
    guiController.saveImage(resDirPath + "testImageCompressed10.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageCompressed10.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1513261, 1184256, 1513261, 1842271};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  private void printPixels(Image actualImage) {
    System.out.printf("{%d,%d,%d,%d}", actualImage.getPixel(0, 0).getColorComponents()
            , actualImage.getPixel(0, 1).getColorComponents()
            , actualImage.getPixel(1, 0).getColorComponents()
            , actualImage.getPixel(1, 1).getColorComponents());
  }

  class ImageEditorFrameMock implements ImageEditorView {

    private final StringBuilder log;

    public ImageEditorFrameMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void addFeatures(Features features) {

    }

    @Override
    public void setImage(BufferedImage image) {

    }

    @Override
    public void setHistogram(BufferedImage histogram) {

    }

    @Override
    public void showErrorMessageDialog(String message, String title) {
      log.append(message);
    }

    @Override
    public void showWarningMessageBeforeLoading(String imagePath) {

    }

    @Override
    public void cleanSlate() {

    }
  }

  class GUIImageOperationFactoryMock implements OperationCreator {

    private final StringBuilder log;

    public GUIImageOperationFactoryMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public CLIOperation createOperation(String commandName) {
      log.append(String.format("Operation with name '%s' has been created", commandName));
      log.append("\n");
      return new GUIOperationMock(log);
    }
  }

  class GUIOperationMock implements CLIOperation {

    private final StringBuilder log;

    public GUIOperationMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void execute(String... args) throws IllegalArgumentException {
      log.append(String.format("Operation has been executed with the following args: %s",
              Arrays.toString(args)));
      log.append("\n");
    }
  }
}