import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

import ime.controller.cli.ImageProcessorCLI;
import ime.imageio.ImageFormat;
import ime.imageio.ImageReader;
import ime.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionalities of Image Manipulation and Enhancement
 * application.
 */
public class ImageOperationTest {
  @Test
  public void testMultipleOperationsPNG() {
    String inputImagePath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource("boston.png")).getPath();
    String outputImagePath = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load").append(" ").append(inputImagePath).append(" ").append("boston");
    commandScript.append("\n");
    commandScript.append("red-component").append(" ").append("boston").append(" ")
            .append("boston-red").append("\n");
    commandScript.append("green-component").append(" ").append("boston").append(" ")
            .append("boston-green").append("\n");
    commandScript.append("blue-component").append(" ").append("boston").append(" ")
            .append("boston-blue").append("\n");
    commandScript.append("rgb-combine").append(" ").append("boston-rgb").append(" ")
            .append("boston-red").append(" ")
            .append("boston-green").append(" ")
            .append("boston-blue").append("\n");
    commandScript.append("horizontal-flip").append(" ").append("boston-rgb").append(" ")
            .append("boston-rgb-horizontal").append("\n");
    commandScript.append("vertical-flip").append(" ").append("boston-rgb-horizontal").append(" ")
            .append("boston-rgb-vertical").append("\n");
    commandScript.append("rgb-split").append(" ").append("boston").append(" ")
            .append("boston-split-red").append(" ").append("boston-split-green").append(" ")
            .append("boston-split-blue").append("\n");
    commandScript.append("brighten").append(" ").append("10").append(" ").append("boston-rgb")
            .append(" ").append("boston-rgb-brighten").append("\n");
    commandScript.append("darken").append(" ").append("10").append(" ").append("boston-rgb")
            .append(" ").append("boston-rgb-darken").append("\n");
    commandScript.append("blur").append(" ").append("boston").append(" ").append("boston-blur")
            .append("\n");
    commandScript.append("sharpen").append(" ").append("boston").append(" ").append("boston-sharpen")
            .append("\n");
    commandScript.append("sepia").append(" ").append("boston").append(" ").append("boston-sepia")
            .append("\n");
    commandScript.append("luma-component").append(" ").append("boston").append(" ")
            .append("boston-luma").append("\n");
    commandScript.append("value-component").append(" ").append("boston").append(" ")
            .append("boston-value").append("\n");
    commandScript.append("intensity-component").append(" ").append("boston").append(" ")
            .append("boston-intensity").append("\n").
            append("save").append(" ").append(outputImagePath).append("/boston-actual.png")
            .append(" ").append("boston-rgb")
            .append("\n").append("exit");
    ByteArrayInputStream inputStream = new ByteArrayInputStream(commandScript.toString().getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualImage;
    try {
      actualImage = imageReader.read(outputImagePath + "/boston-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read actual image file: " +
              outputImagePath + "/boston-actual.png", e);
    }

    Image expectedImage;
    try {
      expectedImage = imageReader.read(inputImagePath, ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read expected image file: " +
              inputImagePath, e);
    }
    assertEquals(actualImage, expectedImage);

  }
}
