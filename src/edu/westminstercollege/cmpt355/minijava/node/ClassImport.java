package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record ClassImport(ParserRuleContext ctx, List<String> importParts) implements Import {
    @Override
    public ParserRuleContext ctx() {
        return ctx;
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }
}
