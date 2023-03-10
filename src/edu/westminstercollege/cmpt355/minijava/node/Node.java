package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public sealed interface Node
        permits Declaration, Expression, Statement, TypeNode {

    default String getNodeDescription() {
        String fullName = getClass().getSimpleName();
        int index = fullName.lastIndexOf('.');
        if (index >= 0)
            return fullName.substring(index + 1);
        return fullName;
    }

    default ParserRuleContext ctx(){
        var context = getClass().getTypeParameters();
        return (ParserRuleContext)context[1];
    }

    List<? extends Node> children();
}
