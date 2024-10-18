//package ime.operations;
//
//import ime.imageIO.ImageLibrary;
//import ime.models.Image;
//import ime.models.ImageType;
//import ime.models.Pixel;
//import ime.models.PixelFactory;
//import ime.models.SimpleImage;
//
//
//public abstract class AbstractVisualize implements ImageOperation {
//
//  public AbstractVisualize(ImageLibrary library) {
//    super(library);
//  }
//
//  @Override
//  public void apply(String[] args) throws IllegalArgumentException {
//    validateArgs(args);
//    String inputImageName = args[0];
//    String outputImageName = args[1];
//    Image inputImage = getImage(inputImageName);
//    Image outputImage = new SimpleImage(inputImage.getHeight(), inputImage.getWidth(), ImageType.RGB);
//
//    for (int i = 0; i < inputImage.getHeight(); i++) {
//      for (int j = 0; j < inputImage.getWidth(); j++) {
//        int colorValue = getColorComponent(inputImage.getPixel(i, j));
//        outputImage.setPixel(i, j, PixelFactory.createPixel(ImageType.RGB, colorValue, colorValue, colorValue));
//      }
//    }
//    addImage(outputImageName, outputImage);
//  }
//
//  // Abstract method to be implemented by all concrete classes.
//  protected abstract int getColorComponent(Pixel pixel);
//
//}
