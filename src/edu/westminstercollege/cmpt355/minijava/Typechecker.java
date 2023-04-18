package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Typechecker {

    public void typecheck(SymbolTable symbols, Node node) throws SyntaxException {
        switch(node) {
            case ClassNode(ParserRuleContext ignored, List<Node> elements) -> {
                for(var element : elements){
                    typecheck(symbols, element);
                }
            }
            case ClassImport(ParserRuleContext ignored, List<String> importParts) -> {
                String path = "";
                for (int i = 0; i < importParts.size(); i++) {
                    path = path.concat(importParts.get(i));
                    if (i < importParts.size() - 1) {
                        path = path.concat(".");
                    }
                }
                var clazz = symbols.findJavaClass(path);
                if(clazz.isPresent()) {
                    symbols.importClass(clazz.get());
                } else {
                    throw new SyntaxException(String.format("Class Import %s does not exist.", path));
                }
            }
            case PackageImport(ParserRuleContext ignored, List<String> importParts) -> {
                String path = "";
                for (int i = 0; i < importParts.size(); i++) {
                    path = path.concat(importParts.get(i));
                    if (i < importParts.size() - 1) {
                        path = path.concat(".");
                    }
                }
                path = path.concat("*");
                // make sure it is a valid package?
                symbols.importPackage(path);
            }
            case Block(ParserRuleContext ignored, List<Statement> statements, SymbolTable symbolses) -> {
                for (var statement : statements) {
                    typecheck(symbolses, statement);
                }
            }
            case FieldDefinition(ParserRuleContext ignored, TypeNode type, String name, Optional<Expression> expr) -> {
                var variable = symbols.findVariable(name);
                if(variable.isPresent()){
                    var realVar = variable.get();
                    realVar.setType(type.type());
                    if(type.type().equals(PrimitiveType.Double)){
                        realVar.setIndex(symbols.getVariableCount());
                        symbols.allocateVariable(2);
                    }
                    else {
                        realVar.setIndex(symbols.getVariableCount());
                        symbols.allocateVariable(1);
                    }
                }
                if(expr.isPresent()){
                    if(type.type().equals(PrimitiveType.Double)){
                        if(!getType(symbols, expr.get()).equals(PrimitiveType.Double)
                                && !getType(symbols, expr.get()).equals(PrimitiveType.Int)){
                            throw new SyntaxException(node, String.format("Field definition must match field's declared type: field %s does not conform to type %s", name, getType(symbols, expr.get()).toString()));
                        }
                    }
                    else if(!getType(symbols, expr.get()).equals(type.type())) {
                        if(getType(symbols, expr.get()) instanceof ClassType && type.type() instanceof ClassType){
                        }
                        else {
                            throw new SyntaxException(node, String.format("Field definition must match field's declared type: field %s does not conform to type %s", name, getType(symbols, expr.get()).toString()));
                        }
                    }
                }
            }
            case Declarations(ParserRuleContext ignored, TypeNode type, List<Declaration> decItems) -> {
                // loop through each declaration, sets type if necessary
                for(var decItem : decItems){
                    var variable = symbols.findVariable(decItem.name());
                    if(variable.isPresent()){
                        var realVar = variable.get();
                        realVar.setType(type.type());
                        if(type.type().equals(PrimitiveType.Double)){
                            realVar.setIndex(symbols.getVariableCount());
                            symbols.allocateVariable(2);
                        }
                        else {
                            realVar.setIndex(symbols.getVariableCount());
                            symbols.allocateVariable(1);
                        }
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
                            if(getType(symbols, decItem.expression().get()) instanceof ClassType && type.type() instanceof ClassType){
                                // do nothing for now
                            } else {
                                throw new SyntaxException(node, String.format("Initialization must match variable's declared type: variable %s does not conform to type %s", decItem.name(), getType(symbols, decItem.expression().get()).toString()));
                            }
                        }
                    }
                }
            }
            case ExpressionStatement(ParserRuleContext ignored, Expression expr) -> {
                typecheck(symbols, expr);
                //Type exprType = getType(symbols, expr);
                //System.out.println("exprType of " + expr + ": " + exprType.toString());
            }
            case VariableAccess(ParserRuleContext ignored, String variableName) -> {
                var variable = symbols.findVariable(variableName);
                if(variable.isEmpty()){
                    var clazz = symbols.findJavaClass(variableName);
                    if(clazz.isEmpty())
                        throw new SyntaxException(node, String.format("Variable %s does not exist", variableName));
                }
            }
            case FieldAccess(ParserRuleContext ignored, Expression expression, String fieldName) -> {
                typecheck(symbols, expression);
                var classType = getType(symbols, expression);
                var field = symbols.findField((ClassType) classType, fieldName);
                if(field.isEmpty()){
                    throw new SyntaxException(node, String.format("Field %s does not exist", fieldName));
                }
            }
            case MethodCall(ParserRuleContext ignored, Optional<Expression> expression, String methodName, List<Expression> arguments) -> {
                if(expression.isPresent())
                    typecheck(symbols, expression.get()); // check what is before

                List<Type> argumentTypes = new ArrayList<>();
                for (var argument : arguments) {
                    typecheck(symbols, argument);
                    argumentTypes.add(getType(symbols, argument));
                }
                var classType = new ClassType(symbols.getCompilingClassName());
                if(expression.isPresent())
                    classType = (ClassType) getType(symbols, expression.get());
                var method = symbols.findMethod(classType, methodName, argumentTypes);
                //System.out.println(classType.className + " " + methodName + " " + argumentTypes);
                if (method.isEmpty()) {
                    throw new SyntaxException(node, String.format("Method %s does not exist", methodName));
                }
            }
            case ConstructorCall(ParserRuleContext ignored, String className, List<Expression> arguments) -> {
                List<Type> argumentTypes = new ArrayList<>();
                for(var arg : arguments){
                    typecheck(symbols, arg);
                    argumentTypes.add(getType(symbols, arg));
                }
                var clazz = symbols.findJavaClass(className);
                if(clazz.isEmpty()){
                    throw new SyntaxException(node, String.format("Class %s does not exist", className));
                } else {
                    var constructor = symbols.findConstructor((ClassType) Reflect.typeFromClass(clazz.get()).get(), argumentTypes);
                    if(constructor.isEmpty()){
                        throw new SyntaxException(node, String.format("Constructor for class %s does not exist with given argument types", className));
                    }
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
                boolean noStringsPresent = !(leftType.equals(stringType)) && !(rightType.equals(stringType));
                //System.out.println(!(leftType.equals(stringType)));
                boolean nonNumericPresent =
                        (!leftType.equals(PrimitiveType.Int) && !leftType.equals(PrimitiveType.Double))
                                || (!rightType.equals(PrimitiveType.Int) && !rightType.equals(PrimitiveType.Double));
                if(operator.equals("+")){
                    if(voidIsPresent) {
                        throw new SyntaxException(node, "Cannot perform addition with a void value.");
                    } else if (nonNumericPresent && noStringsPresent) {
                        throw new SyntaxException(node, String.format("Addition involving %s and %s not possible.", leftType, rightType));
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
                    throw new SyntaxException(node, "Cannot pre-increment " + type);
                }
            }
            case PostIncrement(ParserRuleContext ignored, Expression expression, String ignored2) -> {
                typecheck(symbols, expression);
                var type = getType(symbols, expression);
                if(!type.equals(PrimitiveType.Int) && !type.equals(PrimitiveType.Double)){
                    throw new SyntaxException(node, "Cannot post-increment " + type);
                }
            }
            case Cast(ParserRuleContext ignored, TypeNode type, Expression expression) -> {
                typecheck(symbols, expression);
                var castType = type.type();
                var exprType = getType(symbols, expression);
                var stringType = new ClassType("String");
                if (exprType.equals(VoidType.Instance)) {
                    throw new SyntaxException(node, "Cannot use cast on a void value.");
                } else if (castType.equals(stringType)){
                    // do nothing
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
            case MainMethod(ParserRuleContext ignored, Block block, SymbolTable symbolses) -> {
                //symbolses.allocateVariable(1); // allocate space for this
                typecheck(symbolses, block);
            }
            case MethodDefinition(ParserRuleContext ignored, TypeNode ignored1, String ignored2, List<Parameter> parameters, Block block, SymbolTable symbolses) -> {
                //symbolses.allocateVariable(1); // allocate space for this
                for(var parameter: parameters){
                    typecheck(symbolses, parameter);
                }
                typecheck(symbolses, block);
            }
            case Parameter(ParserRuleContext ignored, TypeNode type, String name) -> {
                Variable parameterVar = symbols.findVariable(name).get();
                parameterVar.setType(type.type());
                parameterVar.setIndex(symbols.getVariableCount());
                if (type.type().equals(PrimitiveType.Double)) {
                    symbols.allocateVariable(2);
                } else {
                    symbols.allocateVariable(1);
                }
            }
            case While(ParserRuleContext ignore, Expression condition, Statement body) -> {
                typecheck(symbols, condition);
                typecheck(symbols, body);
                var conditionType = getType(symbols, condition);
                if (!conditionType.equals(PrimitiveType.Boolean)) {
                    throw new SyntaxException(node, "While condition is must be a boolean type, not " + conditionType);
                }
            }
            /*
            default -> {
                throw new SyntaxException(node, String.format("Typecheck case unimplemented for node %s", node.getNodeDescription()));
            }
             */
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
                    return variable.get().getType();
                }
                else {
                    var clazz = symbols.findJavaClass(variableName);
                    if (clazz.isPresent()) {
                        return new StaticType(variableName);
                    }
                }
            }
            case FieldAccess(ParserRuleContext ignored, Expression expression, String fieldName) -> {
                var classType = getType(symbols, expression);
                var field = symbols.findField((ClassType) classType, fieldName);
                if(field.isPresent()){
                    return field.get().type();
                }
            }
            case MethodCall(ParserRuleContext ignored, Optional<Expression> expression, String methodName, List<Expression> arguments) -> {
                List<Type> argumentTypes = new ArrayList<>();
                var classType = new ClassType(symbols.getCompilingClassName());
                if(expression.isPresent()) {
                    classType = (ClassType) getType(symbols, expression.get());
                }
                for (var argument : arguments)
                    argumentTypes.add(getType(symbols, argument));
                var method = symbols.findMethod((ClassType) classType, methodName, argumentTypes);
                if (method.isPresent())
                    return method.get().returnType();
            }
            case ConstructorCall(ParserRuleContext ignored, String className, List<Expression> arguments) -> {
                List<Type> argumentTypes = new ArrayList<>();
                for(var arg : arguments){
                    argumentTypes.add(getType(symbols, arg));
                }
                var clazz = symbols.findJavaClass(className);
                var constructor = symbols.findConstructor((ClassType) Reflect.typeFromClass(clazz.get()).get(), argumentTypes);
                return constructor.get().containingType();
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
                                ((ClassType) leftType).getClassName().equals("String"))) &&
                            (rightType.equals(PrimitiveType.Double) || rightType.equals(PrimitiveType.Int) || (rightType instanceof ClassType &&
                                    ((ClassType) rightType).getClassName().equals("String")))){
                        // case one is String
                        if( (leftType instanceof ClassType && ((ClassType) leftType).getClassName().equals("String")) ||
                                (rightType instanceof ClassType && ((ClassType) rightType).getClassName().equals("String"))) {
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
                        if(rightType instanceof ClassType && ((ClassType)rightType).getClassName().equals("String")){
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
            default -> System.out.printf("GetType() Unimplemented for Expression: %s\n", expr.getNodeDescription());
        }
        return null;
    }
}
