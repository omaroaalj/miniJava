package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record PostIncrement() implements Expression {
    @Override
    public List<? extends Node> children() {
        return null;
    }
}
