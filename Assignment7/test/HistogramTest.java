import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import controller.ImageController;
import image.BasicPixel;
import image.CustomPixel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for exclusive testing on the createHistogram method.
 */
public class HistogramTest {

  private ImageModel imageModel;
  private ImageController imageController;

  // Helper method to create a row of pixels
  private List<CustomPixel> createPixelRow(int r, int g, int b, int a, int length) {
    return Collections.nCopies(length, new BasicPixel(r, g, b, a));
  }

  @Before
  public void setUp() {
    imageModel = new ImageModel();
    imageController = new ImageController(imageModel, System.in, System.out);


  }

  @Test
  public void histogramTest1() {
    int resultCode = this.imageController.parse("load res/mario.ppm mario");
    int expectedCode = 0;
    assertEquals(expectedCode, resultCode);

    resultCode = this.imageController.parse("histogram mario marioHistogram");
    assertEquals(expectedCode, resultCode);

    resultCode = this.imageController.parse(
        "save res/marioHistogram-Test.png marioHistogram");
    assertEquals(expectedCode, resultCode);

    ArrayList<int[]> expectedValues = new ArrayList<>(Arrays.asList(
        new int[]{0, 0, 175},
        new int[]{0, 33, 175},
        new int[]{0, 96, 425},
        new int[]{0, 121, 175},
        new int[]{0, 211, 825},
        new int[]{0, 255, 725},
        new int[]{1, 0, 175},
        new int[]{1, 47, 825},
        new int[]{1, 85, 175},
        new int[]{1, 125, 425},
        new int[]{1, 150, 175},
        new int[]{1, 224, 725},
        new int[]{2, 0, 175},
        new int[]{2, 47, 825},
        new int[]{2, 72, 175},
        new int[]{2, 139, 425},
        new int[]{2, 178, 725},
        new int[]{2, 243, 175}
    ));

    List<List<Integer>> pixelValueList = imageModel.getImageValues("mario");

    for (int[] expected : expectedValues) {
      int i = expected[0];
      int j = expected[1];
      int val = expected[2];

      if (i >= pixelValueList.size() || j >= pixelValueList.get(i).size() || !pixelValueList.get(i)
          .get(j)
          .equals(val)) {
        fail("Histogram values do not match");
      }
    }

    File file = new File("res/marioHistogram-Test.png");
    if (file.exists()) {
      file.delete();
    } else {
      fail("File was not created");
    }
  }

}
