package ime;

import ime.controller.gui.GUIController;
import ime.controller.operation.GUIImageOperationFactory;
import ime.view.gui.ImageEditorFrame;
import ime.view.gui.ImageEditorView;

public class IMEProgram {

  public static void main(String[] args) {
    ImageEditorView imageEditorView = new ImageEditorFrame("Image Editor");
    GUIController guiController = new GUIController(imageEditorView,
        new GUIImageOperationFactory(imageEditorView));
  }
}
