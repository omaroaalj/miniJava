package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;

public record Block() implements Statement {
    @Override
    public List<? extends Node> children() {
        return null;
    }
}
