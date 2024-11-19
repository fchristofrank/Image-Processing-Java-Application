import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import ime.controller.Features;
import ime.controller.GUIController;
import ime.controller.cli.OperationCreator;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.GUIImageOperationFactory;
import ime.view.ImageEditorView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionalities of the GUI controller.
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
    String expectedString = "Image not found in library: Please load the image before accessing";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
            new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.applyCompress("-20");
    assertEquals(expectedString, log.toString());
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