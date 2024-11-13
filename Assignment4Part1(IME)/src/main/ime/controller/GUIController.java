package ime.controller;


import java.awt.image.BufferedImage;
import java.util.Stack;

import ime.controller.cli.OperationCreator;
import ime.controller.operation.CLIOperation;
import ime.view.ImageEditorView;

public class GUIController implements Features {
  private final OperationCreator imageOperationFactory;
  private final ImageEditorView imageEditorView;
  private final String ERROR_MESSAGE_TITLE = "ERROR";
  private final Stack<OperationCommand> undoStack = new Stack<>();
  private final Stack<OperationCommand> redoStack = new Stack<>();

  public GUIController(ImageEditorView imageEditorView, OperationCreator imageOperationFactory) {
    this.imageEditorView = imageEditorView;
    imageEditorView.addFeatures(this);
    this.imageOperationFactory = imageOperationFactory;
  }

  @Override
  public void loadImage(String imagePath) {
    CLIOperation imageOperation = imageOperationFactory.createOperation("load");
    try {
      imageOperation.execute(imagePath);
      undoStack.push(new OperationCommand(imageOperation, imagePath));
      redoStack.clear();
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void flipImage(String flipType) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(flipType);
    try {
      imageOperation.execute();
      undoStack.push(new OperationCommand(imageOperation));
      redoStack.clear();
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void applyFilter(String filterType, String splitWidth) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(filterType);
    try {
      imageOperation.execute(splitWidth);
      undoStack.push(new OperationCommand(imageOperation, splitWidth));
      redoStack.clear();
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void applyGreyScale(String grayScaleType) {
    CLIOperation imageOperation = imageOperationFactory.createOperation(grayScaleType);
    try {
      imageOperation.execute();
      undoStack.push(new OperationCommand(imageOperation));
      redoStack.clear();
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void saveImage(String imagePath) {
    CLIOperation imageOperation = imageOperationFactory.createOperation("save");
    try {
      imageOperation.execute(imagePath);
      undoStack.push(new OperationCommand(imageOperation, imagePath));
      redoStack.clear();
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void undo() {
    if (!undoStack.isEmpty()) {
      OperationCommand lastOperation = undoStack.pop();
      if(undoStack.isEmpty()) {
        imageEditorView.cleanSlate();
      }
      for (OperationCommand redoOperation : undoStack) {
        redoOperation.execute();
      }
      redoStack.push(lastOperation);
    }
  }

  @Override
  public void redo() {
    if (!redoStack.isEmpty()) {
      OperationCommand redoOperation = redoStack.pop();
      redoOperation.execute();
      undoStack.push(redoOperation);
    }
  }

  class OperationCommand {
    private final CLIOperation operation;
    private final String[] args;

    public OperationCommand(CLIOperation operation, String... args) {
      this.operation = operation;
      this.args = args;
    }

    public void execute() {
      this.operation.execute(args);
    }

  }


}
