package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;
import java.util.Optional;

public record DecItem(String name, Optional<Expression> expr) implements Node {
    @Override
    public List<? extends Node> children() {
        return List.of(Optional.get(expr));
    }

    public String getNodeDescription(){
        return String.format("[name: %s]", name);
    }

}
