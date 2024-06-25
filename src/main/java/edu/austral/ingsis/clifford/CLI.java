package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.Command;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CLI {
  private final VirtualFileSystem virtualFileSystem;
  private final CommandParser parser;

  public CLI() {
    this.virtualFileSystem = new VirtualFileSystem();
    this.parser = new CommandParser();
  }

  public String executeCommand(String input) {
    String[] args = input.split(" ");
    Command command = parser.parse(args[0]);
    String[] params = Arrays.copyOfRange(args, 1, args.length);
    return command.execute(Arrays.stream(params).collect(Collectors.toList()), virtualFileSystem);
  }
}
