import org.junit.Test;

public class FilterTest extends ImageTestUtil {

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

    // Blur White jpg test
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
        "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "white_test.jpg",
            "white_test_actual.jpg",
            "white_test_expected.jpg",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "white_test.png",
            "white_test_actual.png",
            "white_test_expected.png",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "black_test.png",
            "black_test_actual.png",
            "black_test_expected.png",
            "FilterImages");

    runImageTest(
            "TestScripts/filter.txt",
            "black_test.jpg",
            "black_test_actual.jpg",
            "black_test_expected.jpg",
            "FilterImages");
  }

  @Test
  public void testSharpen() {
    runImageTest(
        "TestScripts/sharpen.txt",
        "manhattan-small.jpg",
        "manhattan-small-sharpen-actual.jpg",
        "manhattan-small-sharpen-expected.jpg",
        "FilterImages");

    runImageTest(
        "TestScripts/sharpen.txt",
        "manhattan-small.png",
        "manhattan-small-sharpen-actual.png",
        "manhattan-small-sharpen-expected.png",
        "FilterImages");

    runImageTest(
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

}
