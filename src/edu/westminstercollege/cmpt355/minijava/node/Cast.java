package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record Cast(TypeNode type, Expression expression) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(type, expression);
    }

}
