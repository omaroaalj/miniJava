package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record PostIncrement(ParserRuleContext ctx, Expression expression, String increment) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(expression);
    }

    @Override
    public String getNodeDescription() {
        return String.format("PostIncrement [op: %s]", increment);
    }
}