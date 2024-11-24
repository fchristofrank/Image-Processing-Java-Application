package model;

import image.CustomImage;
import image.CustomPixel;
import java.io.IOException;
import java.util.List;

/**
 * This interface provides all possible commands that the application's Model will later implement.
 */
public interface CustomModel {

  /**
   * Returns an image in the model if the imageName exists.
   *
   * @param imageName the original image name
   * @return The image if it exists in model, otherwise null
   */
  List<List<CustomPixel>> getImage(String imageName);

  /**
   * Ingests an image and loads it into the local filesystem.
   *
   * @param image CustomImage object to be loaded in to the filesystem
   * @param name  the new name the file will be referred to throughout program execution
   * @throws IOException throw in the event of unforeseen file inconsistencies
   */
  void loadImage(CustomImage image, String name) throws IOException;

  /**
   * Ingests an image and splits the image based on its respective RBG values creating three
   * separate images.
   *
   * @param imageName         the original image name
   * @param newImageRedName   the new name of the red image file created
   * @param newImageGreenName the new name of the green image file created
   * @param newImageBlueName  the new name of the blue image file created
   */
  void splitImageByRGB(String imageName, String newImageRedName, String newImageGreenName,
      String newImageBlueName, int percentage);

  /**
   * Ingests an image and converts the pixels of the original image into a maximum rgb value in the
   * pixel.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createValueImage(String imageName, String newImageName, int percentage);

  /**
   * Ingests an image and converts the pixels of the original image into the average rgb value in
   * the pixel.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createIntensityImage(String imageName, String newImageName, int percentage);

  /**
   * Ingests an image and converts the pixels of the original image into the weighted sum value
   * 0.2126r + 0.7152g + 0.0722b value in the pixel.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createLumaImage(String imageName, String newImageName, int percentage);


  /**
   * Ingests an image and horizontally flips the pixels of the original image into a new image.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createHorizontallyImage(String imageName, String newImageName);

  /**
   * Ingests an image and vertically flips the pixels of the original image into a new image.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createVerticallyImage(String imageName, String newImageName);


  /**
   * Ingests an image and brightens the pixels of the original image into a new image.
   *
   * @param brightnessInt the integer value specifying the increase in brightness of the new image
   * @param imageName     the original image name
   * @param newImageName  the new name the file will be referred to throughout program execution
   */
  void brightenImage(int brightnessInt, String imageName, String newImageName);


  /**
   * Ingests three images image based on their respective RBG values creating a new combined images
   * representing all RBG values.
   *
   * @param imageName      the original image name
   * @param imageRedName   the name of the red image file already created
   * @param imageGreenName the name of the red image file already created
   * @param imageBlueName  the name of the red image file already created
   */
  void combineImageByRGB(String imageName, String imageRedName, String imageGreenName,
      String imageBlueName);


  /**
   * Ingests an image and blurs the pixels of the original image into a new image.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   * @param percentage   how much of the image should be converted starting from the left column
   */
  void createBlurredImage(String imageName, String newImageName, int percentage);

  /**
   * Ingests an image and sharpens the pixels of the original image into a new image.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   * @param percentage   how much of the image should be converted starting from the left column
   */
  void createSharpenedImage(String imageName, String newImageName, int percentage);

  /**
   * Ingests an image and converts the pixels of the original image into a sepia image.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   * @param percentage   how much of the image should be converted starting from the left column
   */
  void createSepiaTonedImage(String imageName, String newImageName, int percentage);

  /**
   * Ingests an image and based on the percentile passed compresses an image by the specified
   * percentile.
   *
   * @param percentile   the percentile but which the image should be compressed by
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   */
  void createCompressImage(int percentile, String imageName, String newImageName);


  /**
   * Return a list of occurrences of each component color. Only returns single list if the image is
   * a greyscale image.
   *
   * @return list of integers showing the amount of  times each component occurred in an image
   */
  List<List<Integer>> getImageValues(String imageName);

  /**
   * Ingests a histogram and aligns the meaningful peaks of the histogram.
   *
   * @param imageName    the original image name
   * @param newImageName the new name the file will be referred to throughout program execution
   * @param percentage   how much of the image should be converted starting from the left column
   */
  void colorCorrection(String imageName, String newImageName, int percentage);


  /**
   * Adjust the image levels by the specified black, mid, and white values.
   *
   * @param black      the lower bound value
   * @param mid        the middle bound value
   * @param white      the upper bound value
   * @param percentage how much of the image should be converted starting from the left column
   */
  void levelsAdjustImage(int black, int mid, int white, String imageName, String newImageName,
      int percentage);

  /**
   * Apply the mask operation on a specified image.
   *
   * @param imageName         the actual image that needs that the mask needs to be performed on
   * @param originalImageName the original image where we are getting the pixels from if the mask is
   *                          applied
   * @param maskImageName     the maskImage
   */
  void applyMask(String imageName, String originalImageName, String maskImageName);

  void createDitheredImage(String imageName, String newImageName, int percentage);
}
