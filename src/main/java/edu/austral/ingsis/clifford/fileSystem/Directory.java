package edu.austral.ingsis.clifford.fileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory implements Node {
    private final String name;
    private final Directory parent;
    private final ArrayList<Node> children;

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public String addChild(Node node) {
        List<Node> fileChildren = children.stream().filter(child -> child instanceof File).toList();
        List<Node> directoryChildren = children.stream().filter(child -> child instanceof Directory).toList();

        if (node instanceof File) {
            for (Node child: fileChildren) {
                if (child.getName().equals(node.getName())) {
                    return "'" + node.getName() + "'" + " already exists";
                }
            }
            children.add(node);
            return "'" + node.getName() + "'" + " file created";
        }
        for (Node child: directoryChildren) {
            if (child.getName().equals(node.getName())) {
                return "'" + node.getName() + "'" + " already exists";
            }
        }
        children.add(node);
        return "'" + node.getName() + "'" + " directory created";
    }

    public String removeChild(String name, String option) {
        List<Node> fileChildren = children.stream().filter(child -> child instanceof File).toList();
        List<Node> directoryChildren = children.stream().filter(child -> child instanceof Directory).toList();
        Node node = getChild(name);

        if (Objects.equals(option, "")) {
            if (node instanceof Directory) {
                return "Use --recursive to remove a directory";
            }
            for (Node child: fileChildren) {
                if (child.getName().equals(node.getName())) {
                    children.remove(node);
                    return "'" + name + "'" + " removed";
                }
            }
        } else if (Objects.equals(option, "--recursive")) {
            if (node instanceof File) {
                return "Don´t use --recursive to remove a file";
            }
            for (Node child: directoryChildren) {
                if (child.getName().equals(node.getName())) {
                    children.remove(node);
                    return "'" + name + "'" + " removed";
                }
            }
        }
        return "invalid option";
    }

    public Node getChild(String name) {
        for (Node component : children) {
            if (component.getName().equals(name)) {
                return component;
            }
        }
        return null;
    }

    public ArrayList<String> getChildren() {
        ArrayList<String> childrenNames = new ArrayList<>();
        for (Node node : children) {
            childrenNames.add(node.getName());
        }
        return childrenNames;
    }
}
