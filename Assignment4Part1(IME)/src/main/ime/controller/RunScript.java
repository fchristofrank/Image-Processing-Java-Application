package ime.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ime.imageIO.ImageLibrary;

public class RunScript extends AbstractOperation {
  public RunScript(ImageLibrary library) {
    super(library);
  }

  @Override
  public void execute(String[] args) throws IOException {
    StringBuilder result = new StringBuilder();
    BufferedReader reader = new BufferedReader(new FileReader(args[0]));
    String line;
    while ((line = reader.readLine()) != null) {
      result.append(line.trim()).append("\n");
    }
    reader.close();

  }

  @Override
  protected void validateArgs(String[] args) {
    if (args.length != 1) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }
}
