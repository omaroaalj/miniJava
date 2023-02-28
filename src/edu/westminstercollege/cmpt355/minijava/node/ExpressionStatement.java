package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record ExpressionStatement(Expression expr) implements Statement {
    @Override
    public List<? extends Node> children() {
        return List.of(expr);
    }
}
