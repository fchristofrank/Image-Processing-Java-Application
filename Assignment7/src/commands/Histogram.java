package commands;

import image.BasicImage;
import image.BasicPixel;
import image.CustomPixel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.CustomModel;

/**
 * Command class that implements that implement the Histogram Command used by the controller.
 */
public class Histogram implements Command {

  List<String> inputArray;
  int percentage;
  CustomModel model;

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
    model.loadImage(new BasicImage(customPixelList), newImageName);

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
   * Public constructor class for the Histogram command.
   */
  public Histogram(List<String> inputArray) {
    this.inputArray = inputArray;
    if (inputArray.size() != 3) {
      throw new IllegalArgumentException("Incorrect number of arguments passed");
    }
  }

  @Override
  public void run(CustomModel model) {
    try {
      this.model = model;
      List<List<Integer>> values = model.getImageValues(inputArray.get(1));
      if (values != null) {
        createHistogram(values, inputArray.get(2));
      }
    } catch (IOException e) {
      System.out.println("Histogram was not successfully created.");
    }

  }

}
