package ime.controller;

import java.io.IOException;

public interface CLIOperation {
  void execute(String[] args) throws IOException;
}
