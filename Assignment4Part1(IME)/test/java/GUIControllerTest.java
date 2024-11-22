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
    String expectedString = "Added features\n" + "Operation with name 'load' has been created\n"
        + "Operation has been executed with the following args: [/test/path/image.png]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.loadImage("/test/path/image.png", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void saveImageTest() {
    String expectedString = "Added features\n" + "Operation with name 'save' has been created\n"
        + "Operation has been executed with the following args: [/test/path/image.png]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.saveImage("/test/path/image.png");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void horizontalFlipImageTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'horizontal-flip' has been created\n"
            + "Operation has been executed with the following args: []\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.flipImage("horizontal-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void verticalFlipImageTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'vertical-flip' has been created\n"
            + "Operation has been executed with the following args: []\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.flipImage("vertical-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterImageTestWithoutPreview() {
    String expectedString = "Added features\n" + "Operation with name 'filter' has been created\n"
        + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(false, "filter", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterImageTestWithPreview() {
    String expectedString = "Added features\n" + "Operation with name 'filter' has been created\n"
        + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(true, "filter", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void blurImageTestWithoutPreview() {
    String expectedString = "Added features\n" + "Operation with name 'blur' has been created\n"
        + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(false, "blur", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void sharpenImageTestWithoutPreview() {
    String expectedString = "Added features\n" + "Operation with name 'sepia' has been created\n"
        + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyFilter(false, "sepia", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void redComponentImageTestWithoutPreview() {
    String expectedString =
        "Added features\n" + "Operation with name 'red-component' has been created\n"
            + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "red-component", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void blueComponentImageTestWithoutPreview() {
    String expectedString =
        "Added features\n" + "Operation with name 'blue-component' has been created\n"
            + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "blue-component", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void greenComponentImageTestWithoutPreview() {
    String expectedString =
        "Added features\n" + "Operation with name 'green-component' has been created\n"
            + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "green-component", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void lumaComponentImageTestWithoutPreview() {
    String expectedString =
        "Added features\n" + "Operation with name 'luma-component' has been created\n"
            + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "luma-component", "100");
    assertEquals(expectedString, log.toString());
  }


  @Test
  public void compressImageTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'compress' has been created\n"
            + "Operation has been executed with the following args: [90]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyCompress("90");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void colorCorrectImageTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'color-correct' has been created\n"
            + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyColorCorrect(false, "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void downscaleImageTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'downscale' has been created\n"
            + "Operation has been executed with the following args: [100, 100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.downScale("100", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void greyScaleImageTest() {
    String expectedString = "Added features\n" + "Operation with name 'luma' has been created\n"
        + "Operation has been executed with the following args: [100]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.applyGreyScale(false, "luma", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void invalidGreyScaleImageTest() {
    String expectedString = "Added features\n" + "luma is not a valid operation.";
    StringBuilder log = new StringBuilder();

    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.applyGreyScale(false, "luma", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void adjustLevelsTest() {
    String expectedString =
        "Added features\n" + "Operation with name 'levels-adjust' has been created\n"
            + "Operation has been executed with the following args: [20, 40, 60]\n";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactoryMock(log));
    guiController.adjustLevels(false, "20", "40", "60");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void loadInvalidArgumentsTest() {
    String expectedString = "Added features\n" + "Invalid image format: /invalid";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.loadImage("/invalid", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void loadFailedTest() {
    String expectedString =
        "Added features\n" + "Error reading image file: /doesNotExist.png. Please ensure the file "
            + "exists and is a valid image format.";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage("/doesNotExist.png", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void saveInvalidArgumentsTest() {
    String expectedString = "Added features\n" + "Unable to save the file: /invalid";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.saveImage("/invalid");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterInvalidArgumentsTest() {
    String expectedString =
        "Added features\n" + "Image not found in library: Please load the image before accessing";
    StringBuilder log = new StringBuilder();
    Features guiController = new GUIController(new ImageEditorFrameMock(log),
        new GUIImageOperationFactory(new ImageEditorFrameMock(log)));
    guiController.applyFilter(false, "horizontal-flip", "100");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void compressInvalidArgumentsTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Compression ratio must be between 0 and 100";
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
    String expectedString = "Added features\n" + "Created Histogram\n"
        + "Set Image\nPercentage value for split line must be between 0 and 100.";
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
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Percentage value for split line must be between 0 and 100.";
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
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Invalid dimensions for downscaling. Provided dimensions: 150x150, "
        + "Original dimensions: 2x2";
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
  public void levelsAdjustmentInvalidArgumentsTest1() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Values should be in ascending order";
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
  public void levelsAdjustmentInvalidArgumentsTest2() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Values should be between 0 to 255 only.";
    StringBuilder log = new StringBuilder();
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.adjustLevels(false, "-50", "10", "280");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void isLoadedNotSavedTest() {
    String expectedString =
        "Added features\n" + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n";
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
    String expectedString =
        "Added features\n" + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n";
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

  @Test
  public void applyHFlipTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.flipImage("horizontal-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void applyVFlipTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.flipImage("vertical-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void colorCorrectionTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyColorCorrect(false, "100");
    guiController.saveImage(resDirPath + "testImageColorCorrected.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageColorCorrected.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void flipInvalidTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "invalid-flip is not a valid operation.";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.flipImage("invalid-flip");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void filterInvalidTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "invalid-filter is not a valid operation.";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.flipImage("invalid-filter");
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void isSavedPromptTest() {
    String expectedString =
        "Added features\n" + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
            + "Save before loading an image - prompt";
    StringBuilder log = new StringBuilder();
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "luma-component", "100");
    assertTrue(guiController.isLoadedAndNotSaved());
    guiController.loadImage(resDirPath + "testImage.png", false);
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void colorCorrectionWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyColorCorrect(true, "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageColorCorrected.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageColorCorrected.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void sepiaWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyFilter(true, "sepia", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageSepia.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageSepia.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1775635, 1775635, 1775635, 3814184};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void sepiaWithPreviewAndNotApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyFilter(true, "sepia", "100");
    //guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageSepia.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageSepia.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void greyScaleWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "luma-component", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageGreyScale.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1250067, 1250067, 1250067, 2368548};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void redComponentWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "red-component", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageGreyScale.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973790};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void greenComponentWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "green-component", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageGreyScale.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973790};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void blueComponentWithPreviewAndApplyTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "blue-component", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageGreyScale.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 7895160};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void testHistogramGeneration() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(true, "blue-component", "100");
    guiController.applyPreviewChanges();
    guiController.exitPreviewMode();
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    assertEquals(expectedString, log.toString());
  }


  @Test
  public void guiMultipleOperationsTest() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n" + "Created Histogram\nSet Image\n"
        + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.applyGreyScale(false, "luma-component", "100");
    guiController.applyFilter(false, "blur", "100");
    guiController.adjustLevels(false, "40", "60", "80");
    guiController.saveImage(resDirPath + "testImageGreyScale.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageGreyScale.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{0, 0, 0, 0};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  @Test
  public void testSimpleLoadAndSave() {
    String expectedString = "Added features\n" + "Created Histogram\nSet Image\n";
    StringBuilder log = new StringBuilder();
    ImageEditorView imageEditorViewMock = new ImageEditorFrameMock(log);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Features guiController = new GUIController(imageEditorViewMock,
        new GUIImageOperationFactory(imageEditorViewMock));
    guiController.loadImage(resDirPath + "testImage.png", false);
    guiController.saveImage(resDirPath + "testImageSave.png");
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      Image actualImage;
      actualImage = imageReader.read(resDirPath + "testImageSave.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};
      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedString, log.toString());
  }

  class ImageEditorFrameMock implements ImageEditorView {

    private final StringBuilder log;

    public ImageEditorFrameMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void addFeatures(Features features) {
      log.append("Added features");
      log.append("\n");
    }

    @Override
    public void setImage(BufferedImage image) {
      log.append("Set Image");
      log.append("\n");
    }

    @Override
    public void setHistogram(BufferedImage histogram) {
      log.append("Created Histogram");
      log.append("\n");
    }

    @Override
    public void showErrorMessageDialog(String message, String title) {
      log.append(message);
    }

    @Override
    public void showWarningMessageBeforeLoading(String imagePath) {
      log.append("Save before loading an image - prompt");
    }

    @Override
    public void cleanSlate() {
      log.append("Refreshed the UI");
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