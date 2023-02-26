package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;
import java.util.Optional;

public record Declaration(String name, Optional<Expression> expression) implements Node {

    @Override
    public String getNodeDescription() {
            return String.format("[name: %s]", name);
    }

    @Override
    public List<? extends Node> children() {
        if(expression.isPresent()){
            return List.of(expression.get());
        }
        else {
            return List.of();
        }
    }
}
