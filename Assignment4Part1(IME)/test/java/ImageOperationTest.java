import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ime.controller.cli.ImageProcessorCLI;
import ime.imageIO.ImageFormat;
import ime.imageIO.ImageReader;
import ime.imageIO.ReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class provides a framework for testing image operations in a CLI-based image processor.
 * It contains utility methods for loading test scripts, running commands, and comparing images
 * to verify correct processing results.
 */
public class ImageOperationTest {

  /**
   * Interface used to define custom assertions for comparing expected and actual images.
   */
  public interface ImageAssertion {
    /**
     * Performs an assertion to compare two images.
     *
     * @param expected the expected image result.
     * @param actual   the actual image result after processing.
     */
    void assertImages(Image expected, Image actual);
  }

  /**
   * Runs a CLI command by simulating input. The command string is provided to the
   * CLI application via standard input.
   *
   * @param command the command to be executed by the image processor CLI.
   */
  protected void runTest(String command) {
    ByteArrayInputStream inputCommands = new ByteArrayInputStream(command.getBytes());
    System.setIn(inputCommands);
    new ImageProcessorCLI(inputCommands).run();
  }

  /**
   * Reads and retrieves all non-comment, non-empty lines from a command script file.
   * The lines are concatenated into a single string with new lines and returned as the command.
   *
   * @param path the file path to the command script.
   * @return a string containing all valid commands from the file.
   * @throws IOException if there is an issue reading the file.
   */
  protected String getCommandsFromFile(String path) throws IOException {
    List<String> commands = new ArrayList<>();
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      lines.map(String::trim)
              .filter(line -> !line.startsWith("#") && !line.isEmpty())
              .map(line -> line + "\n")
              .forEach(commands::add);
    }
    return String.join("", commands);
  }

  /**
   * Runs a test using a command script and compares output images with expected results.
   * The method executes the command, processes the images, and performs assertions.
   *
   * @param commandScriptPath  the path to the test command script.
   * @param inputImageFileName the name of the input image file to process.
   * @param outputFileMap      a map of actual output image filenames to expected output image
   *                           filenames.
   * @param folderName         the folder where the input and output images are stored.
   * @param replacements       a map of placeholder keys and their corresponding replacement values
   *                           in the command.
   * @param assertion          an assertion function to compare the expected and actual images.
   * @throws IllegalArgumentException if any arguments are invalid.
   */
  protected void runImageTest(String commandScriptPath, String inputImageFileName,
                              Map<String, String> outputFileMap, String folderName,
                              Map<String, String> replacements, ImageAssertion assertion)
          throws IllegalArgumentException {
    URL inputURL = getClass().getClassLoader().getResource(commandScriptPath);
    Assert.assertNotNull("Test script not found: " + commandScriptPath, inputURL);

    Path inputScriptPath = convertToPath(inputURL, "Test script");

    String command;
    try {
      command = getCommandsFromFile(inputScriptPath.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read commands from script: " +
              commandScriptPath, e);
    }

    URL inputImageURL = getClass().getClassLoader().getResource(inputImageFileName);
    URL outputFolderURL = getClass().getClassLoader().getResource(folderName);

    Assert.assertNotNull("Input resource not found: " + inputImageFileName, inputImageURL);
    Assert.assertNotNull("Output directory not found: " + folderName, outputFolderURL);

    Path outputFolderPath = convertToPath(outputFolderURL, "Output folder");

    command = command.replace("<input_image_path>", inputImageURL.getPath());

    if (replacements != null && !replacements.isEmpty()) {
      for (Map.Entry<String, String> replacement : replacements.entrySet()) {
        command = command.replace(replacement.getKey(), replacement.getValue());
      }
    }

    for (Map.Entry<String, String> entry : outputFileMap.entrySet()) {
      String actualFileName = entry.getKey();
      Path actualImagePath = outputFolderPath.resolve(actualFileName);
      command = command.replaceFirst("<output_image_path>", actualImagePath.toString()
              .replace("\\", "\\\\"));
    }

    runTest(command);

    for (Map.Entry<String, String> entry : outputFileMap.entrySet()) {
      String actualFileName = entry.getKey();
      String expectedFileName = entry.getValue();

      String format = actualFileName.split("\\.")[1].toUpperCase();
      ImageReader imageReader = ReaderFactory.createReader(ImageFormat.valueOf(format));

      Path actualImagePath = outputFolderPath.resolve(actualFileName);
      Path expectedImagePath = outputFolderPath.resolve(expectedFileName);

      Image actualImage;
      try {
        actualImage = imageReader.read(actualImagePath.toString(), ImageType.RGB);
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to read actual image file: " +
                actualImagePath, e);
      }

      Image expectedImage;
      try {
        expectedImage = imageReader.read(expectedImagePath.toString(), ImageType.RGB);
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to read expected image file: " +
                expectedImagePath, e);
      }

      assertion.assertImages(expectedImage, actualImage);
    }
  }

  /**
   * Converts a URL to a Path, throwing a clear IllegalArgumentException if the URI is invalid.
   *
   * @param url  the URL to convert.
   * @param name a descriptive name to use in exception messages.
   * @return the Path corresponding to the URL.
   * @throws IllegalArgumentException if the URL cannot be converted to a URI.
   */
  private Path convertToPath(URL url, String name) throws IllegalArgumentException {
    try {
      return Paths.get(url.toURI());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(name + " URL is invalid: " + url, e);
    }
  }


  protected void runImageTest(String commandScriptPath, String inputImageFileName,
                              String outputActualFileName, String outputExpectedFileName,
                              String folderName, String... replacements) {
    try {
      URL inputURL = getClass().getClassLoader().getResource(commandScriptPath);
      Assert.assertNotNull("Test script not found", inputURL);
      Path inputScriptPath = Paths.get(inputURL.toURI());
      String command = getCommandsFromFile(inputScriptPath.toString());
      URL inputImageURL = getClass().getClassLoader().getResource(inputImageFileName);
      URL outputFolderURL = getClass().getClassLoader().getResource(folderName);
      Assert.assertNotNull("Input resource not found", inputImageURL);
      Assert.assertNotNull("Output directory not found", outputFolderURL);
      Path outputFolderPath = Paths.get(outputFolderURL.toURI());
      Path outputActualImagePath = outputFolderPath.resolve(outputActualFileName);
      Path outputExpectedImagePath = outputFolderPath.resolve(outputExpectedFileName);
      command = command.replace("<input_image_path>", inputImageURL.getPath())
              .replace("<output_image_path>", outputActualImagePath.toString());

      if (replacements != null && replacements.length > 0) {
        for (int i = 0; i < replacements.length; i += 2) {
          command = command.replace(replacements[i], replacements[i + 1]);
        }
      }

      runTest(command);
      String format = outputActualFileName.split("\\.")[1];
      ImageReader imageReader = ReaderFactory.createReader(ImageFormat.valueOf(format.toUpperCase()));
      Image actualImage = imageReader.read(outputActualImagePath.toString(), ImageType.RGB);
      Image expectedImage = imageReader.read(outputExpectedImagePath.toString(), ImageType.RGB);
      assertEquals(actualImage, expectedImage);

    } catch (IOException | URISyntaxException e) {
      fail("Exception should not have been thrown");
    }
  }

  /**
   * Loads an image from the resources folder and returns the Image object.
   * The method reads the image file based on its extension using the appropriate reader.
   *
   * @param imageFileName the filename of the image to load from resources.
   * @return the Image object read from the file.
   * @throws RuntimeException if the resource cannot be found or the image cannot be read.
   */
  protected Image loadImageFromResources(String imageFileName) {
    try {
      URL imageUrl = getClass().getClassLoader().getResource(imageFileName);

      String fileExtension = imageFileName.substring(imageFileName.
              lastIndexOf(".") + 1);
      ImageReader imageReader = ReaderFactory.createReader(ImageFormat.valueOf(fileExtension.
              toUpperCase()));

      assert imageUrl != null;
      Path imagePath = Paths.get(imageUrl.toURI());
      return imageReader.read(imagePath.toString(), ImageType.RGB);

    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URI for resource: " + imageFileName, e);

    } catch (IOException e) {
      throw new RuntimeException("Error reading image file: " + imageFileName, e);
    }
  }
}
