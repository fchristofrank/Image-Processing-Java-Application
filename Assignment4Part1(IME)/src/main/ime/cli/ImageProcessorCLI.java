package ime.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ime.controller.CLIOperation;
import ime.imageIO.ImageLibrary;
import ime.util.FileReader;

public class ImageProcessorCLI implements CommandExecutor {
  private final CommandFactory commandFactory;
  private final Scanner scanner;

  public ImageProcessorCLI() {
    this.commandFactory = new CommandFactory(new ImageLibrary());
    this.scanner = new Scanner(System.in);
  }

  public static void main(String[] args) {
    new ImageProcessorCLI().run();
  }

  public void run() {
    System.out.println("Welcome to the Image Processor. Type 'exit' to quit.");
    while (true) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();
      if (input.equals("exit")) {
        break;
      }
      processInput(input);
    }
    System.out.println("Goodbye - may the gradients be with you!");
  }

  private void processInput(String input) {
    if (input.startsWith("run")) {
      executeScriptFile(input);
    } else {
      executeCommand(input);
    }
  }

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
        executeCommand(line);
      }
    }
  }

  @Override
  public void executeCommand(String command) {
    String[] parts = command.split("\\s+");
    if (parts.length == 0) {
      return;
    }
    String operationName = parts[0];
    String[] args = Arrays.copyOfRange(parts, 1, parts.length);
    try {
      CLIOperation operation = commandFactory.createCommand(operationName);
      operation.execute(args);
    } catch (IllegalArgumentException | IOException e) {
      System.out.println("Error executing command: " + e.getMessage());
    }
  }
}