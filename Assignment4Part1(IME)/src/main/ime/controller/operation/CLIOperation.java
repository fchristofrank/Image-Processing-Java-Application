package ime.controller.operation;

/** This interface represents basic CLI operations in an image processing application. */
public interface CLIOperation {
  /**
   * This method executes a specific operation with the given arguments.
   *
   * @param args the arguments for an operations.
   * @throws IllegalArgumentException if the operation cannot be performed due to invalid or
   *     insufficient arguments.
   */
  void execute(String[] args) throws IllegalArgumentException;
}
