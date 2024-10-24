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
    runTestForImageFormat("png", ImageFormat.PNG);
  }

  @Test
  public void testMultipleOperationsJPG() {
    runTestForImageFormat("jpg", ImageFormat.JPG);
  }

  @Test
  public void testMultipleOperationsPPM() {
    runTestForImageFormat("ppm", ImageFormat.PPM);
  }

  private void runTestForImageFormat(String extension, ImageFormat format) {
    String resDirPath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource("")).getPath();
    String commandScript = createCommandScript(resDirPath, extension);

    ByteArrayInputStream inputStream = new ByteArrayInputStream(commandScript.getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();

    Image actualRGBImage = readImage(resDirPath + "boston-rgb-actual." + extension, format);
    Image actualHVFlipImage = readImage(resDirPath + "boston-hv-actual." + extension, format);

    Image expectedRGBImage = readImage(resDirPath + "boston." + extension, format);
    Image expectedHVFlipImage = readImage(resDirPath + "boston-hv-expected." + extension, format);

    assertEquals(actualRGBImage, expectedRGBImage);
    assertEquals(actualHVFlipImage, expectedHVFlipImage);
  }

  private String createCommandScript(String resDirPath, String extension) {
    return "load " + resDirPath + "boston." + extension + " boston\n" +
            "red-component boston boston-red\n" +
            "green-component boston boston-green\n" +
            "blue-component boston boston-blue\n" +
            "rgb-combine boston-rgb boston-red boston-green boston-blue\n" +
            "horizontal-flip boston-rgb boston-rgb-horizontal\n" +
            "vertical-flip boston-rgb-horizontal boston-rgb-horizontal-vertical\n" +
            "rgb-split boston boston-split-red boston-split-green boston-split-blue\n" +
            "brighten 10 boston-rgb boston-rgb-brighten\n" +
            "darken 10 boston-rgb boston-rgb-darken\n" +
            "blur boston boston-blur\n" +
            "sharpen boston boston-sharpen\n" +
            "sepia boston boston-sepia\n" +
            "luma-component boston boston-luma\n" +
            "value-component boston boston-value\n" +
            "intensity-component boston boston-intensity\n" +
            "save " + resDirPath + "boston-rgb-actual." + extension +
            " boston-rgb\n" +
            "save " + resDirPath + "boston-hv-actual." + extension +
            " boston-rgb-horizontal-vertical\n" +
            "exit";
  }

  private Image readImage(String filePath, ImageFormat format) {
    ImageReader imageReader = ImageReaderFactory.createReader(format);
    try {
      return imageReader.read(filePath, ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file: " + filePath, e);
    }
  }
}
