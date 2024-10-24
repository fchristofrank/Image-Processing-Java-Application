import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import ime.imageIO.ImageFormat;
import ime.imageIO.ImageReader;
import ime.imageIO.ReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;

import static org.junit.Assert.assertEquals;

public class OperationsTest extends ImageOperationTest {

  @Test
  public void testSetup() {
    URL inputURL = getClass().getResource("TestScripts/visualize");
    if (inputURL != null) {
      try {
        Path filePath = Paths.get(inputURL.toURI());

        URL inputImageURL = getClass().getResource("manhattan-small.png");
        URL outputFolderURL = getClass().getClassLoader().getResource("VisualizeImages");

        if (inputImageURL != null && outputFolderURL != null) {
          String inputImagePath = Paths.get(inputImageURL.toURI()).toString();
          String outputImagePath = Paths.get(outputFolderURL.toURI()).toString();

          String commands = getCommandsFromFile(filePath.toString());

          commands = commands.replace("<inputFile>", inputImagePath).replace("<destinationPath>", outputImagePath);

          runTest(commands);
        } else {
          throw new RuntimeException("Failed to load input/output resources.");
        }
      } catch (URISyntaxException | IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new RuntimeException("Resource not found: TestScripts/visualize");
    }
  }

  @Test
  public void testVisualizeLuma() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/luma.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/luma.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeRed() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/red.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/red.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeBlue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/blue.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/blue.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeGreen() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/green.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/green.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeIntensity() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/intensity.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/intensity.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeValue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/value.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/value.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }
}