package ime.controller.gui;

import ime.controller.cli.OperationCreator;
import ime.controller.operation.CLIOperation;
import ime.view.gui.ImageEditorView;
import java.util.Stack;

/**
 * The GUIController class implements the Features interface and manages the graphical user
 * interface operations for an image editor application.
 */
public class GUIController implements Features {

  private final OperationCreator imageOperationFactory;
  private final ImageEditorView imageEditorView;
  private final String ERROR_MESSAGE_TITLE = "ERROR";
  private final Stack<OperationCommand> undoStack = new Stack<>();
  private final Stack<OperationCommand> redoStack = new Stack<>();
  private OperationCommand lastPreviewEnabledOperation;
  private boolean isLoaded;
  private boolean isSaved;

  /**
   * Constructs a new GUIController.
   *
   * @param imageEditorView       The view component of the image editor.
   * @param imageOperationFactory The factory for creating image operations.
   */
  public GUIController(ImageEditorView imageEditorView, OperationCreator imageOperationFactory) {
    this.imageEditorView = imageEditorView;
    imageEditorView.addFeatures(this);
    this.imageOperationFactory = imageOperationFactory;
    isLoaded = false;
    isSaved = false;
  }

  @Override
  public void loadImage(String imagePath, boolean userDecision) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation("load");
      if (isLoadedAndNotSaved() && !userDecision) {
        imageEditorView.showWarningMessageBeforeLoading(imagePath);
        return;
      }
      imageOperation.execute(imagePath);
      undoStack.push(new OperationCommand(imageOperation, imagePath));
      redoStack.clear();
      isLoaded = true;
      isSaved = false;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void flipImage(String flipType) {
    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation(flipType);
      imageOperation.execute();
      undoStack.push(new OperationCommand(imageOperation));
      redoStack.clear();
      isSaved = false;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public boolean applyFilter(boolean isPreview, String filterType, String splitWidth) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation(filterType);
      imageOperation.execute(splitWidth);
      if (!isPreview) {
        undoStack.push(new OperationCommand(imageOperation, splitWidth));
        redoStack.clear();
        isSaved = false;
      } else {
        lastPreviewEnabledOperation = new OperationCommand(imageOperation, splitWidth);
      }
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  @Override
  public boolean applyGreyScale(boolean isPreview, String grayScaleType, String splitWidth) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation(grayScaleType);
      imageOperation.execute(splitWidth);
      if (!isPreview) {
        undoStack.push(new OperationCommand(imageOperation, splitWidth));
        redoStack.clear();
        isSaved = false;
      } else {
        lastPreviewEnabledOperation = new OperationCommand(imageOperation, splitWidth);
      }
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  @Override
  public boolean applyColorCorrect(boolean isPreview, String splitWidth) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation("color-correct");
      imageOperation.execute(splitWidth);
      if (!isPreview) {
        undoStack.push(new OperationCommand(imageOperation, splitWidth));
        redoStack.clear();
        isSaved = false;
      } else {
        lastPreviewEnabledOperation = new OperationCommand(imageOperation, splitWidth);
      }
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  @Override
  public boolean applyCompress(String compressionRatio) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation("compress");
      imageOperation.execute(compressionRatio);
      undoStack.push(new OperationCommand(imageOperation, compressionRatio));
      redoStack.clear();
      isSaved = false;
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  @Override
  public boolean adjustLevels(boolean isPreview, String... args) {
    try {
      CLIOperation imageOperation = imageOperationFactory
          .createOperation("levels-adjust");
      imageOperation.execute(args);
      if (!isPreview) {
        undoStack.push(new OperationCommand(imageOperation, args));
        redoStack.clear();
        isSaved = false;
      } else {
        lastPreviewEnabledOperation = new OperationCommand(imageOperation, args);
      }
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  @Override
  public void saveImage(String imagePath) {

    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation("save");
      imageOperation.execute(imagePath);
      redoStack.clear();
      isSaved = true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
    }
  }

  @Override
  public void undo() {
    if (!undoStack.isEmpty()) {
      isSaved = false;
      OperationCommand lastOperation = undoStack.pop();
      if (undoStack.isEmpty()) {
        imageEditorView.cleanSlate();
        CLIOperation op = imageOperationFactory.createOperation("clear-library");
        op.execute();
        isLoaded = false;
        isSaved = false;
      }
      for (OperationCommand redoOperation : undoStack) {
        redoOperation.execute();
      }
      redoStack.push(lastOperation);
    }
  }

  @Override
  public void redo() {
    isSaved = false;
    if (!redoStack.isEmpty()) {
      OperationCommand redoOperation = redoStack.pop();
      redoOperation.execute();
      undoStack.push(redoOperation);
    }
  }

  @Override
  public void togglePreview(String splitWidth) {
    if (lastPreviewEnabledOperation != null) {
      for (OperationCommand redoOperation : undoStack) {
        if (redoOperation != null) {
          redoOperation.execute();
        }
      }
      String[] args = lastPreviewEnabledOperation.getArgs();
      args[args.length - 1] = splitWidth;
      lastPreviewEnabledOperation =
          new OperationCommand(lastPreviewEnabledOperation.getOperation(), args);
      lastPreviewEnabledOperation.execute();
    }
  }

  @Override
  public void exitPreviewMode() {
    for (OperationCommand redoOperation : undoStack) {
      redoOperation.execute();
    }
    lastPreviewEnabledOperation = null;
  }

  @Override
  public void applyPreviewChanges() {
    if (lastPreviewEnabledOperation != null) {
      String[] args = lastPreviewEnabledOperation.getArgs();
      args[args.length - 1] = "100";
      lastPreviewEnabledOperation =
          new OperationCommand(lastPreviewEnabledOperation.getOperation(), args);
      undoStack.push(lastPreviewEnabledOperation);
      redoStack.clear();
      lastPreviewEnabledOperation = null;
    }
  }

  @Override
  public boolean isLoadedAndNotSaved() {
    return isLoaded && !isSaved;
  }

  @Override
  public boolean downScale(String width, String height) {
    try {
      CLIOperation imageOperation = imageOperationFactory.createOperation("downscale");
      imageOperation.execute(width, height);
      undoStack.push(new OperationCommand(imageOperation, width, height));
      redoStack.clear();
      isSaved = false;
      return true;
    } catch (IllegalArgumentException exception) {
      imageEditorView.showErrorMessageDialog(exception.getMessage(), ERROR_MESSAGE_TITLE);
      return false;
    }
  }

  /**
   * Inner class representing an operation command.
   */
  class OperationCommand {

    private final CLIOperation operation;
    private final String[] args;

    /**
     * Creates an operation command.
     *
     * @param operation the operation
     * @param args      the corresponding arguments for the operation.
     */
    public OperationCommand(CLIOperation operation, String... args) {
      this.operation = operation;
      this.args = args;
    }

    /**
     * This method executes the operation with the corresponding arguments.
     */
    public void execute() {
      this.operation.execute(args);
    }

    public CLIOperation getOperation() {
      return operation;
    }

    public String[] getArgs() {
      return args;
    }
  }
}
