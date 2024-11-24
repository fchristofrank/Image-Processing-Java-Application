import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for exclusive testing on the vertical / horizontal flip methods.
 */
public class HorizontalVerticalTest {

  private CustomImage customImage1;
  private CustomImage customImage2;
  private CustomImage customImage3;


  // Helper method to create a row of pixels
  private List<CustomPixel> createPixelRow(int r, int g, int b, int a, int length) {
    return Collections.nCopies(length, new BasicPixel(r, g, b, a));
  }

  // Sets up the model and the matrices for testing
  @Before
  public void setUp() {

    // Set up a 4x4 matrix for testing
    List<List<CustomPixel>> pixelList1 = new ArrayList<>(List.of(
        createPixelRow(125, 200, 45, 255, 4),
        createPixelRow(0, 0, 0, 255, 4),
        createPixelRow(255, 255, 255, 255, 4),
        createPixelRow(50, 50, 50, 255, 4)
    ));
    customImage1 = new BasicImage(pixelList1);

    // Set up a 4x5 matrix for testing
    List<List<CustomPixel>> pixelList2 = new ArrayList<>(List.of(
        createPixelRow(100, 150, 200, 255, 5),
        createPixelRow(30, 60, 90, 255, 5),
        createPixelRow(10, 20, 30, 255, 5),
        createPixelRow(200, 180, 160, 255, 5)
    ));
    customImage2 = new BasicImage(pixelList2);

    // Set up a 5x4 matrix for testing
    List<List<CustomPixel>> pixelList3 = new ArrayList<>(List.of(
        createPixelRow(5, 10, 15, 255, 4),
        createPixelRow(60, 70, 80, 255, 4),
        createPixelRow(100, 120, 140, 255, 4),
        createPixelRow(200, 220, 240, 255, 4),
        createPixelRow(15, 25, 35, 255, 4)
    ));
    customImage3 = new BasicImage(pixelList3);

  }

  // Test horizontal flip on a 4x4 matrix
  @Test
  public void testHorizontalFlip1() {
    CustomImage imageRes = customImage1.horizontalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage1.get(i, j).getPixelRGBA(),
            imageRes.get(i, imageRes.getWidth() - j - 1).getPixelRGBA());
      }
    }
  }

  // Test horizontal flip on a 4x5 matrix
  @Test
  public void testHorizontalFlip2() {
    CustomImage imageRes = customImage2.horizontalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage2.get(i, j).getPixelRGBA(),
            imageRes.get(i, imageRes.getWidth() - j - 1).getPixelRGBA());
      }
    }
  }

  // Test horizontal flip on a 5x4 matrix
  @Test
  public void testHorizontalFlip3() {
    CustomImage imageRes = customImage3.horizontalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage3.get(i, j).getPixelRGBA(),
            imageRes.get(i, imageRes.getWidth() - j - 1).getPixelRGBA());
      }
    }
  }

  // Test vertical flip on a 4x4 matrix
  @Test
  public void testVerticalFlip1() {
    CustomImage imageRes = customImage1.verticalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage1.get(i, j).getPixelRGBA(),
            imageRes.get(imageRes.getHeight() - i - 1, j).getPixelRGBA());
      }
    }
  }

  // Test vertical flip on a 4x5 matrix
  @Test
  public void testVerticalFlip2() {
    CustomImage imageRes = customImage2.verticalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage2.get(i, j).getPixelRGBA(),
            imageRes.get(imageRes.getHeight() - i - 1, j).getPixelRGBA());
      }
    }
  }

  // Test vertical flip on a 5x4 matrix
  @Test
  public void testVerticalFlip3() {
    CustomImage imageRes = customImage3.verticalFlip();

    // Check that the image isn't null and dimensions are correct
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Validate each pixel by comparing horizontally flipped coordinates
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(customImage3.get(i, j).getPixelRGBA(),
            imageRes.get(imageRes.getHeight() - i - 1, j).getPixelRGBA());
      }
    }
  }
}
