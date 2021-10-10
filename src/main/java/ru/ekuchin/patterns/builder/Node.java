package ru.ekuchin.patterns.builder;

import java.util.ArrayList;

public class Node {
    private ArrayList<Integer> linkedTo;

    public Node(ArrayList<Integer> linkedTo) {
        this.linkedTo = linkedTo;
    }

    public ArrayList<Integer> getLinkedTo() {
        return linkedTo;
    }

    @Override
    public String toString() {
        return "Node{" +
                "linkedTo=" + linkedTo +
                '}';
    }
}
