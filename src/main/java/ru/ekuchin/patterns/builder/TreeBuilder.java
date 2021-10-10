package ru.ekuchin.patterns.builder;

import java.util.ArrayList;

public class TreeBuilder{
    private ArrayList<Node> tree = new ArrayList<Node>();

    public void addNode(ArrayList<Integer> linkedTo) {
        Node node = new Node(linkedTo);
        tree.add(node);
    }

    public void linkNodes(int from, int to){
        tree.get(from).getLinkedTo().add(to);
        //ArrayList<Integer> links = tree.get(from).getLinkedTo();
        //links.add(to);
        //tree.set(from, new Node(links));
    }

    public Tree serialize(){
        return new Tree(tree);
    }
}

