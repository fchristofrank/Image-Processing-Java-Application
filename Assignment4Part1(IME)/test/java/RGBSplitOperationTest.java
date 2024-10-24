import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionalities of rgb split image operation.
 */
public class RGBSplitOperationTest extends ImageOperationTest {
  @Test
  public void testRGBSplitPNG() {
    Map<String, String> outputFileMap = new LinkedHashMap<>();
    outputFileMap.put("manhattan-small-red-actual.png", "manhattan-small-red-expected.png");
    outputFileMap.put("manhattan-small-green-actual.png", "manhattan-small-green-expected.png");
    outputFileMap.put("manhattan-small-blue-actual.png", "manhattan-small-blue-expected.png");
    try {
      runImageTest("TestScripts/rgb-split.txt",
              "manhattan-small.png", outputFileMap, "RGBSplitImages",
              null, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

//  @Test
//  public void testRGBSplitJPG() {
//    Map<String, String> outputFileMap = new LinkedHashMap<>();
//    outputFileMap.put("manhattan-small-red-actual.jpg", "manhattan-small-red-expected.jpg");
//    outputFileMap.put("manhattan-small-green-actual.jpg", "manhattan-small-green-expected.jpg");
//    outputFileMap.put("manhattan-small-blue-actual.jpg", "manhattan-small-blue-expected.jpg");
//    try {
//      runImageTest("TestScripts/rgb-split.txt",
//              "manhattan-small.jpg", outputFileMap, "RGBSplitImages",
//              null, (expected, actual)
//                      -> assertEquals("Images should be identical", expected, actual));
//    } catch (IllegalArgumentException e) {
//      fail("Exception shouldn't be thrown");
//    }
//  }
//
//  @Test
//  public void testRGBSplitPPM() {
//    Map<String, String> outputFileMap = new LinkedHashMap<>();
//    outputFileMap.put("manhattan-small-red-actual.ppm", "manhattan-small-red-expected.ppm");
//    outputFileMap.put("manhattan-small-green-actual.ppm", "manhattan-small-green-expected.ppm");
//    outputFileMap.put("manhattan-small-blue-actual.ppm", "manhattan-small-blue-expected.ppm");
//    try {
//      runImageTest("TestScripts/rgb-split.txt",
//              "manhattan-small.ppm", outputFileMap, "RGBSplitImages",
//              null, (expected, actual)
//                      -> assertEquals("Images should be identical", expected, actual));
//    } catch (IllegalArgumentException e) {
//      fail("Exception shouldn't be thrown");
//    }
//  }

}
