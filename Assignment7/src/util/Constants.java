package util;

import java.util.Arrays;
import java.util.List;

/**
 * Contains all constant values for re-use.
 */
public class Constants {

  public static final String PNG_STR = "png";
  public static final String PPM_STR = "ppm";
  public static final String JPG_STR = "jpg";
  public static final String RUN_STR = "run";
  public static final String BLUR_STR = "blur";
  public static final String EXIT_STR = "exit";
  public static final String LOAD_STR = "load";
  public static final String SAVE_STR = "save";
  public static final String FILE_STR = "-file";
  public static final String SEPIA_STR = "sepia";
  public static final String SHARPEN_STR = "sharpen";
  public static final String BRIGHTEN_STR = "brighten";
  public static final String RGB_SPLIT_STR = "rgb-split";
  public static final String COMPRESS_STR = "compress";
  public static final String HISTOGRAM_STR = "histogram";
  public static final String COLOR_CORRECT_STR = "color-correct";
  public static final String LEVELS_ADJUST_STR = "levels-adjust";
  public static final String RGB_COMBINE_STR = "rgb-combine";
  public static final String VERTICAL_FLIP_STR = "vertical-flip";
  public static final String LUMA_COMPONENT_STR = "luma-component";
  public static final String HORIZONTAL_FLIP_STR = "horizontal-flip";
  public static final String VALUE_COMPONENT_STR = "value-component";
  public static final String INTENSITY_COMPONENT_STR = "intensity-component";
  public static final String RED_COMPONENT_STR = "red-component";
  public static final String GREEN_COMPONENT_STR = "green-component";
  public static final String BLUE_COMPONENT_STR = "blue-component";
  public static final String APPLY = "apply";
  public static final String DITHER = "dither";

  public static final int RAN_SUCCESSFULLY_PROGRAM_CONTINUES = 0;
  public static final int RAN_SUCCESSFULLY_PROGRAM_EXISTS = 1;
  public static final int RAN_UNSUCCESSFULLY_PROGRAM_CONTINUES = 2;
  public static final int RAN_UNSUCCESSFULLY_PROGRAM_EXISTS = 3;


  public static final List<List<Double>> SEPIA_MATRIX = Arrays.asList(
      List.of(0.393, 0.769, 0.189),
      List.of(0.349, 0.686, 0.168),
      List.of(0.272, 0.534, 0.131)
  );

  public static final List<List<Double>> GAUSSIAN_BLUR_MATRIX = Arrays.asList(
      List.of(0.0625, 0.125, 0.0625),
      List.of(0.125, 0.25, 0.125),
      List.of(0.0625, 0.125, 0.0625)
  );

  public static final List<List<Double>> SHARPEN_MATRIX = Arrays.asList(
      List.of(-0.125, -0.125, -0.125, -0.125, -0.125),
      List.of(-0.125, 0.25, 0.25, 0.25, -0.125),
      List.of(-0.125, 0.25, 1.0, 0.25, -0.125),
      List.of(-0.125, 0.25, 0.25, 0.25, -0.125),
      List.of(-0.125, -0.125, -0.125, -0.125, -0.125)
  );


}
