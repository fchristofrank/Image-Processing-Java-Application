import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.imageio.ImageFormat;
import ime.controller.imageio.ImageReader;
import ime.controller.imageio.ImageReaderFactory;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.ImageOperationFactory;
import ime.model.image.Image;
import ime.model.image.ImageType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Objects;
import org.junit.Test;

public class DownscaleTest {

  private void runTest(String commandScript) {
    Readable readableInput = new StringReader(commandScript);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OperationCreator operationCreator = new ImageOperationFactory();
    new ImageProcessorCLI(readableInput, new PrintStream(outputStream), operationCreator).run();
  }

  @Test
  public void testDownscaleAllFormats() {

    String path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript = "load "
        + path
        + "testImage.png testPNG\n"
        + "load "
        + path
        + "testImage.jpg testJPG\n"
        + "load "
        + path
        + "testImage.ppm testPPM\n"
        + "downscale testPNG outputPNG 1 1\n"
        + "save "
        + path
        + "outputPNG.png outputPNG\n"
        + "downscale testJPG outputJPG 1 1 \n"
        + "save "
        + path
        + "outputJPG.jpg outputJPG\n"
        + "downscale testPPM outputPPM 1 1\n"
        + "save "
        + path
        + "outputPPM.ppm outputPPM\n"
        + "save "
        + path
        + "outputPPM.png outputPPM\n"
        + "exit";

    runTest(commandScript);
    Image actualImage;

    try {
      // 1. PNG Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputPNG.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 2. JPG Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputJPG.jpg", ImageType.RGB);
      int[] expectedPixelValue = new int[]{722712};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 3. PPM Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PPM);
      actualImage = imageReader.read(path + "outputPPM.ppm", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 4. PPM to PNG Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputPPM.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownscaleIllegalArgumentsFewerArguments() {

    ImageOperationFactory factory = new ImageOperationFactory();
    CLIOperation operation = factory.createOperation("downscale");
    operation.execute("ImageName","150");
  }

  @Test
  public void testDownscaleIllegalArguments() {
    // Helper method to test invalid arguments
    class Helper {
      void runTest(String imageName, String outputName, String width, String height) {
        try {
          ImageOperationFactory factory = new ImageOperationFactory();
          CLIOperation operation = factory.createOperation("downscale");
          operation.execute(imageName, outputName, width, height);

          // The test should fail if invalid arguments do not throw an exception.
          fail("Invalid arguments to Downscale Operation.");
        } catch (IllegalArgumentException ignored) {
          // Expected Error
        }
      }
    }
    Helper helper = new Helper();

    // Explicit test cases
    helper.runTest("ImageName", "OutputImage", "1", "-1");
    helper.runTest("ImageName", "OutputImage", "-1", "1");
    helper.runTest("ImageName", "OutputImage", "-1", "-1");
    helper.runTest("ImageName", "OutputImage", "0", "1");
    helper.runTest("ImageName", "OutputImage", "1", "0");
    helper.runTest("ImageName", "OutputImage", "0", "0");
    // Size greater than image
    helper.runTest("ImageName", "OutputImage", "5", "0");
    // Size greater than image
    helper.runTest("ImageName", "OutputImage", "0", "5");
    // Size greater than image
    helper.runTest("ImageName", "OutputImage", "5", "5");
    // Without loading the image
    helper.runTest("unknownImage", "OutputImage", "1", "1");
  }

  @Test
  public void testDownscaleOperation(){
    String path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript = "load "
        + path
        + "testImage.png testPNG\n"
        + "load "
        + path
        + "testImage.jpg testJPG\n"
        + "load "
        + path
        + "testImage.ppm testPPM\n"
        + "downscale testPNG outputPNG 1 1\n"
        + "save "
        + path
        + "outputPNG.png outputPNG\n"
        + "downscale testJPG outputJPG 1 2 \n"
        + "save "
        + path
        + "outputJPG.jpg outputJPG\n"
        + "downscale testPPM outputSameDimension 2 1\n"
        + "downscale testPPM outputPPM 2 2\n"
        + "save "
        + path
        + "outputSameDimension.ppm outputPPM\n"
        + "save "
        + path
        + "outputPPM.ppm outputPPM\n"
        + "save "
        + path
        + "outputPPM.png outputPPM\n"
        + "exit";

    runTest(commandScript);
    Image actualImage;

    try {
      // 1. same Aspect Ratio 2X2 to 1X1
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputPNG.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{1315860};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());

    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 2. Different aspect Ratio.
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputJPG.jpg", ImageType.RGB);
      int[] expectedPixelValue = new int[]{657427};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 3. PPM Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PPM);
      actualImage = imageReader.read(path + "outputPPM.ppm", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680,0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 4. Different Aspect Ration
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "outputPPM.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680,0,0,0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 4. Same Dimension
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PPM);
      actualImage = imageReader.read(path + "outputSameDimension.ppm", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680,0,0,0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testMaskOperation() {
    //half mask

    String path;
    path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript;
    commandScript = new StringBuilder().append("load ").append(path)
        .append("testImageMasking.png inputImageName\n").append("load ").append(path)
        .append("maskImage.png maskImageName\n")
        .append("brighten 50 inputImageName maskImageName brightened\n").append("save ")
        .append(path).append("brightened.png brightened\n")
        .append("darken 50 inputImageName maskImageName darkened\n").append("save ").append(path)
        .append("darkened.png darkened\n")
        .append("red-component inputImageName maskImageName red\n").append("save ").append(path)
        .append("redMask.png red\n").append("green-component inputImageName maskImageName green\n")
        .append("save ").append(path).append("greenMask.png green\n")
        .append("blue-component inputImageName maskImageName blue\n").append("save ").append(path)
        .append("blueMask.png blue\n").append("luma-component inputImageName maskImageName luma\n")
        .append("save ").append(path).append("lumaMask.png luma\n")
        .append("intensity-component inputImageName maskImageName luma\n").append("save ")
        .append(path).append("intensityMask.png luma\n")
        .append("value-component inputImageName maskImageName luma\n").append("save ").append(path)
        .append("valueMask.png luma\n").append("blur inputImageName maskImageName blur\n")
        .append("save ").append(path).append("blurMask.png blur\n")
        .append("sharpen inputImageName maskImageName sharpened\n").append("save ").append(path)
        .append("sharpened.png sharpened\n").append("sepia inputImageName maskImageName sepia\n")
        .append("save ").append(path).append("sepiaMask.png sepia\n").append("exit").toString();

    runTest(commandScript);
    Image actualImage;

    try {
      // 1. Brighten Test
      ImageReader imageReader;
      imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "brightened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 3289855};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 2. Darken Test
      ImageReader imageReader;
      imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "darkened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 205};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 3. Red Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "redMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 4. Green Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "greenMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 5. Blue Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "blueMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 16777215};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 6. Luma Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "lumaMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 1184274};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 7. Value Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "valueMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 16777215};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 8. Intensity Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "intensityMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 5592405};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 9. Blur Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "blurMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 3096383};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 10. Sharpen Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "sharpened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 8355839};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 11. Sepia Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "sepiaMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680, 65280, 16776960, 3156769};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

  }


  @Test(expected = IllegalArgumentException.class)
  public void testMaskInvalidDimensionsOperation() {

    //Loads both Image of Invalid Dimensions.
    String path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript = "load "
        + path
        + "testImageMasking.png inputImageName\n"
        + "load "
        + path
        + "maskImage.png maskImageName\nexit";
    runTest(commandScript);
    OperationCreator operator = new ImageOperationFactory();
    CLIOperation sharpen = operator.createOperation("sharpen");
    //Throws error due to invalid dimensions in images.
    sharpen.execute("inputImageName", "maskImageName", "output");
  }

  @Test
  public void testMaskOperationWithFullMask() {

    String path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript = "load "
        + path
        + "testImageMasking.png inputImageName\n"
        + "load "
        + path
        + "fullMaskedImage.png maskImageName\n"
        + "brighten 50 inputImageName maskImageName brightened\n"
        + "save "
        + path
        + "brightened.png brightened\n"
        + "darken 50 inputImageName maskImageName darkened\n"
        + "save "
        + path
        + "darkened.png darkened\n"
        + "red-component inputImageName maskImageName red\n"
        + "save "
        + path
        + "redMask.png red\n"
        + "green-component inputImageName maskImageName green\n"
        + "save "
        + path
        + "greenMask.png green\n"
        + "blue-component inputImageName maskImageName blue\n"
        + "save "
        + path
        + "blueMask.png blue\n"
        + "luma-component inputImageName maskImageName luma\n"
        + "save "
        + path
        + "lumaMask.png luma\n"
        + "intensity-component inputImageName maskImageName luma\n"
        + "save "
        + path
        + "intensityMask.png luma\n"
        + "value-component inputImageName maskImageName luma\n"
        + "save "
        + path
        + "valueMask.png luma\n"
        + "blur inputImageName maskImageName blur\n"
        + "save "
        + path
        + "blurMask.png blur\n"
        + "sharpen inputImageName maskImageName sharpened\n"
        + "save "
        + path
        + "sharpened.png sharpened\n"
        + "sepia inputImageName maskImageName sepia\n"
        + "save "
        + path
        + "sepiaMask.png sepia\n"
        + "exit";

    runTest(commandScript);
    Image actualImage;

    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "brightened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16724530, 3342130, 16777010, 3289855};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "darkened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{13434880, 52480, 13487360, 205};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "darkened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{13434880, 52480, 13487360, 205};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 3. Red Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "redMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16777215, 0, 16777215, 0};


      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 4. Green Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "greenMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{0,16777215,16777215,0};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 5. Blue Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "blueMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{0,0,0,16777215};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 6. Luma Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "lumaMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{3552822,11974326,15527148,1184274};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 7. Value Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "valueMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16777215,16777215,16777215,16777215};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 8. Intensity Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "intensityMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{5592405,5592405,11184810,5592405};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 9. Blur Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "blurMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{6242063,3100447,6246175,3096383};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 10. Sharpen Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "sharpened.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16744255,8388415,16777023,8355839};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }

    try {
      // 11. Sepia Component Test
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "sepiaMask.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{6576453,12889992,16777166,3156769};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  @Test
  public void testOriginalImageIsConserved(){

    String path = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
    String commandScript = "load "
        + path
        + "testImageMasking.png inputImageName\n"
        + "load "
        + path
        + "testImageMasking.png maskImageName\n"
        + "brighten 50 inputImageName maskImageName brightened\n"
        + "save "
        + path
        + "applyAllFilter.png brightened\n"
        + "exit";

    runTest(commandScript);
    Image actualImage;

    try {
      ImageReader imageReader = ImageReaderFactory.createReader(ImageFormat.PNG);
      actualImage = imageReader.read(path + "applyAllFilter.png", ImageType.RGB);
      int[] expectedPixelValue = new int[]{16711680,65280,16776960,255};

      assertEquals(expectedPixelValue[0], actualImage.getPixel(0, 0).getColorComponents());
      assertEquals(expectedPixelValue[1], actualImage.getPixel(0, 1).getColorComponents());
      assertEquals(expectedPixelValue[2], actualImage.getPixel(1, 0).getColorComponents());
      assertEquals(expectedPixelValue[3], actualImage.getPixel(1, 1).getColorComponents());
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read image file", e);
    }
  }

  private void printPixels(Image actualImage) {
    System.out.printf("{%d,%d,%d,%d}",actualImage.getPixel(0, 0).getColorComponents()
        ,actualImage.getPixel(0, 1).getColorComponents()
        ,actualImage.getPixel(1, 0).getColorComponents()
        ,actualImage.getPixel(1, 1).getColorComponents());
  }
}


