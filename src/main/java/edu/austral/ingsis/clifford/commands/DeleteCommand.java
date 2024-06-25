package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.VirtualFileSystem;

import java.util.List;

public class DeleteCommand implements Command {
    @Override
    public String execute(List<String> params, VirtualFileSystem virtualFileSystem) {
        if (params.isEmpty()) return "No path provided";
        if (params.size() == 1) return virtualFileSystem.remove(params.get(0), "");
        if (params.size() == 2) return virtualFileSystem.remove(params.get(1), params.get(0));
        return "Too many arguments";
    }
}
