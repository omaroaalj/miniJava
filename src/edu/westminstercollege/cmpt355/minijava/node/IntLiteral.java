package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record IntLiteral(String text) implements Expression {
    @Override
    public String getNodeDescription() {
        return String.format("IntLiteral [text: %s]", text);
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }
}