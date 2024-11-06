import static org.junit.Assert.assertEquals;

import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.controller.operation.ImageOperationFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Objects;
import org.junit.Test;

public class HistogramTest {

  public void runTest(String commandScript) {
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
  }

  @Test
  public void histogramTest() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("boston.png")
        .append(" ")
        .append("test");
    commandScript.append("\n");
    System.out.println(resDirPath);
    commandScript
        .append("histogram")
        .append(" ")
        .append("test")
        .append(" ")
        .append("result")
        .append("\n")
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("histogram-actual.png")
        .append(" ")
        .append("result")
        .append("\n")
        .append("exit");

    runTest(commandScript.toString());
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualImage;
    Image expectedImage;
    try {
      actualImage = imageReader.read(resDirPath + "histogram-actual.png", ImageType.RGB);
      expectedImage = imageReader.read(resDirPath + "histogram-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testLevelAdjustment() {

    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("testImage.png")
        .append(" ")
        .append("test");
    commandScript.append("\n");
    System.out.println(resDirPath);
    commandScript
        .append("levels-adjust 0 100 200")
        .append(" ")
        .append("test")
        .append(" ")
        .append("result")
        .append("\n")
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("level-adjusted-actual.png")
        .append(" ")
        .append("result")
        .append("\n")
        .append("exit");

    runTest(commandScript.toString());
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualLevelAdjusted;
    Image expectedLevelAdjusted;
    try {
      actualLevelAdjusted =
          imageReader.read(resDirPath + "level-adjusted-actual.png", ImageType.RGB);
      expectedLevelAdjusted =
          imageReader.read(resDirPath + "level-adjusted-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualLevelAdjusted, expectedLevelAdjusted);
  }

  @Test
  public void testVisualizePreviewRedComponent() {

    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
        .append("\n")
        .append("red-component test result 50")
        .append("\n")
        .append("save ")
        .append(resDirPath)
        .append("red-component-preview.png ")
        .append("result")
        .append("\n")
        .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "red-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());
      System.out.println(
          actualImage.getPixel(0, 0).getRed()
              + ","
              + actualImage.getPixel(0, 0).getGreen()
              + ","
              + actualImage.getPixel(0, 0).getBlue());
      System.out.println(
          actualImage.getPixel(0, 1).getRed()
              + ","
              + actualImage.getPixel(0, 1).getGreen()
              + ","
              + actualImage.getPixel(0, 1).getBlue());
      System.out.println(
          actualImage.getPixel(1, 0).getRed()
              + ","
              + actualImage.getPixel(1, 0).getGreen()
              + ","
              + actualImage.getPixel(1, 0).getBlue());
      System.out.println(
          actualImage.getPixel(1, 1).getRed()
              + ","
              + actualImage.getPixel(1, 1).getGreen()
              + ","
              + actualImage.getPixel(1, 1).getBlue());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testVisualizePreviewGreenComponent() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("green-component test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("green-component-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "green-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testVisualizePreviewBlueComponent() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("blue-component test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("blue-component-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "blue-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testVisualizePreviewIntensityComponent() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("intensity-component test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("intensity-component-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "intensity-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testVisualizePreviewLumaComponent() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("luma-component test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("luma-component-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "luma-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1250067, 1315860, 1250067, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testVisualizePreviewValueComponent() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("value-component test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("value-component-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "value-component-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testFilterPreviewBlur() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("blur test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("blur-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "blur-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {723729, 1315860, 789527, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testFilterPreviewSharpen() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("sharpen test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("sharpen-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "sharpen-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {2434364, 1315860, 2434364, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testFilterPreviewSepia() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("sepia test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("sepia-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "sepia-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1775635, 1315860, 1775635, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testLevelAdjustPreview() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("levels-adjust 10 80 250 test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("lvl-adjust-preview.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "lvl-adjust-preview.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testLevelAdjustWithoutPreview() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("levels-adjust 10 80 250 test result")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("lvl-adjust-comb1.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "lvl-adjust-comb1.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 2697653};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testLevelAdjustBMWChanged() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("levels-adjust 100 80 250 test result")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("lvl-adjust-comb2.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "lvl-adjust-comb2.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {16777215, 16777215, 16777215, 16776960};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testLevelAdjustBMWChanged2() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("levels-adjust 250 80 100 test result")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("lvl-adjust-comb3.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "lvl-adjust-comb3.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {0, 0, 0, 255};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testColorCorrectionPreview() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("color-correct test result 50")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("correction.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "correction.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testColorCorrection() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load ").append(resDirPath).append("testImage.png ").append("test");
    commandScript
            .append("\n")
            .append("color-correct test result")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("correction-expected.png ")
            .append("result")
            .append("\n")
            .append("exit");

    runTest(commandScript.toString());
    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(resDirPath + "correction-expected.png", ImageType.RGB);
      int[] expectedPixelValue = new int[] {1315860, 1315860, 1315860, 1973880};

      System.out.println(actualImage.getPixel(0, 0).getColorComponents());
      System.out.println(actualImage.getPixel(0, 1).getColorComponents());
      System.out.println(actualImage.getPixel(1, 0).getColorComponents());
      System.out.println(actualImage.getPixel(1, 1).getColorComponents());

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }
}