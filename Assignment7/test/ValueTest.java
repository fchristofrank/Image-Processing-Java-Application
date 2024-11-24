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
 * Test class for exclusive testing on the valueConversion method.
 */
public class ValueTest {

  private CustomImage customImage1;
  private CustomImage customImage2;
  private CustomImage customImage3;
  private CustomImage customImage4;


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

    // Set up a 4x4 matrix for testing
    List<List<CustomPixel>> pixelList4 = new ArrayList<>(List.of(
        createPixelRow(10, 20, 30, 255, 4),
        createPixelRow(50, 75, 100, 255, 4),
        createPixelRow(100, 150, 200, 255, 4),
        createPixelRow(200, 220, 240, 255, 4)
    ));
    customImage4 = new BasicImage(pixelList4);
  }

  // Tests that check if pixelList is valid

  @Test(expected = IllegalArgumentException.class)
  public void initializationInvalid() {

    List<List<CustomPixel>> pixelList = new ArrayList<>(List.of(
        createPixelRow(5, 10, 15, 255, 5),
        createPixelRow(60, 70, 80, 255, 4),
        createPixelRow(100, 120, 140, 255, 4),
        createPixelRow(200, 220, 240, 255, 4),
        createPixelRow(15, 25, 35, 255, 4)
    ));
    CustomImage image = new BasicImage(pixelList);
  }

  // Testing Value Component on 4x4 matrices
  @Test
  public void testValue1() {
    CustomImage imageRes = customImage1.valueConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage1.get(i, j).getPixelRGBA();
        // Get the maximum value from the original pixel's RGB components
        Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();

        // Check that each RGB channel is set to the maximum value
        assertEquals(maxNumber, imagesResRGBA.get(0));
        assertEquals(maxNumber, imagesResRGBA.get(1));
        assertEquals(maxNumber, imagesResRGBA.get(2));

        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  // Testing Value Component on 4x5 matrices
  @Test
  public void testValue2() {
    CustomImage imageRes = customImage2.valueConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage2.get(i, j).getPixelRGBA();
        // Get the maximum value from the original pixel's RGB components
        Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();

        // Check that each RGB channel is set to the maximum value
        assertEquals(maxNumber, imagesResRGBA.get(0));
        assertEquals(maxNumber, imagesResRGBA.get(1));
        assertEquals(maxNumber, imagesResRGBA.get(2));

        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  // Testing Value Component on 5x4 matrices
  @Test
  public void testValue3() {
    CustomImage imageRes = customImage3.valueConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();
        // Get the maximum value from the original pixel's RGB components
        Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();

        // Check that each RGB channel is set to the maximum value
        assertEquals(maxNumber, imagesResRGBA.get(0));
        assertEquals(maxNumber, imagesResRGBA.get(1));
        assertEquals(maxNumber, imagesResRGBA.get(2));

        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  @Test
  public void testValuePercentage1() {
    int percentage = 50;
    CustomImage imageRes = customImage4.valueConversion(percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {
          Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

          assertEquals(maxNumber, newPixelRGBA.get(0));
          assertEquals(maxNumber, newPixelRGBA.get(1));
          assertEquals(maxNumber, newPixelRGBA.get(2));

          assertEquals(originalPixelRGBA.get(3), newPixelRGBA.get(3));
        }

      }
    }

  }

  @Test
  public void testValuePercentage2() {
    int percentage = 75;
    CustomImage imageRes = customImage4.valueConversion(percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {
          Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

          assertEquals(maxNumber, newPixelRGBA.get(0));
          assertEquals(maxNumber, newPixelRGBA.get(1));
          assertEquals(maxNumber, newPixelRGBA.get(2));

          assertEquals(originalPixelRGBA.get(3), newPixelRGBA.get(3));
        }

      }
    }

  }

  @Test
  public void testValuePercentage3() {
    int percentage = 25;
    CustomImage imageRes = customImage4.valueConversion(percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {
          Integer maxNumber = Collections.max(originalPixelRGBA.subList(0, 3));

          assertEquals(maxNumber, newPixelRGBA.get(0));
          assertEquals(maxNumber, newPixelRGBA.get(1));
          assertEquals(maxNumber, newPixelRGBA.get(2));

          assertEquals(originalPixelRGBA.get(3), newPixelRGBA.get(3));
        }

      }
    }

  }

}
