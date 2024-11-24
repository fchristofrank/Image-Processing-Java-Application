package controller;

/**
 * This interface provides all possible commands that the application's controller will later
 * implement.
 */
public interface CustomController {


  /**
   * Main method of the controller that starts the application when the App file is ran. Will not
   * give up control until it is manually released by the user.
   */
  void start();

  /**
   * Ingests the users string input and executes commands as instructed.
   *
   * @param input string input provided by the user
   * @return a status code expressing the success or failure of the parsing process
   */
  int parse(String input);

  /**
   * Command for running a file script.
   *
   * @param filename the location of the script file
   */
  void runScript(String filename);
}
