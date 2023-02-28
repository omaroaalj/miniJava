package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record Print(List<Expression> expressions) implements Expression {
    @Override
    public List<? extends Node> children() {
        return expressions;
    }
}
