package edu.westminstercollege.cmpt355.minijava.node;

import edu.westminstercollege.cmpt355.minijava.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public record Block(ParserRuleContext ctx, List<Statement> statements) implements Statement {
    @Override
    public List<? extends Node> children() {
        return statements;
    }
    public Block(ParserRuleContext ctx, List<Statement> statements){
        this.ctx = ctx;
        this.statements = statements;
        SymbolTable symbols = new SymbolTable(SymbolTable.Level.Block);
    }
}
