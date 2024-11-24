package controller;

import commands.BlueComponent;
import commands.Blur;
import commands.Brighten;
import commands.ColorCorrect;
import commands.Command;
import commands.Compress;
import commands.Dither;
import commands.GreenComponent;
import commands.Histogram;
import commands.HorizontalFlip;
import commands.IntensityComponent;
import commands.LevelsAdjust;
import commands.LumaComponent;
import commands.RGBCombine;
import commands.RGBSplit;
import commands.RedComponent;
import commands.Sepia;
import commands.Sharpen;
import commands.ValueComponent;
import commands.VerticalFlip;
import fileutils.JPGUtil;
import fileutils.PNGUtil;
import fileutils.PPMUtil;
import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.CustomModel;
import util.Constants;

/**
 * The Controller class is used to parse input into instructions and feed the instructions to the
 * controller class. It will return and integer to the Main/View class indicating the status of the
 * command. The Controller class only checks the syntax correctness of the
 */
public class ImageController implements CustomController {

  protected CustomModel imageModel;
  InputStream in;
  OutputStream out;

  protected String printStatus(int status) {
    switch (status) {
      // Status code 0 means instruction ran successfully, program continues
      case 0:
        return "Instruction Ran Successfully";
      // Status code 1 means instruction ran successfully, program exits
      case 1:
        return "Exiting Program";
      // Status code 2 means instruction ran unsuccessfully, program continues
      case 2:
        return "Error running command, try again";
      // Status code 3 means instruction ran unsuccessfully, program exists
      case 3:
        return "Error Occurred, Exiting Program";
      // Default statement
      default:
        return "Command entered was invalid";
    }
  }

  // Private helper to turn a List<List<CustomPixel>> into a image that can be displayed by the GUI
  protected BufferedImage pixelToImage(List<List<CustomPixel>> pixelList) {
    int height = pixelList.size();
    int width = pixelList.get(0).size();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Populate the BufferedImage with RGB values, ignoring the alpha channel
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        List<Integer> curPixelRGBA = pixelList.get(i).get(j).getPixelRGBA();
        int red = curPixelRGBA.get(0);
        int green = curPixelRGBA.get(1);
        int blue = curPixelRGBA.get(2);

        // Combine RGB into an integer (JPG doesn't support alpha)
        int rgb = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }

  // Helper function to turn a file into a customImage
  protected void loadFunction(String fileName, String imageName) throws IOException {
    // Checks if the file exists first
    File file = new File(fileName);
    if (!file.exists()) {
      throw new IOException(fileName + " doesn't exist, please try again");
    }
    // Makes sure the file exists, and pass the command to be done by model
    String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
    CustomImage newImage = null;
    switch (fileType) {
      case Constants.PPM_STR:
        newImage = new PPMUtil().fileToCustomImage(fileName);
        break;
      case Constants.PNG_STR:
        newImage = new PNGUtil().fileToCustomImage(fileName);
        break;
      case Constants.JPG_STR:
        newImage = new JPGUtil().fileToCustomImage(fileName);
        break;
      default:
        System.out.println("Please enter a valid file name");
    }
    if (newImage != null) {
      imageModel.loadImage(newImage, imageName);
    } else {
      System.out.println("Image not loaded");
    }
  }

  // Helper function to turn a customImage into a file
  protected void saveFunction(String fileName, String imageName) throws IOException {
    // Check if the destination is empty
    File file = new File(fileName);
    if (file.exists()) {
      throw new IOException(fileName + " already exists");
    }

    // Check if image exists
    List<List<CustomPixel>> imagePixelList = imageModel.getImage(imageName);
    if (imagePixelList == null) {
      throw new IOException(imageName + " not found.");
    }

    String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);

    // Uses the appropriate class to save the CustomImage to a file
    boolean isSuccessful = false;
    try {

      switch (fileType) {
        case Constants.PPM_STR:
          new PPMUtil().writeCustomImageToFile(new BasicImage(imagePixelList), file);
          isSuccessful = true;
          break;
        case Constants.PNG_STR:
          new PNGUtil().writeCustomImageToFile(new BasicImage(imagePixelList), file);
          isSuccessful = true;
          break;
        case Constants.JPG_STR:
          new JPGUtil().writeCustomImageToFile(new BasicImage(imagePixelList), file);
          isSuccessful = true;
          break;
        default:
          System.out.println("Please enter a valid file name");
      }

      if (isSuccessful) {
        System.out.println(fileName + " was saved successfully");
      } else {
        System.out.println(fileName + " was not saved successfully");
      }

    } catch (IOException e) {
      System.out.println("Error occurred, file was not saved successfully");
    }
  }

  // Helper function that plots values onto a histogram, turn it into a CustomImage, and save it as
  // newImageName
  protected void createHistogram(List<List<Integer>> values, String newImageName)
      throws IOException {

    // Turn the values into a bufferedImage histogram
    BufferedImage image = getImageFromValues(values);
    // Turn the BufferedImage into a CustomImage
    int width = image.getWidth();
    int height = image.getHeight();
    List<List<CustomPixel>> customPixelList = new ArrayList<>();

    for (int y = 0; y < height; y++) {
      List<CustomPixel> curRow = new ArrayList<>();
      for (int x = 0; x < width; x++) {

        // Get pixel color at (x, y)
        int pixel = image.getRGB(x, y);

        // Extract RGBA values
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        curRow.add(new BasicPixel(red, green, blue, alpha));
      }
      customPixelList.add(curRow);
    }
    // Create our customImage from the customPixelList and add it to the hashMap
    imageModel.loadImage(new BasicImage(customPixelList), newImageName);

  }

  // Helper function to plot an array as a BufferedImage histogram
  private BufferedImage getImageFromValues(List<List<Integer>> values) {
    // Get the max and min for normalization
    int minValue = values.get(0).get(0);
    int maxValue = values.get(0).get(0);
    for (List<Integer> value : values) {
      for (Integer nums : value) {
        minValue = Math.min(minValue, nums);
        maxValue = Math.max(maxValue, nums);
      }
    }
    // Normalize the values
    for (List<Integer> value : values) {
      for (int i = 0; i < value.size(); i++) {
        int normVal = (int) ((value.get(i) - minValue) / (double) (maxValue - minValue)
            * 256);
        value.set(i, normVal);
      }
    }

    // Turn the values into a histogram
    BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();

    // Set background color to white
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 256, 256);
    // If we are plotting 3 different types of values
    int prevRedHeight = 0;
    int prevGreenHeight = 0;
    int prevBlueHeight = 0;
    for (int x = 1; x < 256; x++) { // Start from 1 to use previous values
      int redHeight = values.get(0).get(x);
      int greenHeight = values.get(1).get(x);
      int blueHeight = values.get(2).get(x);

      // Draw line for Red channel
      g2d.setColor(Color.RED);
      g2d.drawLine(x - 1, 256 - prevRedHeight, x, 256 - redHeight);

      // Draw line for Green channel
      g2d.setColor(Color.GREEN);
      g2d.drawLine(x - 1, 256 - prevGreenHeight, x, 256 - greenHeight);

      // Draw line for Blue channel
      g2d.setColor(Color.BLUE);
      g2d.drawLine(x - 1, 256 - prevBlueHeight, x, 256 - blueHeight);

      // Update previous heights
      prevRedHeight = redHeight;
      prevGreenHeight = greenHeight;
      prevBlueHeight = blueHeight;
    }

    g2d.dispose();
    return image;
  }

  /**
   * Public constructor for the controller class which takes in a View which implements the
   * CustomView interface and a imageModel which implements the ImageModel interface.
   */
  public ImageController(CustomModel imageModel, InputStream in,
      OutputStream out) {
    this.imageModel = imageModel;
    this.in = in;
    this.out = out;
  }

  @Override
  public void start() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.in))) {
      String command;
      int status;
      while ((command = reader.readLine()) != null) {
        if (!Objects.equals(command, "exit")) {
          status = parse(command);
          System.out.println(printStatus(status));
        } else {
          return;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int parse(String input) {

    // If our input starts with a #, is empty or a space we just ignore the comment
    if (input.isEmpty() || input.charAt(0) == '#' || input.equals(" ")) {
      return Constants.RAN_SUCCESSFULLY_PROGRAM_CONTINUES;
    }

    // Split the input into strings
    List<String> inputArray = List.of(input.split(" "));

    // Switch case statement based on the first word of the command
    try {
      String commandStr = inputArray.get(0);
      Command command = null;
      switch (commandStr) {
        case Constants.LOAD_STR:
          if (inputArray.size() != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments passed");
          }
          loadFunction(inputArray.get(1), inputArray.get(2));
          break;
        case Constants.SAVE_STR:
          if (inputArray.size() != 3) {
            throw new IllegalArgumentException("Incorrect number of arguments passed");
          }
          saveFunction(inputArray.get(1), inputArray.get(2));
          break;
        case Constants.RED_COMPONENT_STR:
          command = new RedComponent(inputArray);
          break;
        case Constants.GREEN_COMPONENT_STR:
          command = new GreenComponent(inputArray);
          break;
        case Constants.BLUE_COMPONENT_STR:
          command = new BlueComponent(inputArray);
          break;
        case Constants.VALUE_COMPONENT_STR:
          command = new ValueComponent(inputArray);
          break;
        case Constants.INTENSITY_COMPONENT_STR:
          command = new IntensityComponent(inputArray);
          break;
        case Constants.LUMA_COMPONENT_STR:
          command = new LumaComponent(inputArray);
          break;
        case Constants.HORIZONTAL_FLIP_STR:
          command = new HorizontalFlip(inputArray);
          break;
        case Constants.VERTICAL_FLIP_STR:
          command = new VerticalFlip(inputArray);
          break;
        case Constants.BRIGHTEN_STR:
          command = new Brighten(inputArray);
          break;
        case Constants.RGB_SPLIT_STR:
          command = new RGBSplit(inputArray);
          break;
        case Constants.RGB_COMBINE_STR:
          command = new RGBCombine(inputArray);
          break;
        case Constants.BLUR_STR:
          command = new Blur(inputArray);
          break;
        case Constants.SHARPEN_STR:
          command = new Sharpen(inputArray);
          break;
        case Constants.SEPIA_STR:
          command = new Sepia(inputArray);
          break;
        case Constants.COMPRESS_STR:
          command = new Compress(inputArray);
          break;
        case Constants.HISTOGRAM_STR:
          command = new Histogram(inputArray);
          break;
        case Constants.COLOR_CORRECT_STR:
          command = new ColorCorrect(inputArray);
          break;
        case Constants.LEVELS_ADJUST_STR:
          command = new LevelsAdjust(inputArray);
          break;
        case Constants.DITHER:
          command = new Dither(inputArray);
          break;
        case Constants.RUN_STR:
        case Constants.FILE_STR:
          if (inputArray.size() != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments passed");
          }
          runScript(inputArray.get(1));
          break;
        case Constants.EXIT_STR:
          return Constants.RAN_SUCCESSFULLY_PROGRAM_EXISTS;
        default:
          System.out.println("Invalid command passed: " + inputArray.get(0));
          return Constants.RAN_UNSUCCESSFULLY_PROGRAM_CONTINUES;
      }
      if (command != null) {
        command.run(imageModel);
      }
    } catch (IllegalArgumentException |
             IOException e) {
      System.out.println(e.getMessage());
      return Constants.RAN_UNSUCCESSFULLY_PROGRAM_CONTINUES;
    }

    return Constants.RAN_SUCCESSFULLY_PROGRAM_CONTINUES;
  }

  @Override
  public void runScript(String filename) {
    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(filename))) {
      String fileLine;
      while ((fileLine = bufferedReader.readLine()) != null) {
        parse(fileLine);
      }
    } catch (IOException e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }
}
