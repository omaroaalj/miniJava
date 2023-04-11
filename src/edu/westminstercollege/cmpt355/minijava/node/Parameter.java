package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record Parameter(ParserRuleContext ctx, TypeNode type, String name) implements Node {

    @Override
    public String getNodeDescription() {
        return name;
    }

    @Override
    public List<? extends Node> children() {
        return List.of(type);
    }
}
