package ime.controller.cli;

import ime.controller.operation.CLIOperation;

/**
 * This interface provides a method for creating {@link CLIOperation} instances based
 * on a given command name.
 * This interface follows the factory pattern, allowing the creation of different
 * operations dynamically in a CLI application.
 */
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
