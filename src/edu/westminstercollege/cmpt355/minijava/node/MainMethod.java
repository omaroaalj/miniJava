package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record MainMethod(ParserRuleContext ctx, Block block) implements Node {
    @Override
    public List<? extends Node> children() {
        return List.of(block);
    }

}