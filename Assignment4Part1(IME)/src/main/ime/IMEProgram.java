package ime;

import ime.controller.GUIController;
import ime.view.ImageEditorFrame;
import ime.view.ImageEditorView;

public class IMEProgram {
  public static void main(String []args)
  {
    ImageEditorView imageEditorView = new ImageEditorFrame("IME");
    GUIController guiController = new GUIController(imageEditorView);
  }
}
