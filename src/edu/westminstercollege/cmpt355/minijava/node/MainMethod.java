package edu.westminstercollege.cmpt355.minijava.node;
import edu.westminstercollege.cmpt355.minijava.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.List;

public record MainMethod(ParserRuleContext ctx, Block block) implements Node {
    @Override
    public List<? extends Node> children() {
        return List.of(block);
    }
    public MainMethod(ParserRuleContext ctx, Block block){
        this.ctx = ctx;
        this.block = block;
        SymbolTable symbols = new SymbolTable(SymbolTable.Level.Method);
    }

}