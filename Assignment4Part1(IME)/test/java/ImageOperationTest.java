import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ime.controller.cli.ImageProcessorCLI;
import ime.controller.operation.Save;
import ime.imageio.ImageFormat;
import ime.imageio.ImageReader;
import ime.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageLibrary;
import ime.model.image.ImageType;
import ime.model.operation.Blur;
import ime.model.operation.Combine;
import ime.model.operation.Filter;
import ime.model.operation.ImageOperation;
import ime.model.operation.MultipleImageOperation;
import ime.model.operation.Sharpen;
import ime.model.operation.VisualizeBlue;
import ime.model.operation.VisualizeGreen;
import ime.model.operation.VisualizeRed;
import jdk.dynalink.Operation;

import static java.awt.Color.red;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** This class tests the functionalities of Image Manipulation and Enhancement application. */
public class ImageOperationTest {
  @Test
  public void testMultipleOperationsPNG() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("boston.png")
        .append(" ")
        .append("boston");
    commandScript.append("\n");
    commandScript
        .append("red-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-red")
        .append("\n");
    commandScript
        .append("green-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-green")
        .append("\n");
    commandScript
        .append("blue-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("rgb-combine")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-red")
        .append(" ")
        .append("boston-green")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("horizontal-flip")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append("\n");
    commandScript
        .append("vertical-flip")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n");
    commandScript
        .append("rgb-split")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-split-red")
        .append(" ")
        .append("boston-split-green")
        .append(" ")
        .append("boston-split-blue")
        .append("\n");
    commandScript
        .append("brighten")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-brighten")
        .append("\n");
    commandScript
        .append("darken")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-darken")
        .append("\n");
    commandScript
        .append("blur")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blur")
        .append("\n");
    commandScript
        .append("sharpen")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sharpen")
        .append("\n");
    commandScript
        .append("sepia")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sepia")
        .append("\n");
    commandScript
        .append("luma-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-luma")
        .append("\n");
    commandScript
        .append("value-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-value")
        .append("\n");
    commandScript
        .append("intensity-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-intensity")
        .append("\n")
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-rgb-actual.png")
        .append(" ")
        .append("boston-rgb")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-hv-actual.png")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n")
        .append("exit");
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(commandScript.toString().getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;
    Image actualHVFlipImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-rgb-actual.png", ImageType.RGB);
      actualHVFlipImage = imageReader.read(resDirPath + "boston-hv-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    Image expectedHVFlipImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);
      expectedHVFlipImage = imageReader.read(resDirPath + "boston-hv-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
    assertEquals(actualHVFlipImage, expectedHVFlipImage);
  }

  @Test
  public void testMultipleOperationsJPG() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("boston.jpg")
        .append(" ")
        .append("boston");
    commandScript.append("\n");
    commandScript
        .append("red-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-red")
        .append("\n");
    commandScript
        .append("green-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-green")
        .append("\n");
    commandScript
        .append("blue-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("rgb-combine")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-red")
        .append(" ")
        .append("boston-green")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("horizontal-flip")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append("\n");
    commandScript
        .append("vertical-flip")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n");
    commandScript
        .append("rgb-split")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-split-red")
        .append(" ")
        .append("boston-split-green")
        .append(" ")
        .append("boston-split-blue")
        .append("\n");
    commandScript
        .append("brighten")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-brighten")
        .append("\n");
    commandScript
        .append("darken")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-darken")
        .append("\n");
    commandScript
        .append("blur")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blur")
        .append("\n");
    commandScript
        .append("sharpen")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sharpen")
        .append("\n");
    commandScript
        .append("sepia")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sepia")
        .append("\n");
    commandScript
        .append("luma-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-luma")
        .append("\n");
    commandScript
        .append("value-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-value")
        .append("\n");
    commandScript
        .append("intensity-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-intensity")
        .append("\n")
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-rgb-actual.jpg")
        .append(" ")
        .append("boston-rgb")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-hv-actual.jpg")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n")
        .append("exit");
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(commandScript.toString().getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    Image actualHVFlipImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-rgb-actual.jpg", ImageType.RGB);
      actualHVFlipImage = imageReader.read(resDirPath + "boston-hv-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    Image expectedHVFlipImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston.jpg", ImageType.RGB);
      expectedHVFlipImage = imageReader.read(resDirPath + "boston-hv-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
    assertEquals(actualHVFlipImage, expectedHVFlipImage);
  }

  @Test
  public void testMultipleOperationsPPM() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("boston.ppm")
        .append(" ")
        .append("boston");
    commandScript.append("\n");
    commandScript
        .append("red-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-red")
        .append("\n");
    commandScript
        .append("green-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-green")
        .append("\n");
    commandScript
        .append("blue-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("rgb-combine")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-red")
        .append(" ")
        .append("boston-green")
        .append(" ")
        .append("boston-blue")
        .append("\n");
    commandScript
        .append("horizontal-flip")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append("\n");
    commandScript
        .append("vertical-flip")
        .append(" ")
        .append("boston-rgb-horizontal")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n");
    commandScript
        .append("rgb-split")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-split-red")
        .append(" ")
        .append("boston-split-green")
        .append(" ")
        .append("boston-split-blue")
        .append("\n");
    commandScript
        .append("brighten")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-brighten")
        .append("\n");
    commandScript
        .append("darken")
        .append(" ")
        .append("10")
        .append(" ")
        .append("boston-rgb")
        .append(" ")
        .append("boston-rgb-darken")
        .append("\n");
    commandScript
        .append("blur")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blur")
        .append("\n");
    commandScript
        .append("sharpen")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sharpen")
        .append("\n");
    commandScript
        .append("sepia")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sepia")
        .append("\n");
    commandScript
        .append("luma-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-luma")
        .append("\n");
    commandScript
        .append("value-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-value")
        .append("\n");
    commandScript
        .append("intensity-component")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-intensity")
        .append("\n")
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-rgb-actual.ppm")
        .append(" ")
        .append("boston-rgb")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-hv-actual.ppm")
        .append(" ")
        .append("boston-rgb-horizontal-vertical")
        .append("\n")
        .append("exit");
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(commandScript.toString().getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PPM);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston.ppm", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston.ppm", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testFromCLI() {

    // Load the script file from resources
    URL inputURL = getClass().getClassLoader().getResource("script");
    if (inputURL == null) {
      throw new RuntimeException("Test script not found");
    }

    Path inputScriptPath;
    try {
      // Convert the URL to a Path object
      inputScriptPath = Paths.get(inputURL.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    // Read the content of the file
    List<String> fileContent;
    try {
      fileContent = Files.readAllLines(inputScriptPath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Error reading the script file", e);
    }

    // Replace a certain string (example: replace "oldString" with "newString")
    String oldString = "<inputFilePath>";
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    fileContent.replaceAll(s -> s.replace(oldString, resDirPath));

    // Write the modified content back to the same file
    try {
      Files.write(inputScriptPath, fileContent, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Error writing back to the script file", e);
    }

    System.out.println("File updated successfully: " + inputScriptPath);

    Assert.assertNotNull("Test script not found", inputURL);
    try {
      inputScriptPath = Paths.get(inputURL.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    String command = "run " + inputScriptPath + "\n" + "exit";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(command.getBytes());
    System.setIn(inputStream);
    new ImageProcessorCLI(inputStream).run();

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualImage;
    try {
      actualImage = imageReader.read(resDirPath + "red.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-red-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read expected image file", e);
    }
    assertEquals(actualImage, expectedRGBImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments(){

    MultipleImageOperation combine = new Combine();

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualImage;

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();

    try {
      actualImage = imageReader.read(resDirPath + "boston-red-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    combine.apply(Arrays.asList(actualImage,actualImage));
    fail("Must Raise Exception as Illegal Argument is passed.");
  }

  @Test
  public void testVisualize(){

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Image actualImage;
    Image expectedRed;

    try {
      actualImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);
      expectedRed = imageReader.read(resDirPath + "boston-red-expected.png", ImageType.RGB);

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    ImageOperation redComponent = new VisualizeRed();
    ImageOperation greenComponent = new VisualizeGreen();
    ImageOperation blueComponent = new VisualizeBlue();

    Image red = redComponent.apply(actualImage);
    Image green = greenComponent.apply(actualImage);
    Image blue = blueComponent.apply(actualImage);

    assertEquals(expectedRed,red);

    MultipleImageOperation combine = new Combine();
    Image expectedCombined = combine.apply(Arrays.asList(red,green,blue));

    assertEquals(expectedCombined,actualImage);

  }

  @Test
  public void testFilter(){

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Image actualImage;
    Image expectedBlurImage;
    Image expectedSharpenImage;

    try {
      actualImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);
      expectedBlurImage = imageReader.read(resDirPath + "boston-blur-expected.png", ImageType.RGB);
      expectedSharpenImage = imageReader.read(resDirPath + "boston-sharpen-expected.png", ImageType.RGB);

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    ImageOperation blur = new Blur();
    ImageOperation sharpen = new Sharpen();

    Image blurredImage = blur.apply(actualImage);
    Image sharpendImage = sharpen.apply(actualImage);

    assertEquals(expectedBlurImage,blurredImage);
    assertEquals(expectedSharpenImage,sharpendImage);

  }





}
