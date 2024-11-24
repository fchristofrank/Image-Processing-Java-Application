package commands;

import model.CustomModel;

/**
 * The Command interface is the base interface from which all commands should implement. It has a
 * single method go, which is just responsible for running the command.
 */
public interface Command {

  /**
   * The go method is just responsible for running the command, assuming the input is validated.
   *
   * @param model the model on which the command should be operated on.
   */
  public void run(CustomModel model);
}
