package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record MethodCall(ParserRuleContext ctx, Expression expr, String methodName, List<String> arguments) implements Expression {
    @Override
    public String getNodeDescription() {
        String args = arguments.toString();
        return String.format("MethodCall [%s(%s)]", methodName, args);
    }

    @Override
    public List<? extends Node> children() {
        return List.of(expr);
    }
}
