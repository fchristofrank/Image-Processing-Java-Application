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
 * Test class for exclusive testing on the levelsAdjustImage method.
 */
public class LevelsAdjustTest {

  private CustomImage customImage1;


  // Helper method to create a row of pixels
  private List<CustomPixel> createPixelRow(int r, int g, int b, int a, int length) {
    return Collections.nCopies(length, new BasicPixel(r, g, b, a));
  }

  @Before
  public void setUp() {

    // Set up a 5x4 matrix for testing
    List<List<CustomPixel>> pixelList3 = new ArrayList<>(List.of(
        createPixelRow(5, 10, 15, 255, 4),
        createPixelRow(60, 70, 80, 255, 4),
        createPixelRow(100, 120, 140, 255, 4),
        createPixelRow(200, 220, 240, 255, 4),
        createPixelRow(15, 25, 35, 255, 4)
    ));
    customImage1 = new BasicImage(pixelList3);
  }


  @Test
  public void testLevelsAdjustPercentage1() {
    int black = 50;
    int mid = 128;
    int white = 255;
    int percentage = 20;
    CustomImage imageRes = customImage1.levelsAdjust(black, mid, white, percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)
        ),
        Arrays.asList(
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(15, 25, 35, 255),
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

  @Test
  public void testLevelsAdjustPercentage2() {
    int black = 50;
    int mid = 128;
    int white = 255;
    int percentage = 40;

    CustomImage imageRes = customImage1.levelsAdjust(black, mid, white, percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(139, 159, 180, 255),
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

  @Test
  public void testLevelsAdjustPercentage3() {
    int black = 50;
    int mid = 128;
    int white = 255;
    int percentage = 60;
    CustomImage imageRes = customImage1.levelsAdjust(black, mid, white, percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(5, 10, 15, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(60, 70, 80, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(100, 120, 140, 255),
            Arrays.asList(100, 120, 140, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(200, 220, 240, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(139, 159, 180, 255),
            Arrays.asList(139, 159, 180, 255),
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

  @Test
  public void testLevelsAdjustPercentage4() {
    int black = 50;
    int mid = 128;
    int white = 255;
    int percentage = 80;
    CustomImage imageRes = customImage1.levelsAdjust(black, mid, white, percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(117, 128, 139, 255),
            Arrays.asList(5, 10, 15, 255)
        ),
        Arrays.asList(
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(227, 245, 255, 255),
            Arrays.asList(60, 70, 80, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(100, 120, 140, 255)
        ),
        Arrays.asList(
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(255, 255, 255, 255),
            Arrays.asList(200, 220, 240, 255)
        ),
        Arrays.asList(
            Arrays.asList(139, 159, 180, 255),
            Arrays.asList(139, 159, 180, 255),
            Arrays.asList(139, 159, 180, 255),
            Arrays.asList(15, 25, 35, 255)
        )
    );

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }

  }

  @Test
  public void testLevelsAdjustPercentage5() {
    int black = 0;
    int mid = 128;
    int white = 255;
    int percentage = 100;
    CustomImage imageRes = customImage1.levelsAdjust(black, mid, white, percentage);

    // Ensure the result image is not null and has the correct dimensions
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(7, 12, 17, 255),
            Arrays.asList(7, 12, 17, 255),
            Arrays.asList(7, 12, 17, 255),
            Arrays.asList(7, 12, 17, 255)
        ),
        Arrays.asList(
            Arrays.asList(62, 72, 82, 255),
            Arrays.asList(62, 72, 82, 255),
            Arrays.asList(62, 72, 82, 255),
            Arrays.asList(62, 72, 82, 255)
        ),
        Arrays.asList(
            Arrays.asList(102, 122, 142, 255),
            Arrays.asList(102, 122, 142, 255),
            Arrays.asList(102, 122, 142, 255),
            Arrays.asList(102, 122, 142, 255)
        ),
        Arrays.asList(
            Arrays.asList(202, 222, 242, 255),
            Arrays.asList(202, 222, 242, 255),
            Arrays.asList(202, 222, 242, 255),
            Arrays.asList(202, 222, 242, 255)
        ),
        Arrays.asList(
            Arrays.asList(17, 27, 37, 255),
            Arrays.asList(17, 27, 37, 255),
            Arrays.asList(17, 27, 37, 255),
            Arrays.asList(17, 27, 37, 255)
        )
    );
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imageRes.get(i, j).getPixelRGBA());
      }
    }


  }


}
