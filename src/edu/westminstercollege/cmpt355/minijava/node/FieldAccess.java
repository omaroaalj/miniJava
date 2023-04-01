package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record FieldAccess(ParserRuleContext ctx, Expression expression, String fieldName) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(expression);
    }

    @Override
    public String getNodeDescription() {
        return String.format("FieldAccess [%s]", fieldName);
    }
}
