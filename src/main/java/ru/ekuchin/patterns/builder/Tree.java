package ru.ekuchin.patterns.builder;

import java.util.ArrayList;

public class Tree {
    @Override
    public String toString() {
        return "Tree{" +
                "tree=" + tree +
                '}';
    }

    private final ArrayList<Node> tree;

    public ArrayList<Node> getTree() {
        return tree;
    }

    public Tree(ArrayList<Node> tree) {
        this.tree = tree;
    }
}
