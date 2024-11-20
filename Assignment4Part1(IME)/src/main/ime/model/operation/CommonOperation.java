package ime.model.operation;

import java.util.Map;

import ime.model.image.Image;

/**
 * This is a class that offers operations certain common operations. The calculateFrequencies are
 * implemented which are shared by histogram and color-correction respectively.
 */
public interface CommonOperation {

  /**
   * This Method must count the frequency of the pixels and return the number of pixels(frequency)
   * for a given pixel value.
   *
   * @param inputImage image to be counted.
   * @return the frequency map containing the map for red, green and blue channels.
   */
  Map<String, Map<Integer, Integer>> calculateFrequencies(Image inputImage);
}
