package edu.westminstercollege.cmpt355.minijava.node;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;
import java.util.Optional;

public record FieldDefinition(ParserRuleContext ctx, TypeNode type, String name, Optional<Expression> expr) implements Node {
    @Override
    public List<? extends Node> children() {
        if(expr.isPresent())
            return List.of(expr.get());
        else
            return List.of();
    }
}
