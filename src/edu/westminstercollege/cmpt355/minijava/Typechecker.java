package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Typechecker {

    public void typecheck(SymbolTable symbols, Node node) throws SyntaxException {
        switch(node) {
            case Block(ParserRuleContext ignored, List<Statement> statements) -> {
                for (var statement : statements) {
                    typecheck(symbols, statement);
                }
            }
            case Declarations(ParserRuleContext ignored, TypeNode type, List<Declaration> decItems) -> {
                // loop through each declaration, sets type if necessary
                for(var decItem : decItems){
                    var variable = symbols.findVariable(decItem.name());
                    if(variable.isPresent()){
                        var realVar = variable.get();
                        realVar.setType(type.type());
                    }
                    // check if declaration has children, then check if declared double, can have double or int values
                    if(decItem.children().size() > 0 ) {
                        if(type.type().equals(PrimitiveType.Double)){
                            if(!getType(symbols, decItem.expression().get()).equals(PrimitiveType.Double)
                                    && !getType(symbols, decItem.expression().get()).equals(PrimitiveType.Int)){
                                throw new SyntaxException(node, String.format("Initialization must match variable's declared type: variable %s does not conform to type %s", decItem.name(), getType(symbols, decItem.expression().get()).toString()));
                            }
                        }
                        // else make sure the declared type matches given value
                        else if(!getType(symbols, decItem.expression().get()).equals(type.type())){
                            throw new SyntaxException(node, String.format("Initialization must match variable's declared type: variable %s does not conform to type %s", decItem.name(), getType(symbols, decItem.expression().get()).toString()));
                        }
                    }
                }
            }
            case ExpressionStatement(ParserRuleContext ignored, Expression expr) -> {
                typecheck(symbols, expr);

                Type exprType = getType(symbols, expr);
                System.out.println("exprType of " + expr + ": " + exprType.toString());
            }
            case VariableAccess(ParserRuleContext ignored, String variableName) -> {
                var variable = symbols.findVariable(variableName);
                if(variable.isEmpty()){
                    throw new SyntaxException(node, String.format("Variable %s does not exist", variableName));
                }
            }
            case Assignment(ParserRuleContext ignored, Expression exprName, Expression expression) -> {
                typecheck(symbols, exprName);
                typecheck(symbols, expression);
                Type leftType = getType(symbols, exprName),
                        rightType = getType(symbols, expression);
                
                if (!leftType.equals(rightType)
                        && (!leftType.equals(PrimitiveType.Double) || !rightType.equals(PrimitiveType.Int))) {
                    throw new SyntaxException(node, String.format("Cannot assign type %s to variable of the type %s", rightType, leftType));
                }
            }
            case BinaryOp(ParserRuleContext ignored, String operator, Expression left, Expression right) -> {
                typecheck(symbols, left);
                typecheck(symbols, right);

                Type leftType = getType(symbols, left),
                        rightType = getType(symbols, right);
                boolean voidIsPresent = leftType.equals(VoidType.Instance) || rightType.equals(VoidType.Instance);
                var stringType = new ClassType("String");
                boolean noStringsPresent = !leftType.equals(stringType) && !rightType.equals(stringType);
                boolean nonNumericPresent =
                        (!leftType.equals(PrimitiveType.Int) && !leftType.equals(PrimitiveType.Double))
                                || (!rightType.equals(PrimitiveType.Int) && !rightType.equals(PrimitiveType.Double));
                if(operator.equals("+")){
                    if(voidIsPresent) {
                        throw new SyntaxException(node, "Cannot perform addition with a void value.");
                    } else if (nonNumericPresent && noStringsPresent) {
                        throw new SyntaxException(node, "Cannot perform addition with non numerical/non String values.");
                    }
                }
                else {
                    if(voidIsPresent) {
                        throw new SyntaxException(node, "Cannot perform binary operation with a void value.");
                    } else if (nonNumericPresent) {
                        throw new SyntaxException(node, String.format("%s %s %s not possible.", leftType, operator, rightType));
                    }
                }
            }
            case Negate(ParserRuleContext ignored, Expression expression) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot negate " + type);
                }
            }
            case PreIncrement(ParserRuleContext ignored, Expression expression, String ignored2) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot use pre-increment " + type);
                }
            }
            case PostIncrement(ParserRuleContext ignored, Expression expression, String ignored2) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot use post-increment " + type);
                }
            }
            case Cast(ParserRuleContext ignored, TypeNode type, Expression expression) -> {
                var castType = type.type();
                var exprType = getType(symbols, expression);
                var stringType = new ClassType("String");
                if (exprType.equals(VoidType.Instance)) {
                    throw new SyntaxException(node, "Cannot use cast on a void value.");
                } else if (!castType.equals(stringType) && !(castType instanceof PrimitiveType)) {
                    throw new SyntaxException(node, castType + " not a valid cast type.");
                } else if ((castType instanceof PrimitiveType) && exprType.equals(stringType)) {
                    throw new SyntaxException(node, "Cannot cast String to " + castType);
                } else if ((castType.equals(PrimitiveType.Int) || castType.equals(PrimitiveType.Double))
                        && exprType.equals(PrimitiveType.Boolean)) {
                    throw new SyntaxException(node, "Cannot cast " + exprType + " to " + castType);
                }
            }
            case Print(ParserRuleContext ignored, List<Expression> expressions) -> {
                for(var expr : expressions){
                    typecheck(symbols, expr);
                    if(getType(symbols, expr) instanceof VoidType){
                        throw new SyntaxException(node, "Cannot print a void value.");
                    }
                }
            }
            default -> {}
        }
    }

    public Type getType(SymbolTable symbols, Expression expr) {
        switch(expr) {
            case IntLiteral(ParserRuleContext ignored, String ignored2) -> {
                return PrimitiveType.Int;
            }
            case DoubleLiteral(ParserRuleContext ignored, String ignored2) -> {
                return PrimitiveType.Double;
            }
            case BooleanLiteral(ParserRuleContext ignored, String ignored2) -> {
                return PrimitiveType.Boolean;
            }
            case StringLiteral(ParserRuleContext ignored, String ignored2) -> {
                return new ClassType("String");
            }
            case VariableAccess(ParserRuleContext ignored, String variableName) -> {
                var variable = symbols.findVariable(variableName);
                if(variable.isPresent()){
                    var realVar = variable.get();
                    return realVar.getType();
                }
            }
            case Assignment(ParserRuleContext ignored, Expression exprName, Expression ignored2) -> {
                return getType(symbols, exprName);
            }
            case BinaryOp(ParserRuleContext ignored, String operator, Expression left, Expression right) -> {
                Type leftType = getType(symbols, left),
                        rightType = getType(symbols, right);
                // cases if operator is +
                boolean b = (leftType.equals(PrimitiveType.Int) || leftType.equals(PrimitiveType.Double))
                        && (rightType.equals(PrimitiveType.Int) || rightType.equals(PrimitiveType.Double));
                boolean b1 = leftType.equals(PrimitiveType.Double) || rightType.equals(PrimitiveType.Double);
                if(operator.equals("+")){
                    // case if int, double or string
                    if( (leftType.equals(PrimitiveType.Double) || leftType.equals(PrimitiveType.Int) || (leftType instanceof ClassType &&
                                ((ClassType) leftType).className().equals("String"))) &&
                            (rightType.equals(PrimitiveType.Double) || rightType.equals(PrimitiveType.Int) || (rightType instanceof ClassType &&
                                    ((ClassType) rightType).className().equals("String")))){
                        // case one is String
                        if( (leftType instanceof ClassType && ((ClassType) leftType).className().equals("String")) ||
                                (rightType instanceof ClassType && ((ClassType) rightType).className().equals("String"))) {
                            return new ClassType("String");
                        }
                        // case both or int/double
                        else if(b){
                            // case one is double
                            if(b1){
                                return PrimitiveType.Double;
                            }
                            else {
                                return PrimitiveType.Int;
                            }
                        }
                    }
                    // case either is void, just return void because invalid operation
                    else if(leftType.equals(VoidType.Instance) || rightType.equals(VoidType.Instance)){
                        return VoidType.Instance;
                    }
                    // case either is a classType not String, invalid op, return either
                    else if(leftType instanceof ClassType || rightType instanceof ClassType){
                        if(rightType instanceof ClassType && ((ClassType)rightType).className().equals("String")){
                            return rightType;
                        }
                        else {
                            return leftType;
                        }
                    }
                }
                // case for all other operators
                else {
                    // case if left/right int or double
                    if (b) {
                        // case one is double
                        if (b1) {
                            return PrimitiveType.Double;
                        } else {
                            return PrimitiveType.Int;
                        }
                    }
                    // case if either is a classType, including String
                    else if(leftType instanceof ClassType || rightType instanceof ClassType){
                        if(rightType instanceof ClassType){
                            return rightType;
                        }
                        else {
                            return leftType;
                        }
                    }
                }
            }
            case Negate(ParserRuleContext ignored, Expression expression) -> {
                return getType(symbols, expression);
            }
            case PreIncrement(ParserRuleContext ignored, Expression expression, String ignored2) -> {
                return getType(symbols, expression);
            }
            case PostIncrement(ParserRuleContext ignored, Expression expression, String ignored2) -> {
                return getType(symbols, expression);
            }
            case Cast(ParserRuleContext ignored, TypeNode type, Expression ignored2) -> {
                return type.type();
            }
            case Print(ParserRuleContext ignored, List<Expression> ignored2) -> {
                return VoidType.Instance;
            }
        }
        return null;
    }
}
