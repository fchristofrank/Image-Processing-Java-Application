import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.controller.operation.AbstractOperation;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.CommandExecutor;
import ime.controller.operation.ImageLibrary;
import ime.controller.operation.ImageOperationFactory;
import ime.controller.operation.ImageRepo;
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
        .append("testImageMasking.png")
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

    int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 255};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.jpg")
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
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-rgb-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    int[] expectedPixelValue = new int[]{788505, 1709607, 1709607, 2630709};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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

    int[] expectedPixelValue = new int[]{9079434, 9079434, 9079434, 9079434};

    assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArguments() {

    MultipleImageOperation combine = new Combine();

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualImage;

    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();

    try {
      actualImage = imageReader.read(resDirPath + "maskImage.png", ImageType.RGB);
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

    try {
      actualImage = imageReader.read(resDirPath + "testImage.png", ImageType.RGB);

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    ImageOperation redComponent = new VisualizeRed();
    ImageOperation greenComponent = new VisualizeGreen();
    ImageOperation blueComponent = new VisualizeBlue();

    Image red = redComponent.apply(actualImage);
    Image green = greenComponent.apply(actualImage);
    Image blue = blueComponent.apply(actualImage);

    int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

    assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

    MultipleImageOperation combine = new Combine();
    Image expectedCombined = combine.apply(Arrays.asList(red, green, blue));

    expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

    assertEquals(expectedPixelValue[0], expectedCombined.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], expectedCombined.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], expectedCombined.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], expectedCombined.getPixel(1, 1).getColorComponents());
  }

  @Test
  public void testFilter() {

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Image actualImage;

    try {
      actualImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    ImageOperation blur = new Blur();
    ImageOperation sharpen = new Sharpen();

    Image blurredImage = blur.apply(actualImage);
    Image sharpendImage = sharpen.apply(actualImage);

    int[] expectedPixelValue = new int[]{5070723, 6782639, 6782639, 9087466};

    assertEquals(expectedPixelValue[0], blurredImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], blurredImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], blurredImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], blurredImage.getPixel(1, 1).getColorComponents());

    expectedPixelValue = new int[]{16777215, 16777215, 16777215, 16777215};

    assertEquals(expectedPixelValue[0], sharpendImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], sharpendImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], sharpendImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], sharpendImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.png")
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
    int[] expectedPixelValue = new int[]{2960685, 2960685, 2960685, 3618705};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.png")
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

    int[] expectedPixelValue = new int[]{2960685, 2960685, 2960685, 3618705};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.jpg")
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

    int[] expectedPixelValue = new int[]{2433330, 3354432, 3354432, 4275534};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.png")
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

    int[] expectedPixelValue = new int[]{1775635, 1775635, 1775635, 3814184};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
  }

  @Test
  public void testSepiaWithPreviewPNG() {
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
        .append(" ").append("split").append(" ")
        .append("50")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-sepia-wp-actual.png")
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
      actualRGBImage = imageReader.read(resDirPath + "boston-sepia-wp-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage =
          imageReader.read(resDirPath + "boston-sepia-wp-expected.png", ImageType.RGB);
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
        .append("testImage.png")
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

    int[] expectedPixelValue = new int[]{1315860, 1315860, 1973880, 1315860};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.png")
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

    int[] expectedPixelValue = new int[]{1315860, 1973880, 1315860, 1315860};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
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
        .append("testImage.png")
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

    int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 7895160};

    assertEquals(expectedPixelValue[0], actualRedImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualGreenImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRedImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualBlueImage.getPixel(1, 1).getColorComponents());
  }

  @Test
  public void testCompression50PercentPNG() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("test-compress.png")
        .append(" ")
        .append("test")
        .append("\n");
    commandScript
        .append("compress")
        .append(" ")
        .append("50")
        .append(" ")
        .append("test")
        .append(" ")
        .append("test-compress")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("test-compress-actual.png")
        .append(" ")
        .append("test-compress")
        .append("\n")
        .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage =
          imageReader.read(resDirPath + "test-compress-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage =
          imageReader.read(resDirPath + "test-compress-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualCompressedImage, expectedCompressedImage);
  }

  @Test
  public void testCompression100PercentPNG() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("test-compress.png")
        .append(" ")
        .append("test")
        .append("\n");
    commandScript
        .append("compress")
        .append(" ")
        .append("100")
        .append(" ")
        .append("test")
        .append(" ")
        .append("test-compress")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("test-compress-full-actual.png")
        .append(" ")
        .append("test-compress")
        .append("\n")
        .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage =
          imageReader.read(resDirPath + "test-compress-full-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage =
          imageReader.read(resDirPath + "test-compress-full-expected.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualCompressedImage, expectedCompressedImage);
  }

  @Test
  public void testCompressionZeroPercentPNG() {
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    StringBuilder commandScript = new StringBuilder();
    commandScript
        .append("load")
        .append(" ")
        .append(resDirPath)
        .append("test-compress.png")
        .append(" ")
        .append("test")
        .append("\n");
    commandScript
        .append("compress")
        .append(" ")
        .append("0")
        .append(" ")
        .append("test")
        .append(" ")
        .append("test-compress")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("test-no-compress-actual.png")
        .append(" ")
        .append("test-compress")
        .append("\n")
        .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    Image actualCompressedImage;
    try {
      actualCompressedImage =
          imageReader.read(resDirPath + "test-no-compress-actual.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedCompressedImage;
    try {
      expectedCompressedImage =
          imageReader.read(resDirPath + "test-no-compress-expected.png", ImageType.RGB);
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
      valueExpected = imageReader.read(resDirPath + "boston-value-expected.ppm", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(valueActual, valueExpected);
  }

  @Test
  public void testBlurWithPreviewPNG() {
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
        .append("blur")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-blur")
        .append(" ").append("split").append(" ")
        .append("70")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-blur-wp-actual.jpg")
        .append(" ")
        .append("boston-blur")
        .append("\n")
        .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-blur-wp-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    int[] expectedPixelValue = new int[]{36, 1579068, 723759, 2434377};

    assertEquals(expectedPixelValue[0], actualRGBImage.getPixel(0, 0).getColorComponents());
    assertEquals(expectedPixelValue[1], actualRGBImage.getPixel(0, 1).getColorComponents());
    assertEquals(expectedPixelValue[2], actualRGBImage.getPixel(1, 0).getColorComponents());
    assertEquals(expectedPixelValue[3], actualRGBImage.getPixel(1, 1).getColorComponents());
  }

  @Test
  public void testSharpenWithPreviewPNG() {
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
        .append("sharpen")
        .append(" ")
        .append("boston")
        .append(" ")
        .append("boston-sharpen")
        .append(" ").append("split").append(" ")
        .append("70")
        .append("\n");
    commandScript
        .append("save")
        .append(" ")
        .append(resDirPath)
        .append("boston-sharpen-wp-actual.jpg")
        .append(" ")
        .append("boston-sharpen")
        .append("\n")
        .append("exit");
    Readable readableInput = new StringReader(commandScript.toString());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image actualRGBImage;
    try {
      actualRGBImage = imageReader.read(resDirPath + "boston-sharpen-wp-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    Image expectedRGBImage;
    try {
      expectedRGBImage =
          imageReader.read(resDirPath + "boston-sharpen-wp-expected.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    assertEquals(actualRGBImage, expectedRGBImage);
  }

  private void runTest(String commandScript) {
    Readable readableInput = new StringReader(commandScript);
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

    assertEquals(expectedImage.getWidth(), actualImage.getWidth());
    assertEquals(expectedImage.getHeight(), actualImage.getHeight());
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
        .append("red-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());

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
        .append("green-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
        .append("blue-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
        .append("intensity-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
        .append("luma-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1250067, 1315860, 1250067, 1973880};

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
        .append("value-component test result split 50")
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
        .append("blur test result split 50")
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
      int[] expectedPixelValue = new int[]{723729, 1315860, 789527, 1973880};

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
        .append("sharpen test result split 50")
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
      int[] expectedPixelValue = new int[]{2434364, 1315860, 2434364, 1973880};

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
        .append("sepia test result split 50")
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
      int[] expectedPixelValue = new int[]{1775635, 1315860, 1775635, 1973880};

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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 2697653};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test(expected = Exception.class)
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
      int[] expectedPixelValue = new int[]{16777215, 16777215, 16777215, 16776960};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test(expected = Exception.class)
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
      int[] expectedPixelValue = new int[]{0, 0, 0, 255};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    } catch (IllegalArgumentException e) {
      throw e;
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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

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
      int[] expectedPixelValue = new int[]{1315860, 1315860, 1315860, 1973880};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testExitCommand() {
    String input = "exit\n";
    Readable readableInput = new StringReader(input);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput, new PrintStream(outputStream), new ImageOperationFactory());
    commandExecutor.run();
    String[] output = outputStream.toString().split("\n");
    assertEquals("> Goodbye - may the gradients be with you!\r", output[output.length - 1]);
  }

  @Test
  public void testLoadCommand() {
    String input = "load images/koala.ppm koala\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSaveCommand() {
    String input = "save images/koala-gs.ppm koala-greyscale\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBrightenCommand() {
    String input = "brighten 10 koala koala-brighter\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testVerticalFlipCommand() {
    String input = "vertical-flip koala koala-vertical\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testHorizontalFlipCommand() {
    String input = "horizontal-flip koala-vertical koala-vertical-horizontal\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testVerticalFlipCommandPreview() {
    String input = "vertical-flip koala koala-vertical 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testHorizontalFlipCommandPreview() {
    String input = "horizontal-flip koala-vertical koala-vertical-horizontal 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testValueComponentCommand() {
    String input = "value-component koala koala-greyscale\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRGBCombineCommand() {
    String input = "rgb-combine koala-red-tint koala-red koala-green koala-blue\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRGBSplitCommand() {
    String input = "rgb-split koala koala-red koala-green koala-blue\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testCompressCommand() {
    String input = "compress 50 koala koala-compress\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testLevelAdjustCommand() {
    String input = "levels-adjust 0 120 250 koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testLevelAdjustCommandPreview() {
    String input = "levels-adjust 0 120 250 koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testColorCorrectCommand() {
    String input = "color-correct koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testColorCorrectCommandPreview() {
    String input = "color-correct koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testHistogramCommand() {
    String input = "histogram koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testHistogramCommandPreview() {
    String input = "histogram koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRedComponentCommand() {
    String input = "red-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRedComponentCommandPreview() {
    String input = "red-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testGreenComponentCommand() {
    String input = "green-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testGreenComponentCommandPreview() {
    String input = "green-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBlueComponentCommand() {
    String input = "blue-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBlueComponentCommandPreview() {
    String input = "blue-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testLumaComponentCommand() {
    String input = "luma-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testLumaComponentCommandPreview() {
    String input = "luma-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testIntensityComponentCommand() {
    String input = "intensity-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testIntensityComponentCommandPreview() {
    String input = "intensity-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testValueComponentCommand2() {
    String input = "value-component koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testValueComponentCommandPreview() {
    String input = "value-component koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBlurCommand() {
    String input = "blur koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBlurCommandPreview() {
    String input = "blur koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSharpenCommand() {
    String input = "sharpen koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSharpenCommandPreview() {
    String input = "sharpen koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testCompressPreview() {
    String input = "compress koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSepia() {
    String input = "sepia koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSepiaPreview2() {
    String input = "sepia koala koala-processed 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBrightness() {
    String input = "brighten 10 koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testDarkness() {
    String input = "darken 10 koala koala-processed\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBrightnessPreview() {
    String input = "brighten 10 koala koala-brighter 50\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor =
        new ImageProcessorCLI(
            readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  static class MockOperation extends AbstractOperation {

    private final StringBuilder logger;

    /**
     * Constructs an AbstractOperation with the specified image library.
     *
     * @param library the ImageLibrary to be used for image operations
     */
    public MockOperation(ImageRepo library, StringBuilder logger) {
      super(library);
      this.logger = logger;
    }

    @Override
    public void execute(String... args) throws IllegalArgumentException {
      logger.append(String.join(" ", args));
      logger.append("\n").append("exit").append("\n");
    }
  }

  static class MockCommandFactory implements OperationCreator {

    private final ImageRepo library;
    private final StringBuilder logger;

    public MockCommandFactory(ImageRepo library, StringBuilder logger) {
      this.library = library;
      this.logger = logger;
    }

    @Override
    public CLIOperation createOperation(String commandName) {
      logger.append(commandName).append(" ");
      return new MockOperation(library, logger);
    }
  }
}
