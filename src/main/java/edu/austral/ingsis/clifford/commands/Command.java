package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.VirtualFileSystem;

import java.util.List;

public interface Command {
    String execute(List<String> params, VirtualFileSystem virtualFileSystem);
}
