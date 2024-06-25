package edu.austral.ingsis.clifford.fileSystem;

public class File implements Node {
  private final String name;

  public File(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
