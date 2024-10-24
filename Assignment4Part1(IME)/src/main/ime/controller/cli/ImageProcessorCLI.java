package ime.controller.cli;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import ime.controller.operation.CLIOperation;
import ime.model.image.ImageLibrary;
import ime.util.FileReader;

/**
 * The main command-line interface for the Image Processor application.
 * <p>
 * This class implements the {@link CommandExecutor} interface and provides
 * functionality to run the image processor, accept user input, execute
 * commands, and run script files.
 * </p>
 */
public class ImageProcessorCLI implements CommandExecutor {
  private final InputStream in;
  private final ImageOperationFactory imageOperationFactory;

  /**
   * Constructs a new ImageProcessorCLI instance.
   * Initializes the command factory with a new ImageLibrary and sets up
   * the input scanner.
   */
  public ImageProcessorCLI(InputStream in) {
    this.in = in;
    this.imageOperationFactory = new ImageOperationFactory(new ImageLibrary());
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(in);
    System.out.println("Welcome to the Image Processor. Type 'exit' to quit.");
    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();
      if (input.equals("exit")) {
        break;
      }
      if (!input.isEmpty()) {
        processInput(input);
      }else{
        System.out.println("Please enter a command.");
      }
    }
    System.out.println("Goodbye - may the gradients be with you!");
  }


  /**
   * Processes the user input, determining whether to execute a script file or a single command.
   *
   * @param input the user input string
   */
  private void processInput(String input) {
    if (input.startsWith("run")) {
      executeScriptFile(input);
    } else {
      executeCommand(input);
    }
  }

  /**
   * Executes a script file containing multiple commands.
   *
   * @param input the 'run' command with the filename
   */
  private void executeScriptFile(String input) {
    String[] parts = input.split("\\s+");
    if (parts.length != 2) {
      System.out.println("Invalid 'run' command. Usage: run <filename>");
      return;
    }
    String filename = parts[1];
    String fileContent = null;
    try {
      fileContent = FileReader.readFromFile(filename);
    } catch (IOException e) {
      System.out.println("Error reading file: " + filename);
    }
    if (fileContent != null) {
      for (String line : fileContent.split("\n")) {
        if (line.startsWith("#")){
          continue;
        }
        executeCommand(line);
      }
    }
  }

  /**
   * Executes a single command.
   * This method creates a corresponding operation for the specific command and
   * pass the appropriate arguments.
   *
   * @param command the command string to execute
   */
  private void executeCommand(String command) {
    String[] parts = command.split("\\s+");
    if (parts.length == 0) {
      return;
    }
    String operationName = parts[0];
    String[] args = Arrays.copyOfRange(parts, 1, parts.length);
    try {
      CLIOperation operation = imageOperationFactory.createOperation(operationName);
      operation.execute(args);
    } catch (IllegalArgumentException e) {
      System.out.println("Error executing command: " + e.getMessage());
    }
  }

}