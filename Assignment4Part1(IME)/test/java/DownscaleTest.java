import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.operation.Downscale;
import ime.model.operation.ImageOperation;

public class DownscaleTest {


  @Test
  public void testDownscale(){

    String resDirPath =
            Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.JPG);
    Image image;
    try {
      image = imageReader.read(resDirPath + "boston-sharpen-wp-actual.jpg", ImageType.RGB);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
    ImageOperation operation = new Downscale();
    operation.apply(image,"test","test","100","100");
  }
}
