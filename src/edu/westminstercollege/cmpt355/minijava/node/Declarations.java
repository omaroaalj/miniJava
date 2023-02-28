package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;
import java.util.ArrayList;

public record Declarations(TypeNode type, List<Declaration> decItems) implements Statement {
    @Override
    public List<? extends Node> children() {
        ArrayList<Node> elements = new ArrayList<>();
        elements.add(type);
        elements.addAll(decItems);
        return elements;
    }

}
