package edu.westminstercollege.cmpt355.minijava.node;

import java.util.List;
import org.antlr.v4.runtime.* ;

public record Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) implements Expression {
    @Override
    public List<? extends Node> children() {
        return List.of(exprName, expression);
    }

    //@Override
    /*public String getNodeDescription() {
        return String.format("Assignment [variableName: %s]", variableName);
    }
     */
}

