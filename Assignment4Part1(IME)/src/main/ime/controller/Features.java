package ime.controller;

/**
 * The Features interface defines the contract for image manipulation operations.
 * It provides methods for loading, modifying, and saving images, as well as
 * managing undo/redo functionality and preview modes.
 */
public interface Features {

  /**
   * Loads an image from the specified path.
   *
   * @param imagePath    the path to the image file
   * @param userDecision a boolean indicating user's decision (purpose not specified)
   */
  void loadImage(String imagePath, boolean userDecision);

  /**
   * Flips the loaded image based on the specified flip type.
   *
   * @param flipType the type of flip operation to perform
   */
  void flipImage(String flipType);

  /**
   * Applies a filter to the loaded image.
   *
   * @param isPreview  boolean indicating whether this is a preview operation
   * @param filterType the type of filter to apply
   * @param splitWidth the width at which to split the preview (if applicable)
   */
  void applyFilter(boolean isPreview, String filterType, String splitWidth);

  /**
   * Applies a grayscale effect to the loaded image.
   *
   * @param isPreview     boolean indicating whether this is a preview operation
   * @param grayScaleType the type of grayscale effect to apply
   * @param splitWidth    the width at which to split the preview (if applicable)
   */
  void applyGreyScale(boolean isPreview, String grayScaleType, String splitWidth);

  /**
   * Applies color correction to the loaded image.
   *
   * @param isPreview  boolean indicating whether this is a preview operation
   * @param splitWidth the width at which to split the preview (if applicable)
   */
  void applyColorCorrect(boolean isPreview, String splitWidth);

  /**
   * Compresses the loaded image.
   *
   * @param compressionRatio the ratio at which to compress the image
   */
  void applyCompress(String compressionRatio);

  /**
   * Adjusts the levels of the loaded image.
   *
   * @param isPreview boolean indicating whether this is a preview operation
   * @param args      variable number of arguments for level adjustment
   */
  void adjustLevels(boolean isPreview, String... args);

  /**
   * Saves the current image to the specified path.
   *
   * @param imagePath the path where the image should be saved
   */
  void saveImage(String imagePath);

  /**
   * Undoes the last operation performed on the image.
   */
  void undo();

  /**
   * Redoes the last undone operation on the image.
   */
  void redo();

  /**
   * Toggles the preview mode for image operations.
   *
   * @param splitWidth the width at which to split the preview
   */
  void togglePreview(String splitWidth);

  /**
   * Exits the preview mode.
   *
   * @param isEnabled boolean indicating whether to enable or disable preview mode
   */
  void exitPreviewMode(boolean isEnabled);

  /**
   * Checks if an image is both loaded and saved.
   *
   * @return true if an image is loaded and saved, false otherwise
   */
  boolean isLoadedAndSaved();

  void downScale(String width, String height);
}