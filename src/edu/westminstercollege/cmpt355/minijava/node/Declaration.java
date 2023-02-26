package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;
import java.util.Optional;

public record Declaration(String name, Optional<Expression> expression) implements Node {

    @Override
    public String getNodeDescription() {
            return String.format("Declaration [name: %s]", name);
    }

    @Override
    public List<? extends Node> children() {
        return List.of(expression.get());
    }
}
