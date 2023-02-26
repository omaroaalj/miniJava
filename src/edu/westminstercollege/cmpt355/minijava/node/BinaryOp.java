package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record BinaryOp(Expression left, Expression right) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(left, right);
    }
}