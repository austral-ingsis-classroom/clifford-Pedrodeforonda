package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.VirtualFileSystem;
import java.util.Collections;
import java.util.List;

public class ListCommand implements Command {
  @Override
  public String execute(List<String> params, VirtualFileSystem virtualFileSystem) {
    List<String> children = virtualFileSystem.listChildren();
    if (params.isEmpty()) {
      return String.join(" ", children);
    }
    String order = params.get(0);
    if (order.equals("--ord=asc")) {
      Collections.sort(children);
    } else if (order.equals("--ord=desc")) {
      children.sort(Collections.reverseOrder());
    }
    return String.join(" ", children);
  }
}
