package ModelTest;

import org.junit.Test;

import ime.model.image.Image;
import ime.model.image.ImageType;
import ime.model.image.SimpleImage;
import ime.model.pixel.PixelFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This test class evaluates the functionalities of the Simple Image class.
 */
public class SimpleImageTest {
  @Test
  public void testImageEqualityEqualImages() {
    Image simpleImage1 = new SimpleImage(1, 1, ImageType.RGB);
    simpleImage1.setPixel(0, 0, PixelFactory.createPixel(simpleImage1.getType(),
            100, 100, 100));
    Image simpleImage2 = new SimpleImage(1, 1, ImageType.RGB);
    simpleImage2.setPixel(0, 0, PixelFactory.createPixel(simpleImage1.getType(),
            100, 100, 100));
    assertEquals(simpleImage1, simpleImage2);
  }

  @Test
  public void testImageEqualityUnEqualImages() {
    Image simpleImage1 = new SimpleImage(1, 1, ImageType.RGB);
    simpleImage1.setPixel(0, 0, PixelFactory.createPixel(simpleImage1.getType(),
            200, 100, 100));
    Image simpleImage2 = new SimpleImage(1, 1, ImageType.RGB);
    simpleImage2.setPixel(0, 0, PixelFactory.createPixel(simpleImage1.getType(),
            100, 100, 100));
    assertNotEquals(simpleImage1, simpleImage2);
  }

  @Test
  public void testHashCode() {
    int hashcode = 1688081780;
    Image simpleImage = new SimpleImage(1, 1, ImageType.RGB);
    simpleImage.setPixel(0, 0, PixelFactory.createPixel(simpleImage.getType(),
            200, 100, 100));
    assertEquals(hashcode, simpleImage.hashCode());
  }
}
