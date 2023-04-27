package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record RelationalOp(ParserRuleContext ctx, Expression left, Expression right, String op) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(left, right);
    }
    @Override
    public String getNodeDescription() {
        return String.format("RelationalOp[op=%s]", op);
    }

}
