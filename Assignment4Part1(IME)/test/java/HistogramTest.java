import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.operation.Histogram;
import ime.model.operation.ImageOperation;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;

public class HistogramTest {

  @Test
  public void histogramTest() {
    ImageOperation operation = new Histogram();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    String resDirPath =
        Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    Image actualImage;

    try {
      actualImage = imageReader.read(resDirPath + "boston.png", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    actualImage.applyOperation(operation);
  }
}
