package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record ClassNode(ParserRuleContext ctx, List<MethodDefinition> methods) implements Node {
    @Override
    public List<? extends Node> children() {
        return methods;
    }

}
