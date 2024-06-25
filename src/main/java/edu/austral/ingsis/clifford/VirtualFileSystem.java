package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.fileSystem.Directory;
import edu.austral.ingsis.clifford.fileSystem.File;

import java.util.ArrayList;
import java.util.Objects;

public class VirtualFileSystem {
    private Directory root;

    public VirtualFileSystem() {
        root = new Directory("/", null);
    }

    public Directory getRoot() {
        return root;
    }

    public String createFile(String name) {
        File file = new File(name);
        return root.addChild(file);
    }

    public String createDirectory(String name, Directory parent) {
        Directory directory = new Directory(name, parent);
        return root.addChild(directory);
    }

    public String remove(String name, String option) {
        return root.removeChild(name, option);
    }

    public ArrayList<String> listChildren() {
        return root.getChildren();
    }

    public String moveTo(String path) {
        if (path.equals("..")) {
            if (Objects.equals(root.getName(), "/")) {
                return "moved to directory '/'";
            }
            if (root != null && root.getParent() != null) {
                root = root.getParent();
                return "moved to directory " + "'" + root.getName() + "'";
            }
            return "cannot move to parent directory";
        }
        String[] directories = path.split("/");
        for (String directory : directories) {
            if (root == null) {
                return "directory not found";
            }
            if (root.getChild(directory) == null) {
                return "'" + directories[directories.length - 1] + "'" + " directory does not exist";
            }
            root = (Directory) root.getChild(directory);
        }
        return "moved to directory " + "'" + directories[directories.length - 1] + "'";
    }

    public String getActualPath() {
        ArrayList<String> directories = new ArrayList<>();
        Directory actualRoot = root;
        directories.add(actualRoot.getName());
        while (actualRoot.getParent() != null) {
            actualRoot = actualRoot.getParent();
            if (!Objects.equals(actualRoot.getName(), "/")) {
                directories.add(actualRoot.getName());
            }
        }
        ArrayList<String> reversedDirectories = new ArrayList<>();
        for (int i = directories.size() - 1; i >= 0; i--) {
            reversedDirectories.add(directories.get(i));
        }
        return "/" + String.join("/", reversedDirectories);
    }
}
