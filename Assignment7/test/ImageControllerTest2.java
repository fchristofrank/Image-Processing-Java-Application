import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import controller.ImageController;
import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests each behaviour within the Controller class. These tests assume that all
 * functions tested in BasicImageTest are fully functional, so will be focused on the controller and
 * model classes. We will not be testing the image operations themselves, such as vertical flip,
 * horizontal flip, etc, but checking whether a file inside the model can be saved as a file and a
 * file can be saved as an image in the model.
 */
public class ImageControllerTest2 {

  CustomImage customImage;
  ImageController imageController;
  ImageModel imageModel;


  @Before
  public void setUp() throws Exception {
    imageModel = new ImageModel();
    this.imageController = new ImageController(imageModel, System.in, System.out);

    List<List<CustomPixel>> pixelList = new ArrayList<>();

    pixelList.add(List.of(
        new BasicPixel(100, 200, 240, 255),
        new BasicPixel(50, 100, 150, 255),
        new BasicPixel(25, 50, 100, 255)
    ));

    customImage = new BasicImage(pixelList);
  }


  // Loads a png image file from the res folder
  @Test
  public void loadImageTest1() {
    int resultCode = imageController.parse("load res/mario.png mario");
    // Checks and makes sure the result code is successful
    assertEquals(0, resultCode);
    //Makes sure image exist in model

    assertNotNull(imageModel.getImage("mario"));
  }

  // Loads a ppm image file from the res folder
  @Test
  public void loadImageTest2() {
    int resultCode = imageController.parse("load res/mario.ppm mario");
    // Checks and makes sure the result code is successful
    assertEquals(0, resultCode);
    //Makes sure image exist in model
    assertNotNull(imageModel.getImage("mario"));
  }

  // Loads a jpg image file from the res folder
  @Test
  public void loadImageTest3() {
    int resultCode = imageController.parse("load res/mario.jpg mario");
    // Checks and makes sure the result code is successful
    assertEquals(0, resultCode);
    //Makes sure image exist in model
    assertNotNull(imageModel.getImage("mario"));
  }

  // Try to load an invalid image file from the res folder,
  // the model should be empty and not store it
  @Test
  public void loadImageTestInvalid1() {
    int resultCode = imageController.parse("load res/mario1.png mario");
    assertEquals(2, resultCode);
  }


  // Load from invalid directory
  @Test
  public void loadImageTestInvalid2() {
    int resultCode = imageController.parse("load invalid_directory/invalid.png invalid");
    assertEquals(2, resultCode);
  }

  // Try to load an image file from the res folder with an invalid number of commands,
  // the model should be empty and not store it
  @Test
  public void loadImageTestInvalid3() {
    int resultCode = imageController.parse("load res/mario1.png mario invalidArguement");
    assertEquals(2, resultCode);
  }

  // Save an image that exists in the model as a png file
  @Test
  public void saveImageTest1() {
    imageController.parse("load res/mario.png mario");
    int expectedCode = imageController.parse("save res/mario1.png mario");
    assertEquals(0, expectedCode);
    //Delete the file so we can re-run this command
    File file = new File("res/mario1.png");
    file.delete();
  }

  // Save an image that exists in the model as a ppm file
  @Test
  public void saveImageTest2() {
    imageController.parse("load res/mario.png mario");
    int expectedCode = imageController.parse("save res/mario1.ppm mario");
    assertEquals(0, expectedCode);
    //Delete the file so we can re-run this command
    File file = new File("res/mario1.ppm");
    file.delete();
  }

  // Save an image that exists in the model as a jpg file
  @Test
  public void saveImageTest3() {
    imageController.parse("load res/mario.png mario");
    int expectedCode = imageController.parse("save res/mario1.jpg mario");
    assertEquals(0, expectedCode);
    //Delete the file so we can re-run this command
    File file = new File("res/mario1.jpg");
    file.delete();
  }

  // Save an image that was modified by the model
  @Test
  public void saveImageTest4() {
    imageController.parse("load res/mario.png mario");
    imageController.parse("vertical-flip mario marioVertical");
    int expectedCode = imageController.parse("save res/mario1.jpg marioVertical");
    assertEquals(0, expectedCode);
    //Delete the file so we can re-run this command
    File file = new File("res/mario1.jpg");
    file.delete();
  }

  //Save an image that doesn't exist in the model
  @Test
  public void saveImageTestInvalid1() {
    int expectedCode = imageController.parse("save res/mario1.png mario");
    assertEquals(2, expectedCode);
  }

  // Save an image to a file that already exists
  @Test
  public void saveImageTestInvalid2() {
    int expectedCode = imageController.parse("save res/mario.png mario");
    assertEquals(2, expectedCode);
  }

  // Save an image with an invalid number of arguments for the command
  @Test
  public void saveImageTestInvalid3() {
    int expectedCode = imageController.parse("save res/mario.png mario invalid");
    assertEquals(2, expectedCode);
  }

  // Run commands from example_commands_file.txt
  @Test
  public void runFileScript1() {
    int resultCode = imageController.parse(
        "run commandscripts/example_commands_file.txt");
    int expectedCode = 0;
    assertEquals(resultCode, expectedCode);
    File file1 = new File("res/mario-brighter-new.png");
    File file2 = new File("res/mario-greyscale-new.png");
    File file3 = new File("res/mario-red-tint-new.png");
    file1.delete();
    file2.delete();
    file3.delete();
  }

  // Run commands from example_commands_file_1.txt
  @Test
  public void runCommandScript2() {
    int resultCode = imageController.parse(
        "run commandscripts/example_commands_file_1.txt");
    int expectedCode = 0;
    assertEquals(resultCode, expectedCode);
    File file1 = new File("res/mario-sharpen-new.png");
    File file2 = new File("res/mario-horizontal-sepia-new.png");
    File file3 = new File("res/mario-vertical-blur-new.png");
    file1.delete();
    file2.delete();
    file3.delete();
  }

  // Run script example_commands_file_1 with incorrect arguements
  @Test
  public void runCommandScriptTestInvalidArguments() {
    int resultCode = imageController.parse(
        "run commandscripts/example_commands_file_1.txt invalid_command");
    assertEquals(2, resultCode);
  }

  // Run empty file
  @Test
  public void runCommandScriptTestEmptyFile() {
    int resultCode = imageController.parse(
        "run commandscripts/empty_file.txt");
    assertEquals(0, resultCode);
  }

  // Run invalid commands from invalid_commands_file.txt

  @Test
  public void runCommandScriptInvalid1() {
    int resultCode = imageController.parse(
        "run commandscripts/invalid_commands_file.txt");
    int expectedCode = 0;
    assertEquals(resultCode, expectedCode);
  }

  // Invalid command given to the controller through parse function
  @Test
  public void invalidCommandTest1() {
    int resultCode = imageController.parse("invalid-command");
    int expectedCode = 2;
    assertEquals(resultCode, expectedCode);
  }

  @Test
  public void exitCommandTest() {
    int resultCode = imageController.parse("exit");
    assertEquals(1, resultCode);
  }

  @Test
  public void emptyStringCommandTest() {
    int resultCode = imageController.parse("");
    int expectedCode = 0;
    assertEquals(resultCode, expectedCode);
  }

  @Test
  public void spaceCommandTest() {
    int resultCode = imageController.parse(" ");
    int expectedCode = 0;
    assertEquals(resultCode, expectedCode);
  }

  @Test
  public void newLineCommandTest() {
    int resultCode = imageController.parse("\n");
    int expectedCode = 2;
    assertEquals(resultCode, expectedCode);
  }


}
