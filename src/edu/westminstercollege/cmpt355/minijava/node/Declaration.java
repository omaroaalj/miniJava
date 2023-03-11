package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;

public record Declaration(ParserRuleContext ctx, String name, Optional<Expression> expression) implements Node {

    @Override
    public String getNodeDescription() {
            return String.format("Declaration [name: %s]", name);
    }

    @Override
    public List<? extends Node> children() {
        if(expression.isPresent()){
            return List.of(expression.get());
        }
        return List.of();
    }
}
