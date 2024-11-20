package ime;

import ime.controller.GUIController;
import ime.controller.operation.GUIImageOperationFactory;
import ime.view.ImageEditorFrame;
import ime.view.ImageEditorView;

public class IMEProgram {
  public static void main(String []args) {
    ImageEditorView imageEditorView = new ImageEditorFrame("Image Editor");
    GUIController guiController = new GUIController(imageEditorView,
            new GUIImageOperationFactory(imageEditorView));
  }
}
