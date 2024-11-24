import static org.junit.Assert.assertEquals;

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
 * Test class for exclusive testing on the colorCorrection method.
 */
public class ColorCorrectTest {

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

  // Test color correction on a 4x4 matrix
  @Test
  public void testColorCorrect1() {
    CustomImage imageRes = customImage1.colorCorrection(100);
    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255),
            Arrays.asList(125, 200, 45, 255)
        ),
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255)
        ),
        Arrays.asList(
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255),
            Arrays.asList(50, 50, 50, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Test color correction on a 4x5 matrix
  @Test
  public void testColorCorrect2() {
    CustomImage imageRes = customImage2.colorCorrection(100);
    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(110, 150, 190, 255),
            Arrays.asList(110, 150, 190, 255),
            Arrays.asList(110, 150, 190, 255),
            Arrays.asList(110, 150, 190, 255),
            Arrays.asList(110, 150, 190, 255)
        ),
        Arrays.asList(
            Arrays.asList(40, 60, 80, 255),
            Arrays.asList(40, 60, 80, 255),
            Arrays.asList(40, 60, 80, 255),
            Arrays.asList(40, 60, 80, 255), Arrays.asList(40, 60, 80, 255),
            Arrays.asList(40, 60, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(20, 20, 20, 255),
            Arrays.asList(20, 20, 20, 255),
            Arrays.asList(20, 20, 20, 255),
            Arrays.asList(20, 20, 20, 255),
            Arrays.asList(20, 20, 20, 255)
        ),
        Arrays.asList(
            Arrays.asList(210, 180, 150, 255),
            Arrays.asList(210, 180, 150, 255),
            Arrays.asList(210, 180, 150, 255),
            Arrays.asList(210, 180, 150, 255),
            Arrays.asList(210, 180, 150, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Test color correction on a 5x4 matrix
  @Test
  public void testColorCorrect3() {
    CustomImage imageRes = customImage3.colorCorrection(100);
    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(10, 10, 10, 255)
        ),
        Arrays.asList(
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(65, 70, 75, 255)
        ),
        Arrays.asList(
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(105, 120, 135, 255)),
        Arrays.asList(
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(205, 220, 235, 255)
        ),
        Arrays.asList(
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(20, 25, 30, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }
  }

  // Test color correction on a 5x4 matrix with a 40%
  @Test
  public void testColorCorrectPercentage1() {
    CustomImage imageRes = customImage3.colorCorrection(40);

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)),
        Arrays.asList(
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(15, 25, 35, 255),
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

  // Test color correction on a 5x4 matrix with a 50%
  @Test
  public void testColorCorrectPercentage2() {
    CustomImage imageRes = customImage3.colorCorrection(50);

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(10, 10, 10, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(65, 70, 75, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(105, 120, 135, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)),
        Arrays.asList(
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(205, 220, 235, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(20, 25, 30, 255),
            Arrays.asList(20, 25, 30, 255),
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
