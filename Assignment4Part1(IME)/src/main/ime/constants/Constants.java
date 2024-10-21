package ime.constants;

public class Constants {

  public static final int PIXEL_LOWER_LIMIT = 0;
  public static final int PIXEL_UPPER_LIMIT = 255;
  public static final float[][] GAUSSIAN_BLUR_KERNEL = {
          {1f / 16f, 1f / 8f, 1f / 16f},
          {1f / 8f, 1f / 4f, 1f / 8f},
          {1f / 16f, 1f / 8f, 1f / 16f}
  };

  public static final float[][] SHARPEN_KERNEL = {
          {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f},
          {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
          {-1f / 8f, 1f / 4f, 1, 1f / 4f, -1f / 8f},
          {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
          {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},

  };
}