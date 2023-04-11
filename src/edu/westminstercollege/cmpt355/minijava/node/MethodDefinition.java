package edu.westminstercollege.cmpt355.minijava.node;
import edu.westminstercollege.cmpt355.minijava.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.ArrayList;
import java.util.List;

public record MethodDefinition(ParserRuleContext ctx, TypeNode returnType, String name, List<Parameter> parameters, Block block) implements Node {
    @Override
    public String getNodeDescription() {
        return "name";
    }
    @Override
    public List<? extends Node> children() {
        List<Node> list = new ArrayList<>();
        list.add(returnType);
        list.addAll(parameters);
        list.add(block);
        return list;
    }
    public MethodDefinition(ParserRuleContext ctx, TypeNode returnType, String name, List<Parameter> parameters, Block block){
        this.ctx = ctx;
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        SymbolTable symbols = new SymbolTable(SymbolTable.Level.Method);
    }

}