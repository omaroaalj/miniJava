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
            case ExpressionStatement(ParserRuleContext ctx, Expression expr) -> {
                typecheck(symbols, expr);

                Type exprType = getType(symbols, expr);
                System.out.println("exprType of " + expr + ": " + exprType.toString());
            }
            case IntLiteral(ParserRuleContext ctx, String text) -> {
                try{
                    int value = Integer.parseInt(text);
                } catch (NumberFormatException e){
                    throw new SyntaxException(node, "value must be of type Integer");
                }
            }
            case DoubleLiteral(ParserRuleContext ctx, String text) -> {
                try{
                    double value = Double.parseDouble(text);
                } catch (NumberFormatException e){
                    throw new SyntaxException(node, "value must be of type Double");
                }
            }
            case BooleanLiteral(ParserRuleContext ctx, String text) -> {
                if(!text.equals("true") && !text.equals("false")){
                    throw new SyntaxException(node, "value must be of type Boolean");
                }
            }
            case StringLiteral(ParserRuleContext ctx, String text) -> {
                int text_length = text.length()-1;
                int lastChar = text.charAt(text_length);
                int firstChar = text.charAt(0);
                if (firstChar != '"' || lastChar != '"')
                    throw new SyntaxException(node, "value must start and end with \" ");
            }
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {
                var variable = symbols.findVariable(variableName);
                if(variable.isEmpty()){
                    throw new SyntaxException(node, String.format("Variable %s does not exist", variableName));
                }
            }
            case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {
                typecheck(symbols, exprName);
                typecheck(symbols, expression);
                Type left = getType(symbols, exprName),
                        right = getType(symbols, expression);
                if(left.equals(PrimitiveType.Double) && right.equals(PrimitiveType.Int)){
                    //do nothing
                }
                else if( (left instanceof ClassType && ((ClassType)left).className().equals("String"))
                        && (right instanceof ClassType && ((ClassType)right).className().equals("String"))) {
                    //do nothing
                }
                else if(left != right){
                    throw new SyntaxException(node, String.format("Cannot assign type %s to variable of the type %s", right.toString(), left.toString()));
                }
            }
            case BinaryOp(ParserRuleContext ctx, String operator, Expression left, Expression right) -> {
                typecheck(symbols, left);
                typecheck(symbols, right);

                Type leftType = getType(symbols, left),
                        rightType = getType(symbols, right);
                boolean b = leftType.equals(VoidType.Instance) || rightType.equals(VoidType.Instance);
                boolean b1 = leftType.equals(PrimitiveType.Boolean) || rightType.equals(PrimitiveType.Boolean);
                if(operator.equals("+")){
                    if(b){
                        throw new SyntaxException(node, "Cannot perform addition with a void value.");
                    }
                    //check if left or right are a classType that is not String
                    else if( (leftType instanceof ClassType && !((ClassType) leftType).className().equals("String"))
                            && (rightType instanceof ClassType && !((ClassType) rightType).className().equals("String")) ){
                        throw new SyntaxException(node, "Cannot perform addition with non numerical/non String values.");
                    }
                    else if(leftType.equals(PrimitiveType.Boolean) && rightType.equals(PrimitiveType.Boolean)){
                        throw new SyntaxException(node, "Cannot perform addition with boolean values.");
                    }
                    else if((leftType instanceof ClassType && !((ClassType) leftType).className().equals("String"))
                            || (rightType instanceof ClassType && !((ClassType) rightType).className().equals("String"))){
                        throw new SyntaxException(node, "Cannot perform addition with non numerical values.");
                    }
                    
                }
                else {
                    if(b){
                        throw new SyntaxException(node, "Cannot perform binary operation with a void value.");
                    }
                    //check if left or right are a classType that is not String
                    else if( leftType instanceof ClassType || rightType instanceof ClassType){
                        throw new SyntaxException(node, leftType + " " + operator + " " + rightType + " not possible.");
                    }
                    else if(b1){
                        throw new SyntaxException(node, leftType + " " + operator + " " + rightType + " not possible.");
                    }
                }
            }
            case Negate(ParserRuleContext ctx, Expression expression) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot negate " + type);
                }
            }
            case PreIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot use pre-increment " + type);
                }
            }
            case PostIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot use post-increment " + type);
                }
            }
            case Cast(ParserRuleContext ctx, TypeNode type, Expression expression) -> {
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
            case Print(ParserRuleContext ctx, List<Expression> expressions) -> {
                for(var expr : expressions){
                    typecheck(symbols, expr);
                    if(getType(symbols, expr) instanceof VoidType){
                        throw new SyntaxException(node, "Cannot print a void value.");
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
                        if(rightType instanceof ClassType){
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
            case Negate(ParserRuleContext ctx, Expression expression) -> {
                return getType(symbols, expression);
            }
            case PreIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {
                return getType(symbols, expression);
            }
            case PostIncrement(ParserRuleContext ctx, Expression expression, String increment) -> {
                return getType(symbols, expression);
            }
            case Cast(ParserRuleContext ctx, TypeNode type, Expression expression) -> {
                return type.type();
            }
            case Print(ParserRuleContext ctx, List<Expression> expressions) -> {
                return VoidType.Instance;
            }
            default -> {
                throw new RuntimeException(String.format("Unimplemented: %s", expr.getNodeDescription()));
            }
        }
        return null;
    }
}
