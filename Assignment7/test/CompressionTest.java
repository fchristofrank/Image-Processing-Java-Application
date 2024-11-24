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
 * Test class for exclusive testing on the compressConversion method.
 */
public class CompressionTest {

  private CustomImage customImage1;

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
  }

  // Test for 50% Compression
  @Test
  public void CompressionTest1() {
    CustomImage imageRes = customImage1.compressConversion(50);
    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255)
        ),
        Arrays.asList(
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255),
            Arrays.asList(107, 126, 22, 255)
        ),
        Arrays.asList(
            Arrays.asList(209, 228, 152, 255),
            Arrays.asList(209, 228, 152, 255),
            Arrays.asList(209, 228, 254, 255),
            Arrays.asList(209, 228, 254, 255)
        ),
        Arrays.asList(
            Arrays.asList(4, 23, 152, 255),
            Arrays.asList(4, 23, 152, 255),
            Arrays.asList(4, 23, 49, 255),
            Arrays.asList(4, 23, 49, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Test for 100% Compression
  @Test
  public void CompressionTest2() {
    CustomImage imageRes = customImage1.compressConversion(100);
    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }
}
