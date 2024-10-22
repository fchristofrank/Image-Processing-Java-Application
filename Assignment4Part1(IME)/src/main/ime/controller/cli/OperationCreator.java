package ime.controller.cli;

import ime.controller.operation.CLIOperation;

public interface OperationCreator {
  /**
   * Creates and returns a CLIOperation based on the given command name.
   *
   * @param commandName the name of the operation to create
   * @return a CLIOperation instance corresponding to the given command name
   * @throws IllegalArgumentException if the command name is not recognized
   */
  CLIOperation createOperation(String commandName);
}
