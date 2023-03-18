package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Typechecker {

    public void typecheck(SymbolTable symbols, Node node) throws SyntaxException {
        switch(node) {
            case Block(ParserRuleContext ctx, List<Statement> statements) -> {
               for(var statement : statements){
                   typecheck(symbols, statement);
               }
            }
            case Declarations(ParserRuleContext ctx, TypeNode type, List<Declaration> decItems) -> {
                for(var decItem : decItems){
                    var variable = symbols.findVariable(decItem.name());
                    if(variable.isPresent()){
                        var realVariable = variable.get();
                        realVariable.setType(type.type());
                    }
                }
            }
            case ExpressionStatement(ParserRuleContext ctx, Expression expr) -> {
                typecheck(symbols, expr);
                System.out.println(getType(symbols, expr));
            }
            case IntLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case DoubleLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case BooleanLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case StringLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {

            }
            case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {

            }
            case BinaryOp(ParserRuleContext ctx1, String operator, Expression left, Expression right) -> {

            }
            case Negate(ParserRuleContext ctx, Expression expression) -> {

            }
            case PreIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {

            }
            case PostIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {

            }
            case Cast(ParserRuleContext ctx, TypeNode type, Expression expression) -> {

            }
            case Print(ParserRuleContext ctx, List<Expression> expressions) -> {

            }
            default -> {
                throw new RuntimeException(String.format("Unimplemented: %s", node.getNodeDescription()));
            }
        }
    }

    public Type getType(SymbolTable symbols, Expression expr) {
        switch(expr) {
            case IntLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case DoubleLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case BooleanLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case StringLiteral(ParserRuleContext ctx2, String text) -> {

            }
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {

            }
            case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {

            }
            case BinaryOp(ParserRuleContext ctx1, String operator, Expression left, Expression right) -> {

            }
            case Negate(ParserRuleContext ctx, Expression expression) -> {

            }
            case PreIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {

            }
            case PostIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {

            }
            case Cast(ParserRuleContext ctx, TypeNode type, Expression expression) -> {

            }
            case Print(ParserRuleContext ctx, List<Expression> expressions) -> {

            }
            default -> {
                throw new RuntimeException(String.format("Unimplemented: %s", expr.getNodeDescription()));
            }
        }
        return null;
    }
}
