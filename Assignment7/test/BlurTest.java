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
 * Test class for exclusive testing on the blurConversion method.
 */
public class BlurTest {

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

  // Testing Blur Component (should be exact images) on 4x4 matrices
  @Test
  public void testBlur1() {
    CustomImage imagesRes = customImage1.blurConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage1.getHeight(), imagesRes.getHeight());
    assertEquals(customImage1.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(46, 75, 16, 255),
            Arrays.asList(61, 100, 21, 255),
            Arrays.asList(61, 100, 21, 255),
            Arrays.asList(46, 75, 16, 255)
        ),
        Arrays.asList(
            Arrays.asList(68, 83, 53, 255),
            Arrays.asList(90, 110, 70, 255),
            Arrays.asList(90, 110, 70, 255),
            Arrays.asList(68, 83, 53, 255)
        ),
        Arrays.asList(
            Arrays.asList(103, 103, 103, 255),
            Arrays.asList(137, 137, 137, 255),
            Arrays.asList(137, 137, 137, 255),
            Arrays.asList(103, 103, 103, 255)
        ),
        Arrays.asList(
            Arrays.asList(64, 64, 64, 255),
            Arrays.asList(85, 85, 85, 255),
            Arrays.asList(85, 85, 85, 255),
            Arrays.asList(64, 64, 64, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing Blur Component (should be exact images) on 4x5 matrices
  @Test
  public void testBlur2() {
    CustomImage imagesRes = customImage2.blurConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage2.getHeight(), imagesRes.getHeight());
    assertEquals(customImage2.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(41, 65, 91, 255),
            Arrays.asList(54, 86, 121, 255),
            Arrays.asList(54, 86, 121, 255),
            Arrays.asList(54, 86, 121, 255),
            Arrays.asList(41, 65, 91, 255)
        ),
        Arrays.asList(
            Arrays.asList(29, 52, 74, 255),
            Arrays.asList(38, 69, 98, 255),
            Arrays.asList(38, 69, 98, 255),
            Arrays.asList(38, 69, 98, 255),
            Arrays.asList(29, 52, 74, 255)
        ),
        Arrays.asList(
            Arrays.asList(44, 50, 56, 255),
            Arrays.asList(58, 66, 74, 255),
            Arrays.asList(58, 66, 74, 255),
            Arrays.asList(58, 66, 74, 255),
            Arrays.asList(44, 50, 56, 255)
        ),
        Arrays.asList(
            Arrays.asList(76, 70, 64, 255),
            Arrays.asList(101, 93, 85, 255),
            Arrays.asList(101, 93, 85, 255),
            Arrays.asList(101, 93, 85, 255),
            Arrays.asList(76, 70, 64, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());

      }
    }
  }

  // Testing Blur Component (should be exact images) on 5x4 matrices
  @Test
  public void testBlur3() {
    CustomImage imagesRes = customImage3.blurConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage3.getHeight(), imagesRes.getHeight());
    assertEquals(customImage3.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(11, 15, 19, 255),
            Arrays.asList(14, 20, 25, 255),
            Arrays.asList(14, 20, 25, 255),
            Arrays.asList(11, 15, 19, 255)
        ),
        Arrays.asList(
            Arrays.asList(40, 48, 56, 255),
            Arrays.asList(53, 63, 74, 255),
            Arrays.asList(53, 63, 74, 255),
            Arrays.asList(40, 48, 56, 255)
        ),
        Arrays.asList(
            Arrays.asList(84, 97, 112, 255),
            Arrays.asList(111, 129, 149, 255),
            Arrays.asList(111, 129, 149, 255),
            Arrays.asList(84, 97, 112, 255)
        ),
        Arrays.asList(
            Arrays.asList(94, 108, 121, 255),
            Arrays.asList(125, 143, 161, 255),
            Arrays.asList(125, 143, 161, 255),
            Arrays.asList(94, 108, 121, 255)
        ),
        Arrays.asList(
            Arrays.asList(41, 49, 57, 255),
            Arrays.asList(54, 65, 76, 255),
            Arrays.asList(54, 65, 76, 255),
            Arrays.asList(41, 49, 57, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing Blur Component (should be exact images) on matrix with 25% blur
  @Test
  public void testBlurPercentage1() {
    CustomImage imagesRes = customImage1.blurConversion(25);
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(46, 75, 16, 255),
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255)
        ),
        Arrays.asList(
            Arrays.asList(68, 83, 53, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(103, 103, 103, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255)
        ),
        Arrays.asList(
            Arrays.asList(64, 64, 64, 255),
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing Blur Component (should be exact images) on 4x4 matrix with 50% blur
  @Test
  public void testBlurPercentage2() {
    CustomImage imagesRes = customImage1.blurConversion(50);
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(46, 75, 16, 255),
            Arrays.asList(61, 100, 21, 255),
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255)
        ),
        Arrays.asList(
            Arrays.asList(68, 83, 53, 255),
            Arrays.asList(90, 110, 70, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(103, 103, 103, 255),
            Arrays.asList(137, 137, 137, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255)
        ),
        Arrays.asList(
            Arrays.asList(64, 64, 64, 255),
            Arrays.asList(85, 85, 85, 255),
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing Blur Component (should be exact images) on 4x4 matrix with 75% blur
  @Test
  public void testBlurPercentage3() {
    CustomImage imagesRes = customImage1.blurConversion(75);
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(46, 75, 16, 255),
            Arrays.asList(61, 100, 21, 255),
            Arrays.asList(61, 100, 21, 255),
            Arrays.asList(125, 200, 45, 255)
        ),
        Arrays.asList(
            Arrays.asList(68, 83, 53, 255),
            Arrays.asList(90, 110, 70, 255),
            Arrays.asList(90, 110, 70, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(103, 103, 103, 255),
            Arrays.asList(137, 137, 137, 255),
            Arrays.asList(137, 137, 137, 255),
            Arrays.asList(255, 255, 255, 255)
        ),
        Arrays.asList(
            Arrays.asList(64, 64, 64, 255),
            Arrays.asList(85, 85, 85, 255),
            Arrays.asList(85, 85, 85, 255),
            Arrays.asList(50, 50, 50, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }
}


