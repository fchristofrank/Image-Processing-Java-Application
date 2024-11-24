import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
 * Test class for exclusive testing on the splitRGB / combineRBG methods.
 */
public class RGBCombineSplitTest {

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

  // Testing RGB-Split on a 4x4 matrix
  @Test
  public void testRGBSplit() {
    List<CustomImage> imagesRes = customImage1.splitRGB(100);

    // Check that imagesRes has 3 CustomImage objects
    assertEquals(imagesRes.size(), 3);
    // Check that each dimension is correct
    for (CustomImage image : imagesRes) {
      assertEquals(customImage1.getHeight(), image.getHeight());
      assertEquals(customImage1.getWidth(), image.getWidth());
    }

    // Check that imagesRes[0] contains only the red component and the rest are set to 0
    for (int i = 0; i < imagesRes.get(0).getHeight(); i++) {
      for (int j = 0; j < imagesRes.get(0).getWidth(); j++) {
        CustomPixel curPixel = imagesRes.get(0).get(i, j);
        CustomPixel originalPixel = customImage1.get(i, j);

        // Compare the red component
        assertEquals(curPixel.getPixelRGBA().get(0), originalPixel.getPixelRGBA().get(0));

        // Ensure green and blue components are 0
        assertEquals(originalPixel.getPixelRGBA().get(0), curPixel.getPixelRGBA().get(1));
        assertEquals(originalPixel.getPixelRGBA().get(0), curPixel.getPixelRGBA().get(2));
      }
    }

    // Check that imagesRes[1] contains only the green component and the rest are set to 0
    for (int i = 0; i < imagesRes.get(1).getHeight(); i++) {
      for (int j = 0; j < imagesRes.get(1).getWidth(); j++) {
        CustomPixel curPixel = imagesRes.get(1).get(i, j);
        CustomPixel originalPixel = customImage1.get(i, j);

        // Compare the red component
        assertEquals(curPixel.getPixelRGBA().get(1), originalPixel.getPixelRGBA().get(1));

        // Ensure green and blue components are 0
        assertEquals(originalPixel.getPixelRGBA().get(1), curPixel.getPixelRGBA().get(0));
        assertEquals(originalPixel.getPixelRGBA().get(1), curPixel.getPixelRGBA().get(2));
      }
    }

    // Check that imagesRes[1] contains only the blue component and the rest are set to 0
    for (int i = 0; i < imagesRes.get(2).getHeight(); i++) {
      for (int j = 0; j < imagesRes.get(2).getWidth(); j++) {
        CustomPixel curPixel = imagesRes.get(2).get(i, j);
        CustomPixel originalPixel = customImage1.get(i, j);

        // Compare the red component
        assertEquals(curPixel.getPixelRGBA().get(2), originalPixel.getPixelRGBA().get(2));

        // Ensure green and blue components are 0
        assertEquals(originalPixel.getPixelRGBA().get(2), curPixel.getPixelRGBA().get(0));
        assertEquals(originalPixel.getPixelRGBA().get(2), curPixel.getPixelRGBA().get(1));
      }
    }
  }

  // Testing RGB-Split+Combine (should be exact images) on 4x4 matrices
  @Test
  public void testRGBSplitCombine1() {
    List<CustomImage> imagesRes = customImage1.splitRGB(100);

    // Check that imagesRes has 3 CustomImage objects
    assertEquals(imagesRes.size(), 3);
    // Check that each dimension is correct
    for (CustomImage image : imagesRes) {
      assertEquals(customImage1.getHeight(), image.getHeight());
      assertEquals(customImage1.getWidth(), image.getWidth());
    }

    // Combine the imagesRes into imageRes, and check not null, height, width
    CustomImage imageRes = new BasicImage(new ArrayList<>(List.of(
        createPixelRow(125, 200, 45, 255, 4)
    ))).combineRGB(imagesRes.get(0),
        imagesRes.get(1),
        imagesRes.get(2));

    // customImage1 and imageRes should be identical so check non-null, height, width, and pixels
    assertNotNull(imageRes);
    assertEquals(customImage1.getHeight(), imageRes.getHeight());
    assertEquals(customImage1.getWidth(), imageRes.getWidth());
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(imageRes.get(i, j).getPixelRGBA(), customImage1.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing RGB-Split+Combine (should be exact images) on 4x5 matrices
  @Test
  public void testRGBSplitCombine2() {
    List<CustomImage> imagesRes = customImage2.splitRGB(100);

    // Check that imagesRes has 3 CustomImage objects
    assertEquals(imagesRes.size(), 3);
    // Check that each dimension is correct
    for (CustomImage image : imagesRes) {
      assertEquals(customImage2.getHeight(), image.getHeight());
      assertEquals(customImage2.getWidth(), image.getWidth());
    }

    // Combine the imagesRes into imageRes, and check not null, height, width
    CustomImage imageRes = new BasicImage(new ArrayList<>(List.of(
        createPixelRow(125, 200, 45, 255, 4)
    ))).combineRGB(imagesRes.get(0), imagesRes.get(1),
        imagesRes.get(2));

    // customImage2 and imageRes should be identical so check non-null, height, width, and pixels
    assertNotNull(imageRes);
    assertEquals(customImage2.getHeight(), imageRes.getHeight());
    assertEquals(customImage2.getWidth(), imageRes.getWidth());
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(imageRes.get(i, j).getPixelRGBA(), customImage2.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing RGB-Split+Combine (should be exact images) on 5x4 matrices
  @Test
  public void testRGBSplitCombine3() {
    List<CustomImage> imagesRes = customImage3.splitRGB(100);

    // Check that imagesRes has 3 CustomImage objects
    assertEquals(imagesRes.size(), 3);
    // Check that each dimension is correct
    for (CustomImage image : imagesRes) {
      assertEquals(customImage3.getHeight(), image.getHeight());
      assertEquals(customImage3.getWidth(), image.getWidth());
    }

    // Combine the imagesRes into imageRes, and check not null, height, width
    CustomImage imageRes = new BasicImage(new ArrayList<>(List.of(
        createPixelRow(125, 200, 45, 255, 4)
    ))).combineRGB(imagesRes.get(0), imagesRes.get(1),
        imagesRes.get(2));

    // customImage3 and imageRes should be identical so check non-null, height, width, and pixels
    assertNotNull(imageRes);
    assertEquals(customImage3.getHeight(), imageRes.getHeight());
    assertEquals(customImage3.getWidth(), imageRes.getWidth());
    for (int i = 0; i < imageRes.getHeight(); i++) {
      for (int j = 0; j < imageRes.getWidth(); j++) {
        assertEquals(imageRes.get(i, j).getPixelRGBA(), customImage3.get(i, j).getPixelRGBA());
      }
    }
  }

  // Testing RGB-Combine (should be exact images) on matrices with different dimensions
  @Test
  public void testRGBCombineInvalid1() {
    CustomImage imagesRes = new BasicImage(new ArrayList<>(List.of(
        createPixelRow(125, 200, 45, 255, 4)
    )));
    CustomImage res = imagesRes.combineRGB(customImage1, customImage2, customImage3);
    assertNull(res);
  }
}
