package ime.controller.cli;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ime.controller.operation.CLIOperation;
import ime.controller.operation.repository.ImageLibrary;

/**
 * The main command-line interface for the Image Processor application.
 * <p>
 * This class implements the {@link CommandExecutor} interface and provides
 * functionality to run the image processor, accept user input, execute
 * commands, and run script files.
 * </p>
 */
public class ImageProcessorCLI implements CommandExecutor {
  private static final String EXIT_COMMAND = "exit";
  private static final String RUN_COMMAND = "run";
  private static final String COMMENT_PREFIX = "#";
  private static final String WELCOME_MESSAGE = "Welcome to the Image Processor. Type 'exit' " +
          "to quit.";
  private static final String GOODBYE_MESSAGE = "Goodbye - may the gradients be with you!";
  private static final String PROMPT = "> ";
  private static final String EMPTY_COMMAND_MESSAGE = "Please enter a command.";
  private static final String INVALID_RUN_COMMAND = "Invalid 'run' command. Usage: run <filename>";

  private final Readable readable;
  private final Appendable appendable;
  private final OperationCreator imageOperationFactory;

  /**
   * Constructs a new ImageProcessorCLI instance.
   * Initializes the command factory with a new ImageLibrary and sets up
   * the input scanner.
   */
  public ImageProcessorCLI(Readable readable, Appendable appendable,
                           OperationCreator operationCreator) {
    this.readable = readable;
    this.appendable = appendable;
    this.imageOperationFactory = operationCreator;
  }

  @Override
  public void run() {
    try (Scanner scanner = new Scanner(readable)) {
      writeMessage(WELCOME_MESSAGE + System.lineSeparator());
      while (true) {
        writeMessage(PROMPT);
        String input = scanner.nextLine().trim();
        if (EXIT_COMMAND.equals(input)) {
          break;
        }
        if (!input.isEmpty()) {
          try {
            processInput(input);
          } catch (IllegalArgumentException e) {
            writeMessage("Error executing command: " + e.getMessage());
          }
        } else {
          writeMessage(EMPTY_COMMAND_MESSAGE + System.lineSeparator());
        }
      }
      writeMessage(GOODBYE_MESSAGE + System.lineSeparator());
    }
  }

  private void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Processes the user input, determining whether to execute a script file or a single command.
   *
   * @param input the user input string
   */
  private void processInput(String input) {
    if (input.startsWith(RUN_COMMAND)) {
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
      writeMessage(INVALID_RUN_COMMAND + System.lineSeparator());
      return;
    }
    String filename = parts[1];
    try {
      String fileContent = readFromFile(filename);
      Arrays.stream(fileContent.split("\n"))
              .filter(line -> !line.startsWith(COMMENT_PREFIX))
              .forEach(this::executeCommand);
    } catch (IOException e) {
      writeMessage("Error reading file: " + filename);
    }
  }

  /**
   * Executes a single command.
   * This method creates a corresponding operation for the specific command and
   * passes the appropriate arguments.
   *
   * @param command the command string to execute
   */
  private void executeCommand(String command) {
    try {
      String[] parts = command.split("\\s+");
      String operationName = parts[0];
      String[] args = Arrays.copyOfRange(parts, 1, parts.length);
      CLIOperation operation = imageOperationFactory.createOperation(operationName);
      operation.execute(args);
    } catch (Exception e) {
      writeMessage("An error occurred: " + e.getMessage() + System.lineSeparator());
    }
  }

  /**
   * Reads the contents of a file, ignoring comments and empty lines.
   * <p>
   * This method reads from a file specified by the given file path and returns
   * the contents as a string. It ignores lines that are empty or start with '#'
   * (considered as comments).
   * </p>
   *
   * @param filePath the path to the file to be read.
   * @return a string containing the contents of the file, with comments and empty lines removed.
   * @throws IOException if an I/O error occurs while reading the file.
   */
  private String readFromFile(String filePath) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      return lines
              .map(String::trim)
              .filter(line -> !line.isEmpty() && !line.startsWith(COMMENT_PREFIX))
              .collect(Collectors.joining("\n"));
    }
  }
}