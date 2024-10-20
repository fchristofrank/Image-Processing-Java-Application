package ime.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ime.controller.CLIOperation;
import ime.imageIO.ImageLibrary;

public class ImageProcessorCLI {
  private final CommandFactory commandFactory;
  private final Scanner scanner;

  /**
   * This method creates an image processor cli instance.
   */
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
      if (input.equalsIgnoreCase("exit")) {
        break;
      }
      processCommand(input);
    }
    System.out.println("Goodbye - may the gradients be with you!");
  }

  private void processCommand(String input) {
    String[] parts = input.split("\\s+");
    if (parts.length == 0) {
      return;
    }
    String operationName = parts[0];
    String[] args = Arrays.copyOfRange(parts, 1, parts.length);
    try {
      CLIOperation operation = commandFactory.createCommand(operationName);
      operation.execute(args);
    } catch (IllegalArgumentException | IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
