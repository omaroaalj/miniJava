package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public sealed interface Node
        permits ClassNode, Declaration, Expression, FieldDefinition, Import, MainMethod, MethodDefinition, Parameter, Statement, TypeNode {

    default String getNodeDescription() {
        String fullName = getClass().getSimpleName();
        int index = fullName.lastIndexOf('.');
        if (index >= 0)
            return fullName.substring(index + 1);
        return fullName;
    }

    ParserRuleContext ctx();

    List<? extends Node> children();
}
