package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record Assignment(String variableName, Expression expression) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(expression);
    }

    @Override
    public String getNodeDescription() {
        return String.format("Assignment [variableName: %s]", variableName);
    }
}

