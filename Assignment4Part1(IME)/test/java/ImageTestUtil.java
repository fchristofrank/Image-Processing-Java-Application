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
import java.util.stream.Stream;

import ime.controller.cli.ImageProcessorCLI;
import ime.imageIO.ImageFormat;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.model.image.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImageTestUtil {

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

  protected void runImageTest(String commandScriptPath,String inputImageFileName, String outputActualFileName, String outputExpectedFileName, String folderName, String... replacements) {
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
      Reader reader = ReaderFactory.createrReader(ImageFormat.valueOf(format.toUpperCase()));
      Image actualImage = reader.read(outputActualImagePath.toString());
      Image expectedImage = reader.read(outputExpectedImagePath.toString());

      for (int i=0; i < actualImage.getHeight(); i++){
        for (int j=0; j< actualImage.getWidth(); j++){
          System.out.println(actualImage.getPixel(i,j).getValue() + " :: " + expectedImage.getPixel(i,j).getValue());
        }
      }
      assertEquals(actualImage, expectedImage);

    } catch (IOException | URISyntaxException e) {
      fail("Exception should not have been thrown");
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
