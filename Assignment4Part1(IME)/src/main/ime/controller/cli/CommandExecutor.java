package ime.controller.cli;

/**
 * The CommandExecutor interface defines a contract for classes that can execute
 * commands in a command-line environment, specifically for an image processing
 * application.
 */
public interface CommandExecutor {
  /**
   * This method runs the command-line interface (CLI) loop for the image processing application.
   * It continuously waits for user input, processes commands, and performs operations specific
   * to the commands provided by the user.
   */
  void run();
}
