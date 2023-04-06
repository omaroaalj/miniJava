
package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Compiler {


    // Commented out until we have our AST nodes defined...
    private SymbolTable symbols = new SymbolTable();
    private PrintWriter out;
    private final Block block;
    private final String className;
    private final Typechecker tc = new Typechecker();

    public Compiler(Block block, String className) {

        this.block = block;
        this.className = className;
    }

    public void compile(Path outputDir) throws IOException, SyntaxException {
        Path asmFilePath = outputDir.resolve(className + ".minijava");
        try (var out = new PrintWriter(Files.newBufferedWriter(asmFilePath))) {
            this.out = out;
            resolveSymbols(block);
            Typechecker tc = new Typechecker();
            tc.typecheck(symbols, block);

            out.printf(".class public %s\n", className);
            out.printf(".super java/lang/Object\n");
            out.println();
            out.println(".field private static in Ljava/util/Scanner;");
            out.println();
            out.printf("""
                    .method static <clinit>()V
                    .limit stack 3
                    .limit locals 0
                    new java/util/Scanner
                    dup
                    getstatic java/lang/System/in Ljava/io/InputStream;
                    invokenonvirtual java/util/Scanner/<init>(Ljava/io/InputStream;)V
                    putstatic %s/in Ljava/util/Scanner;
                    return
                    .end method
                    
                    """, className);
            out.printf(".method public static main([Ljava/lang/String;)V\n");
            out.printf(".limit stack 100\n");
            symbols.allocateLocalVariable(1); // allocate space for args[]
            out.printf(".limit locals %d\n", symbols.getVariableCount());
            out.println();

            // Generate code for program here 🙂
            generateCode(out, symbols, block);



            // another way
            //block.statements().forEach(this::generateCode);

            out.printf("return\n");
            out.printf(".end method\n");
        }
    }

    // Make sure that all symbols (in this case, names of variables) make sense,
    // i.e. we should not be using the value of a variable before we have assigned
    // to it (Eval does not have declarations).
    private void resolveSymbols(Block block) throws SyntaxException {
        AST.postOrder(block, node -> {
            switch (node) {
                case VariableAccess(ParserRuleContext ignored, String name) -> {
                    var nameVar = symbols.findVariable(name);
                    if (nameVar.isEmpty()) {
                        // no variable found
                        var nameClass = symbols.findJavaClass(name);
                        if(nameClass.isEmpty()){
                            // no classes found
                            throw new SyntaxException(node, String.format("Variable '%s' used before declaration", name));
                        }
                    }
                }
                case Declaration(ParserRuleContext ignored, String name, Optional<Expression> ignored1) -> {
                    if(symbols.findVariable(name).isPresent()){
                        throw new SyntaxException(node, String.format("Variable '%s' already declared", name));
                    }
                    else {
                        symbols.registerVariable(name);
                    }
                }
                case Assignment(ParserRuleContext ignored, Expression exprName, Expression ignored1) -> {
                    if (exprName instanceof VariableAccess expr) {
                        if (symbols.findVariable(expr.getVariableName()).isEmpty()) {
                            throw new SyntaxException(node, String.format("Variable '%s' used before assignment", expr.getVariableName()));
                        }
                    }
                    else {
                        throw new SyntaxException(node, "Character(s) before '=' not a valid variable");
                    }
                }
                default -> {}
            }
        });
    }
    private void generateCode(PrintWriter out, SymbolTable symbols, Node node) throws SyntaxException {
        switch (node) {
            case EmptyStatement(ParserRuleContext ignored) -> {} // do nothing

            case Block(ParserRuleContext ignored, List<Statement> statements) -> {
                //System.out.println("This was called");
                for (var statement : statements) {
                    out.printf("\n.line %d\n", statement.ctx().getStart().getLine());
                    generateCode(out, symbols, statement);
                }
            }
            case ExpressionStatement(ParserRuleContext ignored, Expression expr) -> {
                generateCode(out, symbols, expr);
                Type exprType = tc.getType(symbols, expr);
                if (exprType.equals(VoidType.Instance)){
                    //do nothing
                }
                else if (exprType.equals(PrimitiveType.Double))
                    out.printf("pop2\n");
                else if (exprType.equals(PrimitiveType.Int) || exprType.equals(PrimitiveType.Boolean))
                    out.printf("pop\n");
            }
            case DoubleLiteral(ParserRuleContext ignored, String text) -> {
                out.printf("ldc2_w %f\n", Double.parseDouble(text));
            }
            case IntLiteral(ParserRuleContext ignored, String text) -> {
                out.printf("ldc %d\n", Integer.parseInt(text));
            }
            case BooleanLiteral(ParserRuleContext ignored, String text) -> {
                if (text.equals("true"))
                    out.printf("iconst_1\n");
                else
                    out.printf("iconst_0\n");
            }
            case StringLiteral(ParserRuleContext ignored, String text) -> {
                out.printf("ldc %s\n", text);
            }
            case Declarations(ParserRuleContext ignored, TypeNode ignored1, List<Declaration> decItems) -> {
                for(var decItem : decItems){
                    generateCode(out, symbols, decItem);
                }
            }
            case Declaration(ParserRuleContext ignored, String name, Optional<Expression> expression) -> {
                // check if there is an initialization
                if(expression.isPresent()) {
                    // genCode for that value so it is on the stack
                    generateCode(out, symbols, expression.get());
                    Variable var = symbols.findVariable(name).get();

                    Type varType = var.getType();
                    Type exprType = tc.getType(symbols, expression.get());
                    var stringType = new ClassType("String");
                    if(varType.equals(PrimitiveType.Int) || varType.equals(PrimitiveType.Boolean)) {
                        out.printf("istore %d\n", var.getIndex());
                    }
                    else if(varType.equals(PrimitiveType.Double) && exprType.equals(PrimitiveType.Int)){
                        out.printf("i2d\n");
                        out.printf("dstore %d\n", var.getIndex());
                    }
                    else if(varType.equals(stringType)){
                        out.printf("astore %d\n", var.getIndex());
                    }
                    else {
                        out.printf("dstore %d\n", var.getIndex());
                    }

                }
            }
            case Print(ParserRuleContext ignored, List<Expression> expressions) -> {
                String printlnArg = "";
                var stringType = new ClassType("String");
                Type exprType;
                for (var expr : expressions) {
                    out.printf("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                    generateCode(out, symbols, expr);
                    exprType = tc.getType(symbols, expr);
                    //System.out.println(exprType);
                    if (exprType.equals(PrimitiveType.Int))
                        printlnArg = "I";
                    else if (exprType.equals(PrimitiveType.Double))
                        printlnArg = "D";
                    else if (exprType.equals(PrimitiveType.Boolean))
                        printlnArg = "Z";
                    else if (exprType.equals(stringType))
                        printlnArg = "Ljava/lang/String;";
                    else
                        printlnArg = "Ljava/lang/String;";
                    out.printf(String.format("invokevirtual java/io/PrintStream/print(%s)V\n", printlnArg));
                }
                // new line after each print statement
                out.printf("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                out.println("invokevirtual java/io/PrintStream/println()V");
            }
            case Assignment(ParserRuleContext ignored, Expression name, Expression expr) -> {
                Variable var = symbols.findVariable(((VariableAccess)name).variableName()).get();
                Type exprType = tc.getType(symbols, name);
                Type assigType = tc.getType(symbols, expr);

                generateCode(out, symbols, expr);
                var stringType = new ClassType("String");
                if (exprType.equals(PrimitiveType.Double) && assigType.equals(PrimitiveType.Int)) {
                    out.printf("i2d\n");
                    out.printf("dup2\n");
                    out.printf("dstore_%d\n", var.getIndex());
                }
                else if (exprType.equals(PrimitiveType.Int) || exprType.equals(PrimitiveType.Boolean)){
                    out.printf("dup\n");
                    out.printf("istore %d\n", var.getIndex());
                }
                else if (exprType.equals(stringType)){
                    out.printf("dup\n");
                    out.printf("astore %d\n", var.getIndex());
                }
                else{
                    out.printf("dup2\n");
                    out.printf("dstore %d\n", var.getIndex());
                }
            }
            case VariableAccess(ParserRuleContext ignored, String variableName) -> {
                Variable var = symbols.findVariable(variableName).get();
                var stringType = new ClassType("String");
                if(var.getType().equals(PrimitiveType.Double)){
                    out.printf("dload %d\n", var.getIndex());
                }
                else if (var.getType().equals(stringType)){
                    out.printf("aload %d\n", var.getIndex());
                }
                else if (var.getType().equals(PrimitiveType.Int) || var.getType().equals(PrimitiveType.Boolean)) {
                    out.printf("iload %d\n", var.getIndex());
                }
                else if (var.getType() instanceof StaticType){
                    // do nothing
                }
            }
            case BinaryOp(ParserRuleContext ignored, String operator, Expression left, Expression right) -> {
                int numberOfInts = 0;
                var stringType = new ClassType("String");

                // convert both expressions to double if any are ints, then convert back to int if both were ints
                var leftType = tc.getType(symbols, left);
                var rightType = tc.getType(symbols, right);
                boolean b = (leftType.equals(stringType) || rightType.equals(stringType));
                if(b && operator.equals("+")) {
                    if(leftType.equals(stringType) && rightType.equals(stringType)) {
                        generateCode(out, symbols, left);
                        generateCode(out, symbols, right);
                    } else if (leftType.equals(PrimitiveType.Int)) {
                        generateCode(out, symbols, left);
                        out.println("invokestatic java/lang/Integer.toString(I)Ljava/lang/String;");
                        generateCode(out, symbols, right);
                    } else if (rightType.equals(PrimitiveType.Int)){
                        generateCode(out, symbols, left);
                        generateCode(out, symbols, right);
                        out.println("invokestatic java/lang/Integer.toString(I)Ljava/lang/String;");
                    } else if (leftType.equals(PrimitiveType.Double)) {
                        generateCode(out, symbols, left);
                        out.println("invokestatic java/lang/Double.toString(D)Ljava/lang/String;");
                        generateCode(out, symbols, right);
                    } else if (rightType.equals(PrimitiveType.Double)) {
                        generateCode(out, symbols, left);
                        generateCode(out, symbols, right);
                        out.println("invokestatic java/lang/Double.toString(D)Ljava/lang/String;");
                    } else if (leftType.equals(PrimitiveType.Boolean)) {
                        generateCode(out, symbols, left);
                        out.println("invokestatic java/lang/String.valueOf(Z)Ljava/lang/String;");
                        generateCode(out, symbols, right);
                    } else if (rightType.equals(PrimitiveType.Boolean)) {
                        generateCode(out, symbols, left);
                        generateCode(out, symbols, right);
                        out.println("invokestatic java/lang/String.valueOf(Z)Ljava/lang/String;");
                    } else if (leftType.equals(stringType) && !rightType.equals(stringType)) {
                        generateCode(out, symbols, left);
                        generateCode(out, symbols, right);
                        out.println("invokestatic java/lang/String.valueOf(Ljava/lang/Object;)Ljava/lang/String;");
                    }
                } else {
                    generateCode(out, symbols, left);
                    if (leftType.equals(PrimitiveType.Int)) {
                        out.println("i2d");
                        numberOfInts++;
                    }
                    generateCode(out, symbols, right);
                    if (rightType.equals(PrimitiveType.Int)) {
                        out.println("i2d");
                        numberOfInts++;
                    }
                }
                switch (operator) {
                    case "+" -> {
                        if (b) {
                            out.println("invokevirtual java/lang/String.concat(Ljava/lang/String;)Ljava/lang/String;");
                        } else {
                            out.println("dadd");
                        }
                    }
                    case "-" -> out.println("dsub");
                    case "*" -> out.println("dmul");
                    case "/" -> out.println("ddiv");
                    case "%" -> out.println("drem");
                }
                if (numberOfInts == 2) // were both expressions ints?
                    out.println("d2i");
            }
            case Cast(ParserRuleContext ignored, TypeNode type, Expression expression) -> {
                Type exprType = tc.getType(symbols, expression);
                generateCode(out, symbols, expression);
                var stringType = new ClassType("String");
                if (type.type().equals(PrimitiveType.Double) && exprType.equals(PrimitiveType.Int)) {
                    out.printf("i2d\n");
                } else if (type.type().equals(PrimitiveType.Int) && exprType.equals(PrimitiveType.Double)) {
                    out.printf("d2i\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Int)) {
                    out.printf("invokestatic java/lang/Integer.toString(I)Ljava/lang/String;\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Double)) {
                    out.printf("invokestatic java/lang/Double.toString(D)Ljava/lang/String;\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Boolean)) {
                    out.printf("invokestatic java/lang/String.valueOf(Z)Ljava/lang/String;\n");
                } else if (type.type().equals(stringType) && exprType.equals(stringType)) {
                    //do nothing
                } else {
                    // typechecker should throw exception if there is an issue
                    out.println("invokevirtual java/lang/Object.toString()Ljava/lang/String;");
                }
            }
            case Negate(ParserRuleContext ignored, Expression expr) -> {
                generateCode(out, symbols, expr);
                var exprType = tc.getType(symbols, expr);
                if (exprType == PrimitiveType.Int) {
                    out.println("ineg");
                } else if (exprType == PrimitiveType.Double) {
                    out.println("dneg");
                }
                else
                    throw new RuntimeException(String.format(
                            "Internal compiler error: type of negate is %s", exprType));
            }
            case PreIncrement(ParserRuleContext ignored, Expression expr, String increment) -> {
                var varName = (VariableAccess)expr;
                var varValue = symbols.findVariable(varName.variableName()).get();
                var exprType = tc.getType(symbols, varName);
                var varIndex = varValue.getIndex();
                if (exprType == PrimitiveType.Int) {
                    if (increment.equals("++"))
                        out.println(String.format("iinc %d 1", varIndex));
                    else
                        out.println(String.format("iinc %d -1", varIndex));
                    out.println(String.format("iload %d", varIndex));
                } else if (exprType == PrimitiveType.Double) {
                    out.println(String.format("dload %d", varIndex));
                    out.println("dconst_1");
                    if (increment.equals("++"))
                        out.println("dadd");
                    else
                        out.println("dsub");
                    out.println("dup2");
                    out.println(String.format("dstore %d", varIndex));
                }
                else
                    throw new RuntimeException(String.format(
                            "Internal compiler error: type of pre-increment is %s", exprType));
            }
            case PostIncrement(ParserRuleContext ignored, Expression expr, String increment) -> {
                var varName = (VariableAccess)expr;
                var varValue = symbols.findVariable(varName.variableName()).get();
                var exprType = tc.getType(symbols, varName);
                var varIndex = varValue.getIndex();
                if (exprType == PrimitiveType.Int) {
                    out.println(String.format("iload %d", varIndex));
                    if (increment.equals("++"))
                        out.println(String.format("iinc %d 1", varIndex));
                    else
                        out.println(String.format("iinc %d -1", varIndex));
                } else if (exprType == PrimitiveType.Double) {
                    out.println(String.format("dload %d", varIndex));
                    out.println("dup2");
                    out.println("dconst_1");
                    if (increment.equals("++"))
                        out.println("dadd");
                    else
                        out.println("dsub");
                    out.println(String.format("dstore %d", varIndex));
                }
                else
                    throw new RuntimeException(String.format(
                            "Internal compiler error: type of pre-increment is %s", exprType));
            }
            case FieldAccess(ParserRuleContext ignored, Expression expression, String fieldName) -> {
                var stringType = new ClassType("String");
                var classType = tc.getType(symbols, expression);
                //find class path
                String classPath = String.valueOf(symbols.findJavaClass(((ClassType) classType).getClassName()).get());
                classPath = classPath.substring(6);
                classPath = classPath.replace('.', '/');
                //find field in order to find its type
                var field = symbols.findField((ClassType) classType, fieldName);
                String printArg = "";
                if(field.get().type().equals(PrimitiveType.Double))
                    printArg = " D";
                else if (field.get().type().equals(PrimitiveType.Int))
                    printArg = " I";
                else if (field.get().type().equals(PrimitiveType.Boolean))
                    printArg = " Z";
                else if (field.get().type().equals(VoidType.Instance))
                    printArg = " V";
                else if (field.get().type().equals(stringType))
                    printArg = " Ljava/lang/String;";
                else {
                    String s = field.get().type().toString();
                    String s1 = " L" + s.substring(10, s.length()-1) + ";";
                    printArg = s1.replace('.','/');
                }
                //is it a static or nonstatic field access
                if(classType instanceof StaticType){
                        out.println("getstatic " + classPath + "." + fieldName + printArg);
                }
                else {
                    generateCode(out, symbols, expression);
                    out.println("getfield " + classPath + "/" + fieldName + printArg);
                }
            }
            case MethodCall(ParserRuleContext ignored, Expression expr, String methodName, List<Expression> arguments) -> {
                // find classType and classPath based of expr
                var classType = tc.getType(symbols, expr);
                String classPath = String.valueOf(symbols.findJavaClass(((ClassType) classType).getClassName()).get());
                classPath = classPath.substring(6);
                classPath = classPath.replace('.', '/');
                    // find arguments Types in order to find the exact Method.
                    // from there we can find its return type.
                    List<Type> argumentTypes = new ArrayList<>();
                    for(var arg : arguments){
                        generateCode(out, symbols, arg);
                        var argument = Reflect.typeFromClass(symbols.classFromType(tc.getType(symbols, arg)).get()).get();
                        argumentTypes.add(argument);
                    }
                    // find java method, then use it to find return type
                    var method = symbols.findMethod((ClassType)classType, methodName, argumentTypes);
                    var returnType = method.get().returnType();
                    // convert returnType to assembly equivalent
                    var returnTypeChar = symbols.classFromType(returnType).get().toString();
                    switch (returnTypeChar) {
                        case "double" -> returnTypeChar = "D";
                        case "boolean" -> returnTypeChar = "Z";
                        case "int" -> returnTypeChar = "I";
                        case "void" -> returnTypeChar = "V";
                        default -> {
                            returnTypeChar = returnTypeChar.substring(6);
                            returnTypeChar = returnTypeChar.replace('.','/');
                            returnTypeChar = "L" + returnTypeChar + ";";
                        }
                    }
                    if(classType instanceof StaticType) {
                        // begin printing actual assembly for static method call
                        out.print("invokestatic " + classPath + "/" + methodName + "(");
                        // print every argumentType
                        for (var type : argumentTypes) {
                            var type1 = symbols.classFromType(type).get().toString();
                            switch (type1) {
                                case "double" -> out.print("D");
                                case "boolean" -> out.print("Z");
                                case "int" -> out.print("I");
                                case "void" -> out.print("V");
                                default -> {
                                    type1 = type1.substring(6);
                                    type1 = type1.replace('.','/');
                                    type1 = "L" + type1 + ";";
                                    out.print(type1);
                                }
                            }
                        }
                    // finish invokestatic line with returnType
                    out.println(")" + returnTypeChar);
                }
                // if the method is nonstatic
                else {
                    // start with generatingCode for expr
                    generateCode(out, symbols, expr);
                    out.print("invokevirtual " + classPath + "/" + methodName + "(");
                        for (var type : argumentTypes) {
                            var type1 = symbols.classFromType(type).get().toString();
                            switch (type1) {
                                case "double" -> out.print("D");
                                case "boolean" -> out.print("Z");
                                case "int" -> out.print("I");
                                case "void" -> out.print("V");
                                default -> {
                                    type1 = type1.substring(6);
                                    type1 = type1.replace('.','/');
                                    type1 = "L" + type1 + ";";
                                    out.print(type1);
                                }
                            }
                        }
                        // finish invokevirtual line with returnType
                        out.println(")" + returnTypeChar);
                }
            }
            case ConstructorCall(ParserRuleContext ignored, String className, List<Expression> arguments) -> {
                var classPath = symbols.findJavaClass(className).get().toString();
                classPath = classPath.substring(6);
                classPath = classPath.replace('.', '/');
                out.println("new " + classPath);
                out.println("dup");
                List<String> argumentTypes = new ArrayList<>();
                for (var arg : arguments) {
                    generateCode(out, symbols, arg);
                    var argument = symbols.classFromType(tc.getType(symbols, arg)).get().toString();
                    switch (argument) {
                        case "int" -> argument = "I";
                        case "double" -> argument = "D";
                        case "boolean" -> argument = "Z";
                        case "void" -> argument = "V";
                        default -> {
                            argument = argument.substring(6);
                            argument = argument.replace('.','/');
                            argument = "L" + argument + ";";
                        }
                    }
                        argumentTypes.add(argument);
                }
                out.print("invokenonvirtual " + classPath + ".<init>(");
                for (String type : argumentTypes) {
                    out.print(type);
                }
                out.println(")V");
            }
            default -> {
                throw new SyntaxException("Unimplemented");
            }
        }
    }
}
