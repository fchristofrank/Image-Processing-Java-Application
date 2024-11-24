import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for exclusive testing on the lumaConversion method.
 */
public class LumaTest {

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

  // Testing Luma Component on 4x4 matrices
  @Test
  public void testLuma1() {
    CustomImage imageRes = customImage1.lumaConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage1.get(i, j).getPixelRGBA();

        // Calculate the luma value using the appropriate weights
        int lumaValue = (int) Math.round(
            originalPixelRGBA.get(0) * 0.2126 +
                originalPixelRGBA.get(1) * 0.7152 +
                originalPixelRGBA.get(2) * 0.0722
        );

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();
        // Check that each RGB channel is set to the luma value
        assertEquals(lumaValue, (int) imagesResRGBA.get(0)); // Red
        assertEquals(lumaValue, (int) imagesResRGBA.get(1)); // Green
        assertEquals(lumaValue, (int) imagesResRGBA.get(2)); // Blue

        // Check if the alpha value remains the same
        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  // Testing Luma Component on 4x5 matrices
  @Test
  public void testLuma2() {
    CustomImage imageRes = customImage2.lumaConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage2.get(i, j).getPixelRGBA();

        // Calculate the luma value using the appropriate weights
        int lumaValue = (int) Math.round(
            originalPixelRGBA.get(0) * 0.2126 +
                originalPixelRGBA.get(1) * 0.7152 +
                originalPixelRGBA.get(2) * 0.0722
        );

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();
        // Check that each RGB channel is set to the luma value
        assertEquals(lumaValue, (int) imagesResRGBA.get(0)); // Red
        assertEquals(lumaValue, (int) imagesResRGBA.get(1)); // Green
        assertEquals(lumaValue, (int) imagesResRGBA.get(2)); // Blue

        // Check if the alpha value remains the same
        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  // Testing Luma Component on 5x4 matrices
  @Test
  public void testLuma3() {

    CustomImage imageRes = customImage3.lumaConversion(100);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    // Iterate through each pixel to check value conversion
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage3.get(i, j).getPixelRGBA();

        // Calculate the luma value using the appropriate weights
        int lumaValue = (int) Math.round(
            originalPixelRGBA.get(0) * 0.2126 +
                originalPixelRGBA.get(1) * 0.7152 +
                originalPixelRGBA.get(2) * 0.0722
        );

        // Get the processed pixel's RGBA values
        List<Integer> imagesResRGBA = imageRes.get(i, j).getPixelRGBA();
        // Check that each RGB channel is set to the luma value
        assertEquals(lumaValue, (int) imagesResRGBA.get(0)); // Red
        assertEquals(lumaValue, (int) imagesResRGBA.get(1)); // Green
        assertEquals(lumaValue, (int) imagesResRGBA.get(2)); // Blue

        // Check if the alpha value remains the same
        assertEquals(originalPixelRGBA.get(3), imagesResRGBA.get(3));
      }
    }
  }

  // Testing Luma Component on 5x4 matrices
  @Test
  public void testLumaPercentage() {

    CustomImage imageRes = customImage3.lumaConversion(50);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(9, 9, 9, 255),
            Arrays.asList(9, 9, 9, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(69, 69, 69, 255),
            Arrays.asList(69, 69, 69, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(117, 117, 117, 255),
            Arrays.asList(117, 117, 117, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)
        ),
        Arrays.asList(
            Arrays.asList(217, 217, 217, 255),
            Arrays.asList(217, 217, 217, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(24, 24, 24, 255),
            Arrays.asList(24, 24, 24, 255),
            Arrays.asList(15, 25, 35, 255),
            Arrays.asList(15, 25, 35, 255)
        )
    );
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }
}
