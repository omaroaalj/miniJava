package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record Cast(ParserRuleContext ctx, TypeNode type, Expression expression) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(type, expression);
    }

}
