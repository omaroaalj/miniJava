package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Typechecker {

    public void typecheck(SymbolTable symbols, Node node) throws SyntaxException {
        switch(node) {
            case Block(ParserRuleContext ctx, List<Statement> statements) -> {
                for (var statement : statements) {
                    typecheck(symbols, statement);
                }
            }
            case Declarations(ParserRuleContext ctx, TypeNode type, List<Declaration> decItems) -> {
                for(var decItem : decItems){
                    var variable = symbols.findVariable(decItem.name());
                    if(variable.isPresent()){
                        var realVar = variable.get();
                        realVar.setType(type.type());
                    }
                }
            }
            case ExpressionStatement(ParserRuleContext ctx, Expression expr) -> {
                typecheck(symbols, expr);

                Type exprType = getType(symbols, expr);
                System.out.println("exprType of " + expr + ": " + exprType.toString());
            }
            case IntLiteral(ParserRuleContext ctx, String text) -> {
                try{
                    int value = Integer.parseInt(text);
                } catch (NumberFormatException e){
                    throw new SyntaxException("value must be of type Integer");
                }
            }
            case DoubleLiteral(ParserRuleContext ctx, String text) -> {
                try{
                    double value = Double.parseDouble(text);
                } catch (NumberFormatException e){
                    throw new SyntaxException("value must be of type Double");
                }
            }
            case BooleanLiteral(ParserRuleContext ctx, String text) -> {
                if(!text.equals("true") && !text.equals("false")){
                    throw new SyntaxException("value must be of type Boolean");
                }
            }
            case StringLiteral(ParserRuleContext ctx, String text) -> {
                int text_length = text.length()-1;
                int lastChar = text.charAt(text_length);
                int firstChar = text.charAt(0);
                if (firstChar != '"' || lastChar != '"')
                    throw new SyntaxException("value must start and end with \" ");
            }
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {
                var variable = symbols.findVariable(variableName);

            }
            case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {
                typecheck(symbols, exprName);
                typecheck(symbols, expression);
                Type left = getType(symbols, exprName),
                        right = getType(symbols, expression);
                if(left != right){
                    throw new SyntaxException(String.format("Cannot assign type %s to variable of the type %s", right.toString(), left.toString()));
                }
            }
            case BinaryOp(ParserRuleContext ctx, String operator, Expression left, Expression right) -> {

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
                for(var expr : expressions){
                    typecheck(symbols, expr);
                    if(getType(symbols, expr) instanceof VoidType){
                        throw new SyntaxException("Cannot print a void value.");
                    }
                }
            }
            default -> {
                throw new RuntimeException(String.format("Unimplemented: %s", node.getNodeDescription()));
            }
        }
    }

    public Type getType(SymbolTable symbols, Expression expr) {
        switch(expr) {
            case IntLiteral(ParserRuleContext ctx, String text) -> {
                return PrimitiveType.Int;
            }
            case DoubleLiteral(ParserRuleContext ctx, String text) -> {
                return PrimitiveType.Double;
            }
            case BooleanLiteral(ParserRuleContext ctx, String text) -> {
                return PrimitiveType.Boolean;
            }
            case StringLiteral(ParserRuleContext ctx, String text) -> {
                return new ClassType("String");
            }
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {
                var variable = symbols.findVariable(variableName);
                if(variable.isPresent()){
                    var realVar = variable.get();
                    return realVar.getType();
                }
            }
            case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {
                return getType(symbols, exprName);
            }
            case BinaryOp(ParserRuleContext ctx, String operator, Expression left, Expression right) -> {

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
