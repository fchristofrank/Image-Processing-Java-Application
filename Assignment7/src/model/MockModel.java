package model;

import image.CustomImage;
import image.CustomPixel;
import java.io.IOException;
import java.util.List;

/**
 * The MockModel class allows us to test the Model class' behaviour by mimicking the classes method
 * outputs.
 */
public class MockModel implements CustomModel {

  StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public List<List<CustomPixel>> getImage(String imageName) {
    log.append("getImage" + " " + imageName + "\n");
    return null;
  }

  @Override
  public void loadImage(CustomImage image, String name) throws IOException {
    log.append("loadImage" + " " + name + "\n");
  }

  @Override
  public void splitImageByRGB(String imageName, String newImageRedName, String newImageGreenName,
      String newImageBlueName, int percentage) {
    log.append("splitImageByRGB" + " " + imageName + " " + newImageRedName + " "
        + newImageGreenName + " " + newImageBlueName + " " + percentage + "\n");
  }

  @Override
  public void createValueImage(String imageName, String newImageName, int percentage) {
    log.append("createValueImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createIntensityImage(String imageName, String newImageName, int percentage) {
    log.append(
        "createIntensityImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createLumaImage(String imageName, String newImageName, int percentage) {
    log.append("createLumaImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createHorizontallyImage(String imageName, String newImageName) {
    log.append("createHorizontallyImage" + " " + imageName + " " + newImageName + "\n");
  }

  @Override
  public void createVerticallyImage(String imageName, String newImageName) {
    log.append("createVerticallyImage" + " " + imageName + " " + newImageName + "\n");
  }

  @Override
  public void brightenImage(int brightnessInt, String imageName, String newImageName) {
    log.append("brightenImage" + " " + brightnessInt + " " + imageName + " " + newImageName + "\n");
  }

  @Override
  public void combineImageByRGB(String imageName, String imageRedName, String imageGreenName,
      String imageBlueName) {
    log.append("combineImageByRGB" + " " + imageName + " " + imageRedName + " "
        + imageGreenName + " " + imageBlueName + "\n");
  }

  @Override
  public void createBlurredImage(String imageName, String newImageName, int percentage) {
    log.append(
        "createBlurredImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createSharpenedImage(String imageName, String newImageName, int percentage) {
    log.append(
        "createSharpenedImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createSepiaTonedImage(String imageName, String newImageName, int percentage) {
    log.append(
        "createSepiaTonedImage" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void createCompressImage(int percentile, String imageName, String newImageName) {
    log.append(
        "createCompressImage" + " " + percentile + " " + imageName + " " + newImageName + "\n");
  }

  @Override
  public List<List<Integer>> getImageValues(String imageName) {
    log.append("getImageValues" + " " + imageName + "\n");
    return null;
  }

  @Override
  public void colorCorrection(String imageName, String newImageName, int percentage) {
    log.append("colorCorrection" + " " + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void levelsAdjustImage(int black, int mid, int white, String imageName,
      String newImageName, int percentage) {
    log.append("levelsAdjustImage" + " " + black + " " + mid + " " + white + " "
        + imageName + " " + newImageName + " " + percentage + "\n");
  }

  @Override
  public void applyMask(String imageName, String originalImageName, String maskImageName) {
    log.append(
        "applyMask" + " " + imageName + " " + originalImageName + " " + maskImageName + "\n");
  }

  @Override
  public void createDitheredImage(String imageName, String newImageName, int percentage) {

  }
}
