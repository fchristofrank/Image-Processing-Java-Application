package model;

import image.BasicImage;
import image.BasicPixel;
import image.CustomImage;
import image.CustomPixel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Model class is responsible for storing all images and mapping each image to a respective
 * image name within a Map collection while implementing the ImageActions interface.
 */
public class ImageModel implements CustomModel {

  //Hashmap to map each imageName to a File object (our images currently)
  private CustomImage customImage;
  private Map<String, CustomImage> map;

  /**
   * Empty constructor for initializing an object of the Model class with an empty Map collection.
   */
  public ImageModel() {
    this.map = new HashMap<>();
  }

  /**
   * Retrieves the pixel values of the image in a list of custom pixel lists.
   *
   * @param imageName the original image name
   * @return the pixel list of the custom image
   */
  public List<List<CustomPixel>> getImage(String imageName) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      return customImage.getPixelList();
    } else {
      System.out.println(imageName + " not image found");
    }
    return null;
  }


  @Override
  public void loadImage(CustomImage image, String name) throws IOException {
    map.put(name, image);
  }


  @Override
  public void createHorizontallyImage(String imageName, String newImageName) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.horizontalFlip());
    }
  }

  @Override
  public void createVerticallyImage(String imageName, String newImageName) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.verticalFlip());
    }
  }

  @Override
  public void brightenImage(int brightnessInt, String imageName, String newImageName) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.increaseBrightness(brightnessInt));
    }
  }

  @Override
  public void splitImageByRGB(String imageName, String newImageRedName, String
      newImageGreenName,
      String newImageBlueName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      List<CustomImage> imagesRes = customImage.splitRGB(percentage);
      if (!Objects.equals(newImageRedName, "")) {
        this.map.put(newImageRedName, imagesRes.get(0));
      }
      if (!Objects.equals(newImageGreenName, "")) {
        this.map.put(newImageGreenName, imagesRes.get(1));
      }
      if (!Objects.equals(newImageBlueName, "")) {
        this.map.put(newImageBlueName, imagesRes.get(2));
      }
    }
  }

  @Override
  public void createValueImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      map.put(newImageName, customImage.valueConversion(percentage));
    }
  }

  @Override
  public void createIntensityImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      map.put(newImageName, customImage.intensityConversion(percentage));
    }

  }

  @Override
  public void createLumaImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      map.put(newImageName, customImage.lumaConversion(percentage));
    }

  }

  @Override
  public void combineImageByRGB(String imageName, String imageRedName, String
      imageGreenName,
      String imageBlueName) {
    // Check if the dimensions are the same
    CustomImage imageRed = this.map.get(imageRedName);
    CustomImage imageGreen = this.map.get(imageGreenName);
    CustomImage imageBlue = this.map.get(imageBlueName);
    // If any of the images are not found
    if (imageRed == null || imageGreen == null || imageBlue == null) {
      return;
    }
    // If the images do not share the same height or width
    if (imageRed.getHeight() != imageBlue.getHeight()
        || imageRed.getHeight() != imageGreen.getHeight()
        || imageRed.getWidth() != imageBlue.getWidth()
        || imageRed.getWidth() != imageGreen.getWidth()) {
      return;
    }
    CustomImage res = new BasicImage(
        new ArrayList<>(
            List.of(
                new ArrayList<>(List.of(new BasicPixel(255, 255, 255, 255)))
            )
        )
    );
    res.combineRGB(imageRed, imageGreen, imageBlue);
    this.map.put(imageName, res);
  }

  @Override
  public void createCompressImage(int percentage, String imageName, String newImageName) {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Invalid percentage passed to method");
    }
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.compressConversion(percentage));
    }
  }


  /**
   * Retrieves the pixel values of the given image.
   *
   * @param imageName name of the image loaded in during application run
   * @return the pixel values of the given image
   */
  public List<List<Integer>> getImageValues(String imageName) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      return customImage.getValues();
    } else {
      return null;
    }
  }

  @Override
  public void colorCorrection(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.colorCorrection(percentage));
    }
  }

  @Override
  public void levelsAdjustImage(int b, int m, int w, String imageName,
      String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.levelsAdjust(b, m, w, percentage));
    }
  }


  @Override
  public void createBlurredImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.blurConversion(percentage));
    }
  }

  @Override
  public void createSharpenedImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.sharpenConversion(percentage));
    }
  }

  @Override
  public void createSepiaTonedImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.sepiaConversion(percentage));
    }
  }

  @Override
  public void applyMask(String modifiedImage, String originalImageString, String maskImageString) {
    customImage = this.map.get(modifiedImage);
    CustomImage originalImage = this.map.get(originalImageString);
    CustomImage maskImage = this.map.get(maskImageString);
    if (customImage != null && maskImage != null && originalImage != null) {
      this.map.put(modifiedImage, customImage.maskApply(originalImage, maskImage));
    }
  }

  @Override
  public void createDitheredImage(String imageName, String newImageName, int percentage) {
    customImage = this.map.get(imageName);
    if (customImage != null) {
      this.map.put(newImageName, customImage.ditherImage(percentage));
    }
  }

}
