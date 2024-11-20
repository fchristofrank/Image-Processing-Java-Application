package ime.model.operation;

import java.util.HashMap;
import java.util.Map;

import ime.model.image.Image;

/**
 * Accepts an image and from the entire range of 0 to 255 counts the frequency of the pixels and
 * returns it in a map object for each channel.
 */
public class CountFrequency implements CommonOperation {

  @Override
  public Map<String, Map<Integer, Integer>> calculateFrequencies(Image inputImage) {

    int imageHeight = inputImage.getHeight();
    int imageWidth = inputImage.getWidth();

    Map<Integer, Integer> frequencyRed = new HashMap<>();
    Map<Integer, Integer> frequencyGreen = new HashMap<>();
    Map<Integer, Integer> frequencyBlue = new HashMap<>();

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {
        int red = inputImage.getPixel(i, j).getRed();
        int green = inputImage.getPixel(i, j).getGreen();
        int blue = inputImage.getPixel(i, j).getBlue();

        frequencyRed.put(red, frequencyRed.getOrDefault(red, 0) + 1);
        frequencyGreen.put(green, frequencyGreen.getOrDefault(green, 0) + 1);
        frequencyBlue.put(blue, frequencyBlue.getOrDefault(blue, 0) + 1);
      }
    }

    Map<String, Map<Integer, Integer>> result = new HashMap<>();
    result.put("red", frequencyRed);
    result.put("green", frequencyGreen);
    result.put("blue", frequencyBlue);

    return result;
  }
}
