package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record IfElse(ParserRuleContext ctx, Expression condition, Statement body, Statement elseBody) implements Statement {
    @Override
    public String getNodeDescription() {
        return Statement.super.getNodeDescription();
    }

    @Override
    public List<? extends Node> children() {
        return List.of(condition, body, elseBody);
    }
}
