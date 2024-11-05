import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import ime.controller.operation.CommandExecutor;
import ime.controller.operation.ImageOperationFactory;
import ime.controller.cli.ImageProcessorCLI;
import ime.controller.cli.OperationCreator;
import ime.controller.operation.AbstractOperation;
import ime.controller.operation.CLIOperation;
import ime.controller.operation.repository.ImageLibrary;
import ime.controller.operation.repository.ImageRepo;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionalities of the image processor CLI's controller class.
 */
public class ControllerTest {

  @Test
  public void testExitCommand() {
    String input = "exit\n";
    Readable readableInput = new StringReader(input);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream), new ImageOperationFactory());
    commandExecutor.run();
    String[] output = outputStream.toString().split("\n");
    assertEquals("> Goodbye - may the gradients be with you!\r", output[output.length - 1]);
  }

  @Test
  public void testLoadCommand() {
    String input = "load images/koala.ppm koala\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testSaveCommand() {
    String input = "save images/koala-gs.ppm koala-greyscale\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testBrightenCommand() {
    String input = "brighten 10 koala koala-brighter\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testVerticalFlipCommand() {
    String input = "vertical-flip koala koala-vertical\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testHorizontalFlipCommand() {
    String input = "horizontal-flip koala-vertical koala-vertical-horizontal\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testValueComponentCommand() {
    String input = "value-component koala koala-greyscale\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRGBCombineCommand() {
    String input = "rgb-combine koala-red-tint koala-red koala-green koala-blue\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testRGBSplitCommand() {
    String input = "rgb-split koala koala-red koala-green koala-blue\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  @Test
  public void testCompressCommand(){
    String input = "compress 50 koala koala-compress\nexit\n";
    Readable readableInput = new StringReader(input);
    StringBuilder logger = new StringBuilder();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    CommandExecutor commandExecutor = new ImageProcessorCLI(readableInput,
            new PrintStream(outputStream),
            new MockCommandFactory(new ImageLibrary(), logger));
    commandExecutor.run();
    String actualInput = logger.toString();
    assertEquals(input, actualInput);
  }

  class MockOperation extends AbstractOperation {

    private final StringBuilder logger;

    /**
     * Constructs an AbstractOperation with the specified image library.
     *
     * @param library the ImageLibrary to be used for image operations
     */
    public MockOperation(ImageRepo library, StringBuilder logger) {
      super(library);
      this.logger = logger;
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
      logger.append(String.join(" ", args));
      logger.append("\n").append("exit").append("\n");
    }
  }

  class MockCommandFactory implements OperationCreator {

    private final ImageRepo library;
    private final StringBuilder logger;

    public MockCommandFactory(ImageRepo library, StringBuilder logger) {
      this.library = library;
      this.logger = logger;
    }

    @Override
    public CLIOperation createOperation(String commandName) {
      logger.append(commandName).append(" ");
      return new MockOperation(library, logger);
    }
  }
}
