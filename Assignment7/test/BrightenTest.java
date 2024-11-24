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
 * Test class for exclusive testing on the increaseBrightness method.
 */
public class BrightenTest {

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

  // Testing Brighten on 4x4 matrices
  @Test
  public void testBrighten1() {
    // Increase brightness by 10
    CustomImage imageRes = customImage1.increaseBrightness(10);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage1.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) + 10, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) + 10, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) + 10, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten on 4x5 matrices
  @Test
  public void testBrighten2() {
    // Increase brightness by 10
    CustomImage imageRes = customImage2.increaseBrightness(10);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage2.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) + 10, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) + 10, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) + 10, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten on 5x4 matrices
  @Test
  public void testBrighten3() {
    // Increase brightness by 10
    CustomImage imageRes = customImage3.increaseBrightness(10);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) + 10, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) + 10, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) + 10, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Negative Value) on 4x4 matrices
  @Test
  public void testBrighten4() {
    // Increase brightness by 10
    CustomImage imageRes = customImage1.increaseBrightness(-20);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage1.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) - 20, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) - 20, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) - 20, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Negative Value) on 4x5 matrices
  @Test
  public void testBrighten5() {
    // Increase brightness by 10
    CustomImage imageRes = customImage2.increaseBrightness(-20);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage2.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) - 20, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) - 20, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) - 20, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Negative Value) on 5x4 matrices
  @Test
  public void testBrighten6() {
    // Increase brightness by 10
    CustomImage imageRes = customImage3.increaseBrightness(-20);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) - 20, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) - 20, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) - 20, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Negative Value) on 5x4 matrices
  @Test
  public void testBrighten7() {
    // Increase brightness by 10
    CustomImage imageRes = customImage3.increaseBrightness(-1000);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) - 1000, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) - 1000, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) - 1000, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Negative Value) on 5x4 matrices
  @Test
  public void testBrighten8() {
    // Increase brightness by 10
    CustomImage imageRes = customImage3.increaseBrightness(1000);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) + 1000, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) + 1000, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) + 1000, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }

  // Testing Brighten(Zero Value) on 5x4 matrices
  @Test
  public void testBrighten9() {
    // Increase brightness by 10
    CustomImage imageRes = customImage3.increaseBrightness(-20);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Check that each pixel's brightness is increased by 10
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        List<Integer> brightenedPixelRGBA = imageRes.get(i, j).getPixelRGBA();
        // Verify that the red, green, and blue components are increased by 10
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(0) - 20, 255)),
            (int) brightenedPixelRGBA.get(0));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(1) - 20, 255)),
            (int) brightenedPixelRGBA.get(1));
        assertEquals(Math.max(0, Math.min(originalPixelRGBA.get(2) - 20, 255)),
            (int) brightenedPixelRGBA.get(2));

        // Alpha value should remain the same
        assertEquals(originalPixelRGBA.get(3), brightenedPixelRGBA.get(3));
      }
    }
  }
}
