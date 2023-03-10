package edu.westminstercollege.cmpt355.minijava.node;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.ArrayList;

public record Declarations(ParserRuleContext ctx, TypeNode type, List<Declaration> decItems) implements Statement {
    @Override
    public List<? extends Node> children() {
        ArrayList<Node> elements = new ArrayList<>();
        elements.add(type);
        elements.addAll(decItems);
        return elements;
    }

}
