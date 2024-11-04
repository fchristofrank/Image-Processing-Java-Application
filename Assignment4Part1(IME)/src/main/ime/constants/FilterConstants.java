package ime.constants;

/**
 * This class contains constant values used in various image filter operations. Centralized place to
 * store constants that are used across multiple classes.
 */
public class FilterConstants {

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
  public static final double[][] SEPIA_COLOR_TRANSFORMATION = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };
}