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
import util.Constants;

/**
 * Test class for exclusive testing on the sepiaConversion method.
 */
public class SepiaTest {

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

  // Testing Sepia Component (should be exact images) on 4x4 matrices
  @Test
  public void testSepia1() {
    CustomImage imagesRes = customImage1.sepiaConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage1.getHeight(), imagesRes.getHeight());
    assertEquals(customImage1.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(211, 188, 146, 255),
            Arrays.asList(211, 188, 146, 255),
            Arrays.asList(211, 188, 146, 255),
            Arrays.asList(211, 188, 146, 255)
        ),
        Arrays.asList(
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255),
            Arrays.asList(0, 0, 0, 255)
        ),
        Arrays.asList(
            Arrays.asList(344, 306, 238, 255),
            Arrays.asList(344, 306, 238, 255),
            Arrays.asList(344, 306, 238, 255),
            Arrays.asList(344, 306, 238, 255)
        ),
        Arrays.asList(
            Arrays.asList(67, 60, 46, 255),
            Arrays.asList(67, 60, 46, 255),
            Arrays.asList(67, 60, 46, 255),
            Arrays.asList(67, 60, 46, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());

      }
    }
  }

  // Testing Sepia Component (should be exact images) on 4x5 matrices
  @Test
  public void testSepia2() {
    CustomImage imagesRes = customImage2.sepiaConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage2.getHeight(), imagesRes.getHeight());
    assertEquals(customImage2.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(192, 171, 133, 255),
            Arrays.asList(192, 171, 133, 255),
            Arrays.asList(192, 171, 133, 255),
            Arrays.asList(192, 171, 133, 255),
            Arrays.asList(192, 171, 133, 255)
        ),
        Arrays.asList(
            Arrays.asList(74, 66, 51, 255),
            Arrays.asList(74, 66, 51, 255),
            Arrays.asList(74, 66, 51, 255),
            Arrays.asList(74, 66, 51, 255),
            Arrays.asList(74, 66, 51, 255)
        ),
        Arrays.asList(
            Arrays.asList(24, 22, 17, 255),
            Arrays.asList(24, 22, 17, 255),
            Arrays.asList(24, 22, 17, 255),
            Arrays.asList(24, 22, 17, 255),
            Arrays.asList(24, 22, 17, 255)
        ),
        Arrays.asList(
            Arrays.asList(247, 220, 171, 255),
            Arrays.asList(247, 220, 171, 255),
            Arrays.asList(247, 220, 171, 255),
            Arrays.asList(247, 220, 171, 255),
            Arrays.asList(247, 220, 171, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());

      }
    }
  }

  // Testing Sepia Component (should be exact images) on 5x4 matrices
  @Test
  public void testSepia3() {
    CustomImage imagesRes = customImage3.sepiaConversion(100);
    assertNotNull(imagesRes);
    assertEquals(customImage3.getHeight(), imagesRes.getHeight());
    assertEquals(customImage3.getWidth(), imagesRes.getWidth());

    //Expected Result
    List<List<List<Integer>>> expectedResult = Arrays.asList(
        Arrays.asList(
            Arrays.asList(12, 11, 8, 255),
            Arrays.asList(12, 11, 8, 255),
            Arrays.asList(12, 11, 8, 255),
            Arrays.asList(12, 11, 8, 255)
        ),
        Arrays.asList(
            Arrays.asList(92, 82, 64, 255),
            Arrays.asList(92, 82, 64, 255),
            Arrays.asList(92, 82, 64, 255),
            Arrays.asList(92, 82, 64, 255)
        ),
        Arrays.asList(
            Arrays.asList(158, 140, 109, 255),
            Arrays.asList(158, 140, 109, 255),
            Arrays.asList(158, 140, 109, 255),
            Arrays.asList(158, 140, 109, 255)
        ),
        Arrays.asList(
            Arrays.asList(293, 261, 203, 255),
            Arrays.asList(293, 261, 203, 255),
            Arrays.asList(293, 261, 203, 255),
            Arrays.asList(293, 261, 203, 255)
        ),
        Arrays.asList(
            Arrays.asList(31, 28, 22, 255),
            Arrays.asList(31, 28, 22, 255),
            Arrays.asList(31, 28, 22, 255),
            Arrays.asList(31, 28, 22, 255)
        )
    );

    for (int i = 0; i < imagesRes.getHeight(); i++) {
      for (int j = 0; j < imagesRes.getWidth(); j++) {
        assertEquals(expectedResult.get(i).get(j), imagesRes.get(i, j).getPixelRGBA());
      }
    }
  }

  @Test
  public void testSepiaPercentage1() {
    int percentage = 20;
    CustomImage imageRes = customImage4.sepiaConversion(percentage);

    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {

          for (int row = 0; row < 3; row++) {

            List<Double> newRow = Constants.SEPIA_MATRIX.get(row);

            int red = originalPixelRGBA.get(0);
            int green = originalPixelRGBA.get(1);
            int blue = originalPixelRGBA.get(2);

            double expectedValue = (
                newRow.get(0) * red +
                    newRow.get(1) * green +
                    newRow.get(2) * blue
            );

            assertEquals((int) expectedValue, (int) newPixelRGBA.get(row));

          }
        }
      }
    }
  }

  @Test
  public void testSepiaPercentage2() {
    int percentage = 40;
    CustomImage imageRes = customImage4.sepiaConversion(percentage);

    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {

          for (int row = 0; row < 3; row++) {

            List<Double> newRow = Constants.SEPIA_MATRIX.get(row);

            int red = originalPixelRGBA.get(0);
            int green = originalPixelRGBA.get(1);
            int blue = originalPixelRGBA.get(2);

            double expectedValue = (
                newRow.get(0) * red +
                    newRow.get(1) * green +
                    newRow.get(2) * blue
            );

            assertEquals((int) expectedValue, (int) newPixelRGBA.get(row));

          }
        }
      }
    }
  }

  @Test
  public void testSepiaPercentage3() {
    int percentage = 60;
    CustomImage imageRes = customImage4.sepiaConversion(percentage);

    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {

          for (int row = 0; row < 3; row++) {

            List<Double> newRow = Constants.SEPIA_MATRIX.get(row);

            int red = originalPixelRGBA.get(0);
            int green = originalPixelRGBA.get(1);
            int blue = originalPixelRGBA.get(2);

            double expectedValue = (
                newRow.get(0) * red +
                    newRow.get(1) * green +
                    newRow.get(2) * blue
            );
            assertEquals((int) expectedValue, (int) newPixelRGBA.get(row));
          }
        }
      }
    }
  }

  @Test
  public void testSepiaPercentage4() {
    int percentage = 80;
    CustomImage imageRes = customImage4.sepiaConversion(percentage);

    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {

          for (int row = 0; row < 3; row++) {

            List<Double> newRow = Constants.SEPIA_MATRIX.get(row);

            int red = originalPixelRGBA.get(0);
            int green = originalPixelRGBA.get(1);
            int blue = originalPixelRGBA.get(2);

            double expectedValue = (
                newRow.get(0) * red +
                    newRow.get(1) * green +
                    newRow.get(2) * blue
            );

            assertEquals((int) expectedValue, (int) newPixelRGBA.get(row));

          }
        }
      }
    }
  }

  @Test
  public void testSepiaPercentage5() {
    int percentage = 100;
    CustomImage imageRes = customImage4.sepiaConversion(percentage);

    assertNotNull(imageRes);
    assertEquals(customImage4.getHeight(), imageRes.getHeight());
    assertEquals(customImage4.getWidth(), imageRes.getWidth());

    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        List<Integer> originalPixelRGBA = customImage4.get(i, j).getPixelRGBA();
        List<Integer> newPixelRGBA = imageRes.get(i, j).getPixelRGBA();

        boolean isChangedPixel = !originalPixelRGBA.subList(0, 3)
            .equals(newPixelRGBA.subList(0, 3));

        if (isChangedPixel) {

          for (int row = 0; row < 3; row++) {

            List<Double> newRow = Constants.SEPIA_MATRIX.get(row);

            int red = originalPixelRGBA.get(0);
            int green = originalPixelRGBA.get(1);
            int blue = originalPixelRGBA.get(2);

            double expectedValue = (
                newRow.get(0) * red +
                    newRow.get(1) * green +
                    newRow.get(2) * blue
            );

            assertEquals((int) expectedValue, (int) newPixelRGBA.get(row));

          }
        }
      }
    }
  }

}
