package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record If(ParserRuleContext ctx, Expression condition, Statement body) implements Statement {
    @Override
    public String getNodeDescription() {
        return Statement.super.getNodeDescription();
    }

    @Override
    public List<? extends Node> children() {
        return List.of(condition, body);
    }
}
