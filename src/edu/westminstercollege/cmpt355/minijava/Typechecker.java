package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Typechecker {

    public void typecheck(SymbolTable symbols, Node node) throws SyntaxException {
        switch(node) {
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
    }
}
