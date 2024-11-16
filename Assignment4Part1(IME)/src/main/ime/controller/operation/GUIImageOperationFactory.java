package ime.controller.operation;

import java.awt.image.BufferedImage;

import ime.model.image.Image;
import ime.view.ImageEditorView;

/**
 * A factory class that creates GUI-specific image operation controllers.  This factory extends the base `ImageOperationFactory` and adds GUI-specific functionality for updating the view after each operation.  It handles loading images, saving images, applying various image filters and adjustments, and displaying the resulting images and histograms in the GUI.
 */
public class GUIImageOperationFactory extends ImageOperationFactory {

  private final ImageEditorView imageEditorView;
  private final String IMAGE_NAME = "IMAGE";

  /**
   * Constructs a GUIImageOperationFactory.
   *
   * @param imageEditorView The ImageEditorView to update with the results of image operations.
   */
  public GUIImageOperationFactory(ImageEditorView imageEditorView) {
    super();
    this.imageEditorView = imageEditorView;
  }


  @Override
  public CLIOperation createOperation(String commandName) {
    switch (commandName) {
      case Commands.LOAD:
        return new Load(imageLibrary);
      case Commands.SAVE:
        return new Save(imageLibrary);
      case Commands.HORIZONTAL_FLIP:
        return new HorizontalFlip(imageLibrary);
      case Commands.VERTICAL_FLIP:
        return new VerticalFlip(imageLibrary);
      case Commands.BLUR:
      case Commands.SHARPEN:
      case Commands.SEPIA:
        return new FilterWithPreview(imageLibrary, commandName);
      case Commands.LUMA_COMPONENT:
      case Commands.RED_COMPONENT:
      case Commands.GREEN_COMPONENT:
      case Commands.BLUE_COMPONENT:
        return new VisualizeWithPreview(imageLibrary, commandName);
      case Commands.COLOR_CORRECTION:
        return new ColorCorrection(imageLibrary);
      case Commands.COMPRESS:
        return new Compress(imageLibrary);
      case Commands.LEVELS_ADJUST:
        return new AdjustLevel(imageLibrary);
      default:
        throw new IllegalArgumentException(commandName + " is not a valid operation.");
    }
  }

  /**
   * An inner class representing the Load operation.  Extends the base Load operation and adds functionality to update the view with the loaded image and its histogram.
   */
  class Load extends ImageOperationFactory.Load {
    /**
     * Constructs a Load operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public Load(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      if (args.length == 0) {
        throw new IllegalArgumentException("You must choose an image.");
      }
      super.execute(args[0], IMAGE_NAME);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  /**
   * An inner class representing the Save operation. Extends the base Save operation.
   */
  class Save extends ImageOperationFactory.Save {
    /**
     * Constructs a Save operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public Save(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      if (args.length == 0) {
        throw new IllegalArgumentException("You must specify the file path.");
      }
      super.execute(args[0], IMAGE_NAME);
    }
  }

  /**
   * An inner class representing the Histogram operation. Extends the base Histogram operation and adds functionality to update the view with the histogram.
   */
  class Histogram extends ImageOperationFactory.Histogram {
    /**
     * Constructs a Histogram operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public Histogram(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      String HISTOGRAM_NAME = "HISTOGRAM";
      super.execute(IMAGE_NAME, HISTOGRAM_NAME);
      setViewWithImageHistogram(getImage(HISTOGRAM_NAME));
    }

  }

  /**
   * An inner class representing the Horizontal Flip operation. Extends the base HorizontalFlip operation and adds functionality to update the view with the flipped image and its histogram.
   */
  class HorizontalFlip extends ImageOperationFactory.HorizontalFlip {
    /**
     * Constructs a HorizontalFlip operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public HorizontalFlip(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      super.execute(IMAGE_NAME, IMAGE_NAME);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }

  }

  /**
   * An inner class representing filter operations with preview. Extends the base FilterWithPreview operation and adds functionality to update the view.
   */
  class FilterWithPreview extends ImageOperationFactory.FilterWithPreview {
    /**
     * Constructs a FilterWithPreview operation controller.
     *
     * @param library The ImageRepo instance.
     * @param command The filter command.
     */
    public FilterWithPreview(ImageRepo library, String command) {
      super(library, command);
    }

    @Override
    public void execute(String... args) {
      String splitWidth = "100";
      if (args.length == 1) {
        splitWidth = args[0];
      }
      super.execute(IMAGE_NAME, IMAGE_NAME, splitWidth);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  /**
   * An inner class representing the Vertical Flip operation. Extends the base VerticalFlip operation and adds functionality to update the view with the flipped image and its histogram.
   */
  class VerticalFlip extends ImageOperationFactory.VerticalFlip {
    /**
     * Constructs a VerticalFlip operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public VerticalFlip(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      super.execute(IMAGE_NAME, IMAGE_NAME);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  /**
   * An inner class representing visualization operations with preview. Extends the base VisualizeWithPreview operation and adds functionality to update the view.
   */
  class VisualizeWithPreview extends ImageOperationFactory.VisualizeWithPreview {
    /**
     * Constructs a VisualizeWithPreview operation controller.
     *
     * @param library The ImageRepo instance.
     * @param command The visualization command.
     */
    public VisualizeWithPreview(ImageRepo library, String command) {
      super(library, command);
    }

    @Override
    public void execute(String... args) {
      String splitWidth = "100";
      if (args.length == 1) {
        splitWidth = args[0];
      }
      super.execute(IMAGE_NAME, IMAGE_NAME, splitWidth);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }

  }

  /**
   * An inner class representing the Color Correction operation. Extends the base ColorCorrection operation and adds functionality to update the view.
   */
  class ColorCorrection extends ImageOperationFactory.ColorCorrection {
    /**
     * Constructs a ColorCorrection operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public ColorCorrection(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      String splitWidth = "100";
      if (args.length == 1) {
        splitWidth = args[0];
      }
      super.execute(IMAGE_NAME, IMAGE_NAME, splitWidth);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  /**
   * An inner class representing the Compress operation. Extends the base Compress operation and adds functionality to update the view with the compressed image and its histogram.
   */
  class Compress extends ImageOperationFactory.Compress {
    /**
     * Constructs a Compress operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public Compress(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      if (args.length == 0) {
        throw new IllegalArgumentException("You must specify the compression ratio.");
      }
      super.execute(args[0], IMAGE_NAME, IMAGE_NAME);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  /**
   * An inner class representing the Adjust Level operation. Extends the base AdjustLevel operation and adds functionality to update the view with the adjusted image and its histogram.
   */
  class AdjustLevel extends ImageOperationFactory.AdjustLevel {
    /**
     * Constructs an AdjustLevel operation controller.
     *
     * @param library The ImageRepo instance.
     */
    public AdjustLevel(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      String splitWidth = "100";
      if (args.length < 3) {
        throw new IllegalArgumentException("You must specify all the values of levels to adjust.");
      }
      if (args.length == 4) {
        splitWidth = args[3];
      }
      super.execute(args[0], args[1], args[2], IMAGE_NAME, IMAGE_NAME, splitWidth);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  private void setViewWithImage(Image image) {
    BufferedImage bufferedImage = convertToBufferedImage(image);
    this.imageEditorView.setImage(bufferedImage);
  }

  private void setViewWithImageHistogram(Image image) {
    BufferedImage bufferedImage = convertToBufferedImage(image);
    this.imageEditorView.setHistogram(bufferedImage);
  }

}