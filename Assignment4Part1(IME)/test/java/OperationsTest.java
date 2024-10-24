import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ime.imageIO.ImageFormat;
import ime.imageIO.ImageReader;
import ime.imageIO.ImageReaderFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class OperationsTest extends ImageOperationTest {

  //BrightnessTest
  @Test
  public void testFullBrightenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.png",
            "manhattan-small-full-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.png", outputFileMap,
              "AdjustBrightnessImages",
              replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  //BrightnessTest
  @Test
  public void testBrightenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-brighten-actual.png",
            "manhattan-small-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "50");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.png", outputFileMap,
              "AdjustBrightnessImages",
              replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testFullDarkenPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.png",
            "manhattan-small-full-darken-expected.png");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    try {
      runImageTest("TestScripts/darken.txt", "manhattan-small.png",
              outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testAdjustBrightnessInvalidArgumentPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.png",
            "manhattan-full-brighten-expected.png");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.png", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

  //commented due to number of files limitation on the server.
//  @Test
//  public void testFullBrightenJPG() {
//    HashMap<String, String> outputFileMap = new HashMap<>();
//    outputFileMap.put("manhattan-small-full-brighten-actual.jpg",
//            "manhattan-small-full-brighten-expected.jpg");
//
//    HashMap<String, String> replacements = new HashMap<>();
//    replacements.put("<alpha_value>", "300");
//
//    try {
//      runImageTest("TestScripts/brighten.txt",
//              "manhattan-small.jpg", outputFileMap,
//              "AdjustBrightnessImages", replacements, (expected, actual)
//                      -> assertEquals("Images should be identical", expected, actual));
//    } catch (IllegalArgumentException e) {
//      fail("Exception shouldn't be thrown");
//    }
//  }
//
//  @Test
//  public void testFullDarkenJPG() {
//    HashMap<String, String> outputFileMap = new HashMap<>();
//    outputFileMap.put("manhattan-small-full-darken-actual.jpg",
//            "manhattan-small-full-darken-expected.jpg");
//
//    HashMap<String, String> replacements = new HashMap<>();
//    replacements.put("<alpha_value>", "500");
//
//    try {
//      runImageTest("TestScripts/darken.txt", "manhattan-small.jpg",
//              outputFileMap, "AdjustBrightnessImages", replacements, (expected, actual)
//                      -> assertEquals("Images should be identical", expected, actual));
//    } catch (IllegalArgumentException e) {
//      fail("Exception shouldn't be thrown");
//    }
//  }

  @Test
  public void testAdjustBrightnessInvalidArgumentJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.jpg",
            "manhattan-full-brighten-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.jpg", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

  @Test
  public void testFullBrightenPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-brighten-actual.ppm",
            "manhattan-small-full-brighten-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.ppm",
              outputFileMap, "AdjustBrightnessImages",
              replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testFullDarkenPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-full-darken-actual.ppm",
            "manhattan-small-full-darken-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "500");

    try {
      runImageTest("TestScripts/darken.txt", "manhattan-small.ppm",
              outputFileMap, "AdjustBrightnessImages", replacements,
              (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testAdjustBrightnessInvalidArgumentPPM() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-fail-brighten-actual.ppm",
            "manhattan-full-brighten-expected.ppm");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/brighten.txt",
              "manhattan-small.ppm", outputFileMap,
              "AdjustBrightnessImages", replacements,
              (expected, actual) ->
                      assertNotEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is thrown as the arguments are invalid.
    }
  }

  //ChainOperationTest
  @Test
  public void testMultipleOperation() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-chain-expected.png", "manhattan-small-chain-expected.png");

    Map<String, String> replacements = new HashMap<>();
    replacements.put("<alpha_value>", "300");
    try {
      runImageTest("TestScripts/chain-operation.txt",
              "manhattan-small.png", outputFileMap,
              "ChainOperationImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  //CombineTest
  @Test
  public void combineTest() {

    runImageTest(
            "TestScripts/combine.txt",
            "manhattan-small.png",
            "combine_actual.png",
            "combine_expected.png",
            "CombineImages");

    runImageTest(
            "TestScripts/combine.txt",
            "manhattan-small.jpg",
            "combine_actual.jpg",
            "combine_expected.jpg",
            "CombineImages");

  }

  @Test(expected = RuntimeException.class)
  public void combineFailureTest() {
    runImageTest(
            "FailureTestScripts/combineFailure.txt",
            "manhattan-small.png",
            "combine_actual.png",
            "combine_expected.png",
            "CombineImages");
  }

  //Commented due to file size issues (number of files)
 /* @Test(expected = IllegalArgumentException.class)
  public void combineDimensionTestRed() {

    MultipleImageOperation combine = new Combine();
    Image largeDimensionImage = loadImageFromResources("manhattan-small.png");
    Image smallDimensionImage = loadImageFromResources("white_test.png");

    combine.apply(Arrays.asList(largeDimensionImage, smallDimensionImage, smallDimensionImage), "");
  }*/

  //Commented due to file size issues (number of files).
  /*@Test
  public void combineDimensionTest() {
    MultipleImageOperation combine = new Combine();
    Image largeDimensionImage = loadImageFromResources("manhattan-small.png");
    Image smallDimensionImage = loadImageFromResources("white_test.png");

    try {
      combine.apply(
              Arrays.asList(largeDimensionImage, smallDimensionImage, smallDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for first test");
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      combine.apply(
              Arrays.asList(smallDimensionImage, largeDimensionImage, smallDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for second test");
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      combine.apply(
              Arrays.asList(smallDimensionImage, smallDimensionImage, largeDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for third test");
    } catch (IllegalArgumentException e) {
      // Expected
    }

    largeDimensionImage = loadImageFromResources("CombineImages/black_test_dimension_test.png");
    smallDimensionImage = loadImageFromResources("white_test.png");

    try {
      combine.apply(
              Arrays.asList(largeDimensionImage, smallDimensionImage, smallDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for first test");
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      combine.apply(
              Arrays.asList(smallDimensionImage, largeDimensionImage, smallDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for second test");
    } catch (IllegalArgumentException e) {
      // Expected
    }

    try {
      combine.apply(
              Arrays.asList(smallDimensionImage, smallDimensionImage, largeDimensionImage), "");
      fail("Expected IllegalArgumentException not thrown for third test");
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }*/

  @Test
  public void testFromCLI() {
    URL inputURL = getClass().getClassLoader().getResource("TestScripts/script");
    Assert.assertNotNull("Test script not found", inputURL);
    Path inputScriptPath;
    try {
      inputScriptPath = Paths.get(inputURL.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    String command = "run " + inputScriptPath + "\n" + "exit";
    runTest(command);
  }

  //FilterOperationTest
  @Test
  public void testBlur() {

    // Blur JPG test
    runImageTest(
            "TestScripts/blur.txt",
            "manhattan-small.jpg",
            "manhattan-small-blur-actual.jpg",
            "manhattan-small-blur-expected.jpg",
            "FilterImages");

    // Blur PNG test
    runImageTest(
            "TestScripts/blur.txt",
            "manhattan-small.png",
            "manhattan-small-blur-actual.png",
            "manhattan-small-blur-expected.png",
            "FilterImages");

    //Commented due to file size issues.
    /*// Blur White jpg test
    runImageTest(
            "TestScripts/blur.txt",
            "white_test.jpg",
            "white_test_actual.jpg",
            "white_test_blur_expected.jpg",
            "FilterImages");

    // Blur White png test
    runImageTest(
            "TestScripts/blur.txt",
            "white_test.png",
            "white_test_actual.png",
            "white_test_blur_expected.png",
            "FilterImages");

    // Blur Black png test
    runImageTest(
            "TestScripts/blur.txt",
            "black_test.png",
            "black_test_actual.png",
            "black_test_blur_expected.png",
            "FilterImages");

    // Blur Black jpg test
    runImageTest(
            "TestScripts/blur.txt",
            "black_test.jpg",
            "black_test_actual.jpg",
            "black_test_blur_expected.jpg",
            "FilterImages");*/
  }

  @Test
  public void testSharpen() {
    runImageTest(
            "TestScripts/sharpen.txt",
            "manhattan-small.jpg",
            "manhattan-small-sharpen-actual.jpg",
            "manhattan-small-sharpen-expected.jpg",
            "FilterImages");
    Map<String, String> outputFileMap1 = new HashMap<>();
    outputFileMap1.put(
            "manhattan-small-sharpen-actual.jpg", "manhattan-small-sharpen-expected.jpg");

    Map<String, String> outputFileMap2 = new HashMap<>();
    outputFileMap2.put(
            "manhattan-small-sharpen-actual.png", "manhattan-small-sharpen-expected.png");

    runImageTest(
            "TestScripts/sharpen.txt",
            "manhattan-small.png",
            "manhattan-small-sharpen-actual.png",
            "manhattan-small-sharpen-expected.png",
            "FilterImages");
  }

  //Commented due to file size issues.
    /*runImageTest(
            "TestScripts/sharpen.txt",
            "white_test.jpg",
            "white_test_sharpen_actual.jpg",
            "white_test_sharpen_expected.jpg",
            "FilterImages");


    runImageTest(
            "TestScripts/sharpen.txt",
            "white_test.png",
            "white_test_sharpen_actual.png",
            "white_test_sharpen_expected.png",
            "FilterImages");
  }
*/
  //Flip Operation
  @Test
  public void testHorizontalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.png", "manhattan-small-hflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.png", "manhattan-small-vflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsPNG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.png", "manhattan-small-vflip-expected.png");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.png",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception has been thrown due to invalid arguments.
    }
  }

  @Test
  public void testHorizontalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.jpg", "manhattan-small-hflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.jpg", "manhattan-small-vflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsJPG() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.jpg", "manhattan-small-vflip-expected.jpg");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.jpg",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception has been thrown due to invalid arguments.
    }
  }

  @Test
  public void testHorizontalFlipPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-hflip-actual.ppm", "manhattan-small-hflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/hflip.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-actual.ppm", "manhattan-small-vflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/vflip.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testVerticalFlipInvalidArgsPPM() {
    Map<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-vflip-invalid-actual.ppm", "manhattan-small-vflip-expected.ppm");

    Map<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/Fail/vflip-invalid.txt", "manhattan-small.ppm",
              outputFileMap, "FlipImages", replacements, (expected, actual) ->
                      assertEquals("Images should be identical", expected, actual));
      fail("Exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //Exception have been thrown due to invalid number of arguments.
    }
  }

  //Sepia
  @Test
  public void testSepiaPNG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-sepia-actual.png", "manhattan-small-sepia-expected.png");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/sepia.txt", "manhattan-small.png",
              outputFileMap, "FilterImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  @Test
  public void testSepiaJPG() {
    HashMap<String, String> outputFileMap = new HashMap<>();
    outputFileMap.put("manhattan-small-sepia-actual.jpg", "manhattan-small-sepia-expected.jpg");

    HashMap<String, String> replacements = new HashMap<>();

    try {
      runImageTest("TestScripts/sepia.txt", "manhattan-small.jpg",
              outputFileMap, "FilterImages", replacements, (expected, actual)
                      -> assertEquals("Images should be identical", expected, actual));
    } catch (IllegalArgumentException e) {
      fail("Exception shouldn't be thrown");
    }
  }

  //RGBSplit
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

  //Commented due to number of files limitation.
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

  @Test
  public void testSetup() {
    URL inputURL = getClass().getResource("TestScripts/visualize");
    if (inputURL != null) {
      try {
        Path filePath = Paths.get(inputURL.toURI());

        URL inputImageURL = getClass().getResource("manhattan-small.png");
        URL outputFolderURL = getClass().getClassLoader().getResource("VisualizeImages");

        if (inputImageURL != null && outputFolderURL != null) {
          String inputImagePath = Paths.get(inputImageURL.toURI()).toString();
          String outputImagePath = Paths.get(outputFolderURL.toURI()).toString();

          String commands = getCommandsFromFile(filePath.toString());

          commands = commands.replace("<inputFile>", inputImagePath).replace("<destinationPath>", outputImagePath);

          runTest(commands);
        } else {
          throw new RuntimeException("Failed to load input/output resources.");
        }
      } catch (URISyntaxException | IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new RuntimeException("Resource not found: TestScripts/visualize");
    }
  }

  @Test
  public void testVisualizeLuma() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/luma.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/luma.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeRed() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/red.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/red.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeBlue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/blue.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/blue.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeGreen() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/green.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/green.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeIntensity() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/intensity.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/intensity.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }

  @Test
  public void testVisualizeValue() throws IOException {

    URL expectedImageURL = getClass().getResource("VisualizeImages/value.png");
    URL actualImageURL = getClass().getClassLoader().getResource("");

    ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
    assert expectedImageURL != null;
    assert actualImageURL != null;

    Image expectedImage = imageReader.read(expectedImageURL.getPath(), ImageType.RGB);
    Image actualImage = imageReader.read(actualImageURL.getPath() + "VisualizeImages/value.png", ImageType.RGB);

    assertEquals(expectedImage, actualImage);
  }
}