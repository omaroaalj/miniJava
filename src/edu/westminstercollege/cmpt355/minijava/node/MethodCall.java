package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record MethodCall(ParserRuleContext ctx, Expression expr, String methodName, List<Expression> arguments) implements Expression {
    @Override
    public String getNodeDescription() {
        StringBuilder args = new StringBuilder();
        for(var arg : arguments)
            args.append(", ").append(arg.toString());

        return String.format("MethodCall [%s(%s)]", methodName, args);
    }

    @Override
    public List<? extends Node> children() {
        return arguments;
    }
}
