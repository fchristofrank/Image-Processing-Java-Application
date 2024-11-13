package ime.controller.operation;

import java.awt.image.BufferedImage;

import ime.model.image.Image;
import ime.view.ImageEditorView;

public class GUIImageOperationFactory extends ImageOperationFactory {

  private final ImageEditorView imageEditorView;
  private final String IMAGE_NAME = "IMAGE";

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
        return new VisualizeWithPreview(imageLibrary, commandName);
      default:
        throw new IllegalArgumentException(commandName + " is not a valid operation.");
    }
  }

  class Load extends ImageOperationFactory.Load {
    /**
     * Constructs a Load operation controller with the specified image library.
     *
     * @param library the ImageLibrary instance that provides access to the images.
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

  class Save extends ImageOperationFactory.Save {

    /**
     * Constructs a Save operation controller with the specified image library.
     *
     * @param library the ImageLibrary instance that provides access to the images.
     */
    public Save(ImageRepo library) {
      super(library);
    }

    @Override
    public void execute(String... args) {
      if(args.length == 0) {
        throw new IllegalArgumentException("You must specify the file path.");
      }
      super.execute(args[0], IMAGE_NAME);
    }
  }

  class Histogram extends ImageOperationFactory.Histogram {

    /**
     * Constructs an AbstractOperation with the specified image library.
     *
     * @param library the ImageLibrary to be used for image operations
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

  class HorizontalFlip extends ImageOperationFactory.HorizontalFlip {

    /**
     * This method creates an operation to flip the image horizontally.
     *
     * @param library the ImageLibrary to be used for image operations.
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

  class FilterWithPreview extends ImageOperationFactory.FilterWithPreview {

    public FilterWithPreview(ImageRepo library, String command) {
      super(library, command);
    }

    @Override
    public void execute(String... args) {
      String splitWidth = "100";
      if(args.length == 1){
        splitWidth = args[0];
      }
      super.execute(IMAGE_NAME, IMAGE_NAME, splitWidth);
      new Histogram(imageLibrary).execute();
      setViewWithImage(getImage(IMAGE_NAME));
    }
  }

  class VerticalFlip extends ImageOperationFactory.VerticalFlip {

    /**
     * This method creates an operation to flip the image vertically.
     *
     * @param library the ImageLibrary to be used for image operations.
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

  class VisualizeWithPreview extends ImageOperationFactory.VisualizeWithPreview {

    public VisualizeWithPreview(ImageRepo library, String command) {
      super(library, command);
    }

    @Override
    public void execute(String... args) {
      super.execute(IMAGE_NAME, IMAGE_NAME);
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
