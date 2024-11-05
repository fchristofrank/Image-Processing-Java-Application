import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ime.controller.operation.ImageOperationFactory;
import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.operation.Blur;
import ime.model.operation.Combine;
import ime.model.operation.ImageOperation;
import ime.model.operation.MultipleImageOperation;
import ime.model.operation.Sharpen;
import ime.model.operation.VisualizeBlue;
import ime.model.operation.VisualizeGreen;
import ime.model.operation.VisualizeRed;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests the functionalities of Image Manipulation and Enhancement application.
 */
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
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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
    Readable readableInput = new StringReader(command);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
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
  public void testInvalidArguments() {

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

    combine.apply(Arrays.asList(actualImage, actualImage));
    fail("Must Raise Exception as Illegal Argument is passed.");
  }

  @Test
  public void testVisualize() {

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

    assertEquals(expectedRed, red);

    MultipleImageOperation combine = new Combine();
    Image expectedCombined = combine.apply(Arrays.asList(red, green, blue));

    assertEquals(expectedCombined, actualImage);
  }

  @Test
  public void testFilter() {

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Image actualImage;
    Image expectedBlurImage;
    Image expectedSharpenImage;

    try {
      actualImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);
      expectedBlurImage = imageReader.read(resDirPath + "boston-blur-expected.png", ImageType.RGB);
      expectedSharpenImage =
          imageReader.read(resDirPath + "boston-sharpen-expected.png", ImageType.RGB);

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    ImageOperation blur = new Blur();
    ImageOperation sharpen = new Sharpen();

    Image blurredImage = blur.apply(actualImage);
    Image sharpendImage = sharpen.apply(actualImage);

    assertEquals(expectedBlurImage, blurredImage);
    assertEquals(expectedSharpenImage, sharpendImage);
  }

  @Test
  public void testBrightenPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("brighten")
            .append(" ")
            .append("25")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-brighten")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-brighten-actual.png")
            .append(" ")
            .append("boston-brighten")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;

    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-brighten-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage =
              imageReader.read(resDirPath + "boston-brighten-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testBrightenJPG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.jpg")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("brighten")
            .append(" ")
            .append("25")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-brighten")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-brighten-actual.jpg")
            .append(" ")
            .append("boston-brighten")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-brighten-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage =
              imageReader.read(resDirPath + "boston-brighten-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testDarkenPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("darken")
            .append(" ")
            .append("25")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-darken")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-darken-actual.png")
            .append(" ")
            .append("boston-darken")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-darken-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-darken-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testDarkenJPG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.jpg")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("darken")
            .append(" ")
            .append("25")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-darken")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-darken-actual.jpg")
            .append(" ")
            .append("boston-darken")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-darken-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-darken-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testSepiaPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("sepia")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-sepia")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-sepia-actual.png")
            .append(" ")
            .append("boston-sepia")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-sepia-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-sepia-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testSepiaJPG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.jpg")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("sepia")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-sepia")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-sepia-actual.jpg")
            .append(" ")
            .append("boston-sepia")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-sepia-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-sepia-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testHFlipPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("horizontal-flip")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-hflip")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-hflip-actual.png")
            .append(" ")
            .append("boston-hflip")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-hflip-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-hflip-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testHFlipJPG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.jpg")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("horizontal-flip")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-hflip")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-hflip-actual.jpg")
            .append(" ")
            .append("boston-hflip")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-hflip-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-hflip-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testVFlipPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("vertical-flip")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-vflip")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-vflip-actual.png")
            .append(" ")
            .append("boston-vflip")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-vflip-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-vflip-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testVFlipJPG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.jpg")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("vertical-flip")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-vflip")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-vflip-actual.jpg")
            .append(" ")
            .append("boston-vflip")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-vflip-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage = imageReader.read(resDirPath + "boston-vflip-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  @Test
  public void testRGBSplitPNG() {
    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
            .append("load")
            .append(" ")
            .append(resDirPath)
            .append("boston.png")
            .append(" ")
            .append("boston")
            .append("\n");
    commandScript
            .append("rgb-split")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-red")
            .append(" ")
            .append("boston-green")
            .append(" ")
            .append("boston-blue")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-red-actual.png")
            .append(" ")
            .append("boston-red")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-green-actual.png")
            .append(" ")
            .append("boston-green")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-blue-actual.png")
            .append(" ")
            .append("boston-blue")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualRedImage;
    Image actualGreenImage;
    Image actualBlueImage;
    try {
      actualRedImage = imageReader.read(resDirPath + "boston-red-actual.png", ImageType.RGB);
      actualGreenImage = imageReader.read(resDirPath + "boston-green-actual.png", ImageType.RGB);
      actualBlueImage = imageReader.read(resDirPath + "boston-blue-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRedImage;
    Image expectedGreenImage;
    Image expectedBlueImage;
    try {
      expectedRedImage = imageReader.read(resDirPath + "boston-red-expected.png", ImageType.RGB);
      expectedGreenImage =
              imageReader.read(resDirPath + "boston-green-expected.png", ImageType.RGB);
      expectedBlueImage = imageReader.read(resDirPath + "boston-blue-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRedImage, expectedRedImage);
    assertEquals(actualGreenImage, expectedGreenImage);
    assertEquals(actualBlueImage, expectedBlueImage);
  }

  @Test
  public void testCompression50PercentPNG() {
    String resDirPath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load").append(" ").append(resDirPath).append("test-compress.png")
            .append(" ")
            .append("test").append("\n");
    commandScript.append("compress").append(" ").append("50").append(" ").append("test")
            .append(" ").append("test-compress").append("\n");
    commandScript.append("save").append(" ").append(resDirPath).append("test-compress-actual.png")
            .append(" ").append("test-compress").append("\n").append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage = imageReader.read(resDirPath + "test-compress-actual.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage = imageReader.read(resDirPath + "test-compress-expected.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualCompressedImage, expectedCompressedImage);
  }

  @Test
  public void testCompression100PercentPNG() {
    String resDirPath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load").append(" ").append(resDirPath).append("test-compress.png")
            .append(" ")
            .append("test").append("\n");
    commandScript.append("compress").append(" ").append("100").append(" ").append("test")
            .append(" ").append("test-compress").append("\n");
    commandScript.append("save").append(" ").append(resDirPath).append("test-compress-actual.png")
            .append(" ").append("test-compress").append("\n").append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage = imageReader.read(resDirPath + "test-compress-actual.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage = imageReader.read(resDirPath + "test-compress-expected.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualCompressedImage, expectedCompressedImage);
  }


  @Test
  public void testCompressionZeroPercentPNG() {
    String resDirPath = Objects.requireNonNull(getClass().getClassLoader()
            .getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript.append("load").append(" ").append(resDirPath).append("test-compress.png")
            .append(" ")
            .append("test").append("\n");
    commandScript.append("compress").append(" ").append("0").append(" ").append("test")
            .append(" ").append("test-compress").append("\n");
    commandScript.append("save").append(" ").append(resDirPath).append("test-compress-actual.png")
            .append(" ").append("test-compress").append("\n").append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage = imageReader.read(resDirPath + "test-compress-actual.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage = imageReader.read(resDirPath + "test-compress-expected.png",
              ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualCompressedImage, expectedCompressedImage);
  }


  @Test
  public void testVisualizePPM() {

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("testImage.png")
        .append(" ")
        .append("boston")
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
            .append("\n");
    commandScript
            .append("luma-component")
            .append(" ")
            .append("boston")
            .append(" ")
            .append("boston-luma")
            .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-luma-actual.ppm")
        .append(" ")
        .append("boston-luma")
        .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-value-actual.ppm")
            .append(" ")
            .append("boston-value")
            .append("\n");
    commandScript
            .append("save")
            .append(" ")
            .append(resDirPath)
            .append("boston-intensity-actual.ppm")
            .append(" ")
            .append("boston-intensity")
            .append("\n")
            .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PPM);
    Image lumaImageExpected;
    Image lumaImageActual;
    try {
      lumaImageExpected = imageReader.read(resDirPath + "boston-luma-expected.ppm", ImageType.RGB);
      lumaImageActual = imageReader.read(resDirPath + "boston-luma-actual.ppm", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(lumaImageExpected, lumaImageActual);
        Image intensityExpected;
        Image intensityActual;
        try {
          intensityExpected =
              imageReader.read(resDirPath + "boston-intensity-actual.ppm", ImageType.RGB);
          intensityActual =
              imageReader.read(resDirPath + "boston-intensity-expected.ppm", ImageType.RGB);
        } catch (IOException e) {
          throw new IllegalArgumentException("Failed to read image file", e);
        }
        assertEquals(intensityActual, intensityExpected);

        Image valueExpected;
        Image valueActual;
        try {
          valueActual = imageReader.read(resDirPath + "boston-value-actual.ppm", ImageType.RGB);
          valueExpected = imageReader.read(resDirPath + "boston-value-expected.ppm",
     ImageType.RGB);
        } catch (IOException e) {
          throw new IllegalArgumentException("Failed to read image file", e);
        }
        assertEquals(valueActual, valueExpected);
  }

}
