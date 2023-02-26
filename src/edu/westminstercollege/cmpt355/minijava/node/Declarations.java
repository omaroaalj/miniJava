package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record Declarations(TypeNode type, List<Declaration> decItems) implements Statement, Node {
    @Override
    public List<? extends Node> children() {
        return decItems;
    }

}
