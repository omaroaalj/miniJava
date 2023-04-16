package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record PackageImport(ParserRuleContext ctx, List<String> importParts) implements Import {
    @Override
    public ParserRuleContext ctx() {
        return ctx;
    }

    public String getNodeDescription() {
        String importString = importParts.toString();
        return String.format("PackageImport %s", importString);
    }

    @Override
    public List<? extends Node> children() {
        return List.of();
    }
}
