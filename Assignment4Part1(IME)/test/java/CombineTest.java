import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import ime.model.image.Image;
import ime.model.operation.Combine;
import ime.model.operation.MultipleImageOperation;

import static org.junit.Assert.fail;

public class CombineTest extends ImageOperationTest {

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

    /*runImageTest(
    "TestScripts/combine.txt",
    "manhattan-small.png",
    "combine_actual.png",
    "combine_expected.png",
    "CombineImages");*/
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

  @Test(expected = IllegalArgumentException.class)
  public void combineDimensionTestRed() {

    MultipleImageOperation combine = new Combine();
    Image largeDimensionImage = loadImageFromResources("manhattan-small.png");
    Image smallDimensionImage = loadImageFromResources("white_test.png");

    combine.apply(Arrays.asList(largeDimensionImage, smallDimensionImage, smallDimensionImage), "");
  }

  @Test
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
  }

  @Test
  public void testFromCLI(){
    URL inputURL = getClass().getClassLoader().getResource("TestScripts/script");
    Assert.assertNotNull("Test script not found", inputURL);
    Path inputScriptPath = null;
    try {
      inputScriptPath = Paths.get(inputURL.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    String command = "run "+inputScriptPath + "\n"+"exit" ;
    runTest(command);
  }
}
