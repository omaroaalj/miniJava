package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record PostIncrement(Expression expression, String increment) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(expression);
    }
}