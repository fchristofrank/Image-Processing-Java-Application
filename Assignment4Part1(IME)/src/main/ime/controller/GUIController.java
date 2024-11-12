package ime.controller;

import ime.controller.cli.OperationCreator;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.GUIImageOperationFactory;
import ime.view.ImageEditorView;

public class GUIController implements Features{
  private final OperationCreator imageOperationFactory;

  public GUIController(ImageEditorView imageEditorView) {
    imageEditorView.addFeatures(this);
    this.imageOperationFactory = new GUIImageOperationFactory(imageEditorView);
  }

  @Override
  public void loadImage(String imagePath) {
    CLIOperation imageOperation = imageOperationFactory.createOperation("load");
    try {
      imageOperation.execute(imagePath);
    } catch (IllegalArgumentException exception) {
      //delegate it to the UI
    }
  }

  @Override
  public void flipImage(String flipType) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(flipType);
    try{
      imageOperation.execute();
    }catch (IllegalArgumentException exception){
      //delegate it to the UI
    }
  }

  @Override
  public void applyFilter(String filterType, String splitWidth) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(filterType);
    try{
      imageOperation.execute(splitWidth);
    }catch (IllegalArgumentException exception){
      //delegate it to the UI
    }
  }

  @Override
  public void applyGreyScale(String grayScaleType) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(grayScaleType);
    try {
      imageOperation.execute();
    }catch (IllegalArgumentException exception){
      //delegate it to the UI
    }
  }

  @Override
  public void saveImage(String imagePath) {
    CLIOperation imageOperation = imageOperationFactory.createOperation("save");
    try{
      imageOperation.execute(imagePath);
    }catch (IllegalArgumentException exception){
      //delegate it to the UI
    }
  }


}
