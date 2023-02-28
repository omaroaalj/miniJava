package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record PreIncrement(Expression expression, String increment) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(expression);
    }

    @Override
    public String getNodeDescription() {
        return String.format("PreIncrement [op: %s]", increment);
    }
}