import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import ime.imageIO.ImageFormat;
import ime.imageIO.Reader;
import ime.imageIO.ReaderFactory;
import ime.models.Image;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

  @Test
  public void testSetup() {
    URL inputURL = getClass().getResource("TestScripts/VisualizationTestScript");
    if (inputURL != null) {
      try {
        Path filePath = Paths.get(inputURL.toURI());

        URL inputImageURL = getClass().getResource("manhattan-small.png");
        URL outputFolderURL = getClass().getClassLoader().getResource("");

        if (inputImageURL != null && outputFolderURL != null) {
          String inputImagePath = Paths.get(inputImageURL.toURI()).toString();
          String outputImagePath = Paths.get(outputFolderURL.toURI()).toString();

          String commands = TestUtil.getCommandsFromFile(filePath.toString());

          commands = commands.replace("<inputFile>", inputImagePath).replace("<destinationPath>", outputImagePath);

          TestUtil.runTest(commands);
        } else {
          throw new RuntimeException("Failed to load input/output resources.");
        }
      } catch (URISyntaxException | IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new RuntimeException("Resource not found: TestScripts/VisualizationTestScript");
    }
  }

  @Test
  public void testVisualizeLuma() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-luma-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "luma.png");

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeRed() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-red-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "red.png");

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeBlue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-blue-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "blue.png");

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeGreen() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-green-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "green.png");

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeIntensity() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-intensity-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "intensity.png");

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeValue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeTestImages/manhattan-value-expected.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    Reader reader = ReaderFactory.createrReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = reader.read(expectedImageURL.getPath());
    Image actualImage = reader.read(actualImageURL.getPath() + "value.png");

    assertEquals(expectedImage, actualImage);
  }
}