import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
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
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.model.image.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImageTestUtil {
  @FunctionalInterface
  interface ImageAssertion {
    void assertImages(Image expected, Image actual);
  }

  protected void runTest(String command) {
    ByteArrayInputStream inputCommands = new ByteArrayInputStream(command.getBytes());
    System.setIn(inputCommands);
    new ImageProcessorCLI(inputCommands).run();
  }

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

  protected void runImageTest(String commandScriptPath, String inputImageFileName, Map<String, String> outputFileMap, String folderName, Map<String, String> replacements, ImageAssertion assertion) {
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

      command = command.replace("<input_image_path>", inputImageURL.getPath());

      if (replacements != null && !replacements.isEmpty()) {
        for (Map.Entry<String, String> replacement : replacements.entrySet()) {
          command = command.replace(replacement.getKey(), replacement.getValue());
        }
      }

      for (Map.Entry<String, String> entry : outputFileMap.entrySet()) {
        String actualFileName = entry.getKey();
        Path actualImagePath = outputFolderPath.resolve(actualFileName);
        command = command.replaceFirst("<output_image_path>", actualImagePath.toString().replace("\\", "\\\\"));
      }
      runTest(command);

      for (Map.Entry<String, String> entry : outputFileMap.entrySet()) {
        String actualFileName = entry.getKey();
        String expectedFileName = entry.getValue();

        String format = actualFileName.split("\\.")[1].toUpperCase();
        Reader reader = ReaderFactory.createrReader(ImageFormat.valueOf(format));

        Path actualImagePath = outputFolderPath.resolve(actualFileName);
        Path expectedImagePath = outputFolderPath.resolve(expectedFileName);

        Image actualImage = reader.read(actualImagePath.toString());
        Image expectedImage = reader.read(expectedImagePath.toString());
        assertion.assertImages(expectedImage, actualImage);
      }
    } catch (IOException | URISyntaxException e) {
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  protected Image loadImageFromResources(String imageFileName) {
    try {
      URL imageUrl = getClass().getClassLoader().getResource(imageFileName);

      String fileExtension = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
      Reader imageReader = ReaderFactory.createrReader(ImageFormat.valueOf(fileExtension.toUpperCase()));

      assert imageUrl != null;
      Path imagePath = Paths.get(imageUrl.toURI());
      return imageReader.read(imagePath.toString());

    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URI for resource: " + imageFileName, e);

    } catch (IOException e) {
      throw new RuntimeException("Error reading image file: " + imageFileName, e);
    }
  }
}
