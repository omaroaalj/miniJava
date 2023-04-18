package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;

public record MethodCall(ParserRuleContext ctx, Optional<Expression> expr, String methodName, List<Expression> arguments) implements Expression {
    @Override
    public String getNodeDescription() {
        return String.format("MethodCall [%s]", methodName);
    }

    @Override
    public List<? extends Node> children() {
        return arguments;
    }
}
