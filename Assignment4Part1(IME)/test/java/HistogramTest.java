import static org.junit.Assert.assertEquals;

import ime.controller.operation.ImageOperationFactory;
import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.operation.CountFrequency;
import ime.model.operation.Histogram;
import ime.model.operation.ImageOperation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Objects;
import org.junit.Test;

public class HistogramTest {

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

    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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

    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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
  public void testVisualizePreview(){

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load ")
            .append(resDirPath)
            .append("testImage.png ")
            .append("test");
    commandScript.append("\n");

    commandScript
            .append("red-component ")
            .append("test ")
            .append("result")
            .append("\n")
            .append("save ")
            .append(resDirPath)
            .append("level-adjusted-actual.png ")
            .append("result")
            .append("\n")
            .append("exit");

    Image actualImage;
    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage =
              imageReader.read(resDirPath + "level-adjusted-actual.png", ImageType.RGB);
      System.out.println(actualImage.getPixel(0,0).getColorComponents());
      System.out.println(actualImage.getPixel(0,1).getColorComponents());
      System.out.println(actualImage.getPixel(1,0).getColorComponents());
      System.out.println(actualImage.getPixel(1,1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    }
}
