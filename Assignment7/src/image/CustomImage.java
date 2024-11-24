package image;


import java.util.List;

/**
 * This interface represents a CustomImage object, which is used as the custom data structure to
 * store images. It is responsible for representing an image, and pass appropriate commands called
 * by the model to the CustomPixel objects if needed.
 */
public interface CustomImage {

  /**
   * Gets a single custom pixel object at xCoordinate and yCoordinate.
   *
   * @param xCoordinate horizontal coordinate
   * @param yCoordinate vertical coordinate
   * @return a CustomPixel object
   */
  CustomPixel get(int xCoordinate, int yCoordinate);


  /**
   * Retrieves the images height values as an integer.
   *
   * @return integer of image height
   */
  int getHeight();

  /**
   * Retrieves the images width values as an integer.
   *
   * @return integer of image width
   */
  int getWidth();

  /**
   * Horizontally flips an image.
   *
   * @return a BasicImage object that contains the horizontally flipped images pixels
   */
  CustomImage horizontalFlip();

  /**
   * Vertically flips an image.
   *
   * @return a BasicImage object that contains the vertically flipped images pixels
   */
  CustomImage verticalFlip();


  /**
   * Increases the brightness of a given image based on a user specified integer amount.
   *
   * @param brightness integer value specifying the increased value of the new image
   * @return a CustomImage object containing the modified image
   */
  CustomImage increaseBrightness(int brightness);

  /**
   * Converts pixels of existing image to gray scale using the value method.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage valueConversion(int percentage);

  /**
   * Converts pixels of existing image to gray scale using the luma method.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage lumaConversion(int percentage);

  /**
   * Converts pixels of existing image to gray scale using the intensity method.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage intensityConversion(int percentage);

  /**
   * Converts pixels of existing image to sepia using dot product multiplication.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage sepiaConversion(int percentage);

  /**
   * Converts pixels of existing image to blurred image using dot product multiplication.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage blurConversion(int percentage);

  /**
   * Converts pixels of existing image to sharpened image using dot product multiplication.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage sharpenConversion(int percentage);

  /**
   * Splits a single CustomImage object into three separate images based on their respective RBG
   * values.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return a List of CustomImage objects containing the modified images
   */
  List<CustomImage> splitRGB(int percentage);

  /**
   * Combines three CustomImage object into a single image based on their respective RBG values.
   *
   * @return a List of CustomImage objects containing the modified images
   */
  CustomImage combineRGB(CustomImage redImage, CustomImage blueImage, CustomImage greenImage);

  /**
   * Ingests an image and based on the percentile passed compresses an image by the specified
   * percentile.
   *
   * @param percentile the percentile but which the image should be compressed by
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage compressConversion(int percentile);

  /**
   * Ingests an image and based on the presence of RBG colors in the image creates a histogram that
   * corresponds to the intensity of all RBG color values.
   *
   * @return BasicImage object that contains the converted pixels
   */
  List<List<Integer>> getValues();

  /**
   * Ingests a histogram and aligns the meaningful peaks of the histogram.
   *
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage colorCorrection(int percentage);


  /**
   * Adjust the image levels by the specified black, mid, and white values.
   *
   * @param black      the lower bound value
   * @param mid        the middle bound value
   * @param white      the upper bound value
   * @param percentage how much of the image should be converted starting from the left column
   * @return BasicImage object that contains the converted pixels
   */
  CustomImage levelsAdjust(int black, int mid, int white, int percentage);

  /**
   * Adjust the image levels by the specified black, mid, and white values.
   *
   * @return A deep copy of the pixelList attribute of the BasicImage object
   */
  List<List<CustomPixel>> getPixelList();

  /**
   * Performs a mask operation on the current image. If the corresponding pixel in mask is white,
   * then the pixel fromm original is mapped to the current pixel. The function assumes the
   * dimensions of all the images passed in are the same.
   *
   * @param original the original image
   * @param mask     the mask image
   * @return the current object of which the method was called from
   */
  CustomImage maskApply(CustomImage original, CustomImage mask);

  /**
   * Performs a dithering operation on the current image.
   * @param percentage how much of the image should be converted starting from the left column.
   * @return the dithered image.
   */
  CustomImage ditherImage(int percentage);
}
