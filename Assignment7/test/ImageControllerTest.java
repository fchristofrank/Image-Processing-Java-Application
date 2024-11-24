import static org.junit.Assert.assertEquals;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.util.ArrayList;
import java.util.List;
import model.CustomModel;
import controller.ImageController;
import model.MockModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests each behaviour within the Controller class. These tests simply check whether the
 * arguments are passed correctly to the controller class. We use a log to verify the commands are
 * correctly received by the model. These tests assume that the input is correctly formated and only
 * ensures that there correctly formated inputs and received correctly by the model.
 */

public class ImageControllerTest {

  CustomImage customImage;
  ImageController imageController;
  CustomModel mockModel;
  StringBuilder log;

  @Before
  public void setUp() throws Exception {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    this.imageController = new ImageController(mockModel, System.in, System.out);

    List<List<CustomPixel>> pixelList = new ArrayList<>();

    pixelList.add(List.of(
        new BasicPixel(100, 200, 240, 255),
        new BasicPixel(50, 100, 150, 255),
        new BasicPixel(25, 50, 100, 255)
    ));

    customImage = new BasicImage(pixelList);

    imageController.parse("load res/mario.png mario");
    log.setLength(0);
  }

  @Test
  public void saveCommandTest() {
    imageController.parse("save res/marioCopy.png mario");
    assertEquals("getImage mario\n", log.toString());
  }

  @Test
  public void loadCommandTest() {
    imageController.parse("load res/mario.png mario");
    assertEquals("loadImage mario\n", log.toString());
  }

  @Test
  public void redComponent() {
    imageController.parse("red-component mario mario-Red");
    assertEquals("splitImageByRGB mario mario-Red   100\n", log.toString());
  }

  @Test
  public void redComponentPercentage() {
    imageController.parse("red-component mario mario-Red split 75");
    assertEquals("splitImageByRGB mario mario-Red   75\n", log.toString());
  }

  @Test
  public void greenComponent() {
    imageController.parse("green-component mario mario-Green");
    assertEquals("splitImageByRGB mario  mario-Green  100\n", log.toString());
  }

  @Test
  public void greenComponentPercentage() {
    imageController.parse("green-component mario mario-Green split 75");
    assertEquals("splitImageByRGB mario  mario-Green  75\n", log.toString());
  }

  @Test
  public void blueComponent() {
    imageController.parse("blue-component mario mario-Blue");
    assertEquals("splitImageByRGB mario   mario-Blue 100\n", log.toString());
  }

  @Test
  public void blueComponentPercentage() {
    imageController.parse("blue-component mario mario-Blue split 75");
    assertEquals("splitImageByRGB mario   mario-Blue 75\n", log.toString());
  }

  @Test
  public void rgbCombineTest() {
    imageController.parse("rgb-combine mario mario-Red mario-Green mario-Blue");
    assertEquals("combineImageByRGB mario mario-Red mario-Green mario-Blue\n", log.toString());
  }

  @Test
  public void rgbSplitTest() {
    imageController.parse("rgb-split mario mario-Red mario-Green mario-Blue");
    assertEquals("splitImageByRGB mario mario-Red mario-Green mario-Blue 100\n", log.toString());
  }

  @Test
  public void valueTest() {
    imageController.parse("value-component mario mario-Value");
    assertEquals("createValueImage mario mario-Value 100\n", log.toString());
  }

  @Test
  public void valuePercentageTest() {
    imageController.parse("value-component mario mario-Value split 75");
    assertEquals("createValueImage mario mario-Value 75\n", log.toString());
  }

  @Test
  public void intensity() {
    imageController.parse("intensity-component mario mario-Intensity");
    assertEquals("createIntensityImage mario mario-Intensity 100\n", log.toString());
  }

  @Test
  public void intensityPercentage() {
    imageController.parse("intensity-component mario mario-Intensity split 75");
    assertEquals("createIntensityImage mario mario-Intensity 75\n", log.toString());
  }

  @Test
  public void lumaTest() {
    imageController.parse("luma-component mario mario-Luma");
    assertEquals("createLumaImage mario mario-Luma 100\n", log.toString());
  }

  @Test
  public void lumaTestPercentage() {
    imageController.parse("luma-component mario mario-Luma split 75");
    assertEquals("createLumaImage mario mario-Luma 75\n", log.toString());
  }

  @Test
  public void sepiaTest() {
    imageController.parse("sepia mario mario-SepiaToned");
    assertEquals("createSepiaTonedImage mario mario-SepiaToned 100\n", log.toString());
  }

  @Test
  public void sepiaTestPercentage() {
    imageController.parse("sepia mario mario-SepiaToned split 75");
    assertEquals("createSepiaTonedImage mario mario-SepiaToned 75\n", log.toString());
  }

  @Test
  public void blurTest() {
    imageController.parse("blur mario mario-Blurred");
    assertEquals("createBlurredImage mario mario-Blurred 100\n", log.toString());
  }

  @Test
  public void blurTestPercentage() {
    imageController.parse("blur mario mario-Blurred split 75");
    assertEquals("createBlurredImage mario mario-Blurred 75\n", log.toString());
  }

  @Test
  public void sharpenTest() {
    imageController.parse("sharpen mario mario-Sharpened");
    assertEquals("createSharpenedImage mario mario-Sharpened 100\n", log.toString());
  }

  @Test
  public void sharpenTestPercentage() {
    imageController.parse("sharpen mario mario-Sharpened split 75");
    assertEquals("createSharpenedImage mario mario-Sharpened 75\n", log.toString());
  }

  @Test
  public void verticalTest() {
    imageController.parse("vertical-flip mario mario-Vertical");
    assertEquals("createVerticallyImage mario mario-Vertical\n", log.toString());
  }

  @Test
  public void horizontalTest() {
    imageController.parse("horizontal-flip mario mario-Horizontal");
    assertEquals("createHorizontallyImage mario mario-Horizontal\n", log.toString());
  }

  @Test
  public void histogramTest() {
    imageController.parse("histogram mario mario-Histogram");
    assertEquals("getImageValues mario\n", log.toString());
  }

  @Test
  public void compressTest() {
    imageController.parse("compress 75 mario mario-Compress");
    assertEquals("createCompressImage 75 mario mario-Compress\n", log.toString());
  }

  @Test
  public void colorCorrectTest() {
    imageController.parse("color-correct mario mario-colorCorrect");
    assertEquals("colorCorrection mario mario-colorCorrect 100\n", log.toString());
  }

  @Test
  public void colorCorrectPercentageTest() {
    imageController.parse("color-correct mario mario-colorCorrect split 50");
    assertEquals("colorCorrection mario mario-colorCorrect 50\n", log.toString());
  }

  @Test
  public void levelsAdjustTest() {
    imageController.parse("levels-adjust 10 11 12 mario mario-levels");
    assertEquals("levelsAdjustImage 10 11 12 mario mario-levels 100\n", log.toString());
  }

  @Test
  public void levelsAdjustPercentageTest() {
    imageController.parse("levels-adjust 10 11 12 mario mario-levels split 50");
    assertEquals("levelsAdjustImage 10 11 12 mario mario-levels 50\n", log.toString());
  }


}
