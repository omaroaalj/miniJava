
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
    private SymbolTable symbols = new SymbolTable(SymbolTable.Level.Class);
    private PrintWriter out;
    private final Node node;
    private final String className;
    private final Typechecker tc = new Typechecker();

    public Compiler(Node node, String className) {

        this.node = node;
        this.className = className;
    }

    public void compile(Path outputDir) throws IOException, SyntaxException {
        Path asmFilePath = outputDir.resolve(className + ".minijava");
        symbols.setCompilingClassName(className);
        try (var out = new PrintWriter(Files.newBufferedWriter(asmFilePath))) {
            this.out = out;
            resolveSymbols(node, symbols);
            Typechecker tc = new Typechecker();
            tc.typecheck(symbols, node);
            generateCode(out, symbols, node);
        }
    }

    // Make sure that all symbols (in this case, names of variables) make sense,
    // i.e. we should not be using the value of a variable before we have assigned
    // to it (Eval does not have declarations).
    private void resolveSymbols(Node node, SymbolTable symbols) throws SyntaxException {
        switch(node){
            case FieldDefinition(ParserRuleContext ignored, TypeNode type, String name, Optional<Expression> expr) -> {
                if(expr.isPresent()){
                    resolveSymbols(expr.get(), symbols);
                }
                if(symbols.findVariable(name).isPresent()){
                    if(symbols.findVariable(name).get().isField()){
                        throw new SyntaxException(node, String.format("Field with name: %s already exists.", name));
                    }
                } else {
                    symbols.registerField(name, type.type());
                }
            }
            case MethodDefinition(ParserRuleContext ignored, TypeNode returnType, String name, List<Parameter> parameters, Block block1, SymbolTable symbolses) -> {
                List<Type> parameterTypes = new ArrayList<>();
                for(var param : parameters){
                    resolveSymbols(param, symbolses);
                    parameterTypes.add(param.type().type());
                }
                ClassType classType = new ClassType(symbols.getCompilingClassName());
                if(symbols.findMethod(classType, name, parameterTypes).isPresent()){
                    throw new SyntaxException(node, String.format("Method %s already exists", name));
                } else {
                    symbols.registerMethod(name, parameterTypes, returnType.type());
                    symbolses.setParent(symbols);
                    resolveSymbols(block1, symbolses);
                }
            }
            case MainMethod(ParserRuleContext ignored, Block block1, SymbolTable symbolses) -> {
                List<Type> parameterTypes = new ArrayList<>();
                symbols.registerMethod("main", parameterTypes, VoidType.Instance);
                symbolses.setParent(symbols);
                resolveSymbols(block1, symbolses);
            }
            case Parameter(ParserRuleContext ignored, TypeNode type, String name) -> {
                if(symbols.findVariable(name).isPresent()){
                    throw new SyntaxException(node, String.format("Parameter %s already exists", name));
                } else {
                    symbols.registerVariable(name, type.type());
                }
            }
            case Block(ParserRuleContext ignored, List<Statement> statements, SymbolTable symbolses) -> {
                symbolses.setParent(symbols);
                for(var stmt : statements){
                    resolveSymbols(stmt, symbolses);
                }
            }
            case Declarations(ParserRuleContext ignore, TypeNode type, List<Declaration> decItems) -> {
                for(var decItem : decItems){
                    if(symbols.findVariable(decItem.name()).isPresent()){
                        if(!symbols.findVariable(decItem.name()).get().isField()) {
                            throw new SyntaxException(node, String.format("Variable '%s' already declared", decItem.name()));
                        }
                        else {
                            symbols.registerVariable(decItem.name(), type.type());
                        }
                    }
                    else {
                        if(decItem.expression().isPresent()){
                            symbols.registerVariable(decItem.name(), type.type());
                        } else {
                            symbols.registerVariable(decItem.name(), VoidType.Instance);
                        }
                    }
                }
            }
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
            default -> {
                for(var child : node.children()) {
                    resolveSymbols(child, symbols);
                }
            }
        }
    }
    private String getAssemblyType(Type type){
        var stringType = new ClassType("String");
        if(type.equals(PrimitiveType.Int)) {
            return "I";
        }
        else if(type.equals(PrimitiveType.Double)) {
            return "D";
        }
        else if(type.equals(PrimitiveType.Boolean)) {
            return "Z";
        }
        else if(type.equals(VoidType.Instance)) {
            return "V";
        }
        else if(type.equals(stringType)) {
            return "Ljava/lang/String;";
        }
        else {
            String s = ((ClassType) type).getClassName();
            s = symbols.findJavaClass(s).get().descriptorString();
            return s;
        }
    }
    private void generateCode(PrintWriter out, SymbolTable symbols, Node node) throws SyntaxException {
        switch (node) {
            case EmptyStatement(ParserRuleContext ignored) -> {} // do nothing
            case ClassNode(ParserRuleContext ignore, List<Node> elements) -> {
                out.printf(".class public %s\n", className);
                out.printf(".super java/lang/Object\n\n");
                boolean fieldConstructorMade = false;
                for(var element : elements) {
                    if (element instanceof FieldDefinition field) {
                        out.printf(".field public %s %s\n\n", field.name(), getAssemblyType(field.type().type()));
                    }
                }
                out.println(".method public <init>()V");
                out.printf(".limit stack 10\n.limit locals %s\n\n", symbols.getVariableCount());
                out.println("aload_0");
                out.print("invokenonvirtual java/lang/Object/<init>()V\n");
                for(var element : elements) {
                    if (element instanceof FieldDefinition) {
                        generateCode(out, symbols, element);
                    }
                }
                out.printf("return\n");
                out.printf(".end method\n\n");
                for(var element : elements) {
                    if (!(element instanceof FieldDefinition) && !(element instanceof ClassImport) && !(element instanceof PackageImport)) {
                        generateCode(out, symbols, element);
                    }
                }
            }
            case FieldDefinition(ParserRuleContext ignore, TypeNode type, String name, Optional<Expression> expr) -> {
                if (expr.isPresent()) { // if there is initialization
                    out.println("aload_0");
                    generateCode(out, symbols, expr.get());
                    if(type.type().equals(PrimitiveType.Double) && tc.getType(symbols, expr.get()).equals(PrimitiveType.Int)){
                        out.println("i2d");
                    }
                    String typeDescriptor = getAssemblyType(type.type());
                    out.printf("putfield %s/%s %s\n", symbols.getCompilingClassName(), name, typeDescriptor); // [???] I don't know if this works
                }
            }
            case Block(ParserRuleContext ignored, List<Statement> statements, SymbolTable symbolses) -> {
                for (var statement : statements) {
                    out.printf("\n.line %d\n", statement.ctx().getStart().getLine());
                    generateCode(out, symbolses, statement);
                }
            }
            case MainMethod(ParserRuleContext ignored, Block block, SymbolTable symbolses) -> {
                out.println(".method public main()V");
                out.printf(".limit stack 100\n.limit locals %d\n", symbolses.getVariableCount());
                generateCode(out, symbolses, block);
                out.println("return\n.end method\n");
                out.println(".method public static main([Ljava/lang/String;)V");
                out.printf(".limit stack 100\n.limit locals %d\n", symbolses.getVariableCount());
                out.printf("new %s\ndup\n", symbols.getCompilingClassName());
                out.printf("invokenonvirtual %s/<init>()V\n", symbols.getCompilingClassName());
                out.printf("invokevirtual %s/main()V\n", symbols.getCompilingClassName());
                out.printf("return\n.end method\n\n");
            }
            case MethodDefinition(ParserRuleContext ignored, TypeNode returnType, String name, List<Parameter> parameters, Block block, SymbolTable symbolses) -> {
                out.printf(".method public %s(", name);
                for (var parameter : parameters) {
                    var parameterTypeDescriptor = getAssemblyType(parameter.type().type());
                    out.printf(parameterTypeDescriptor); // [???] I don't know if this works
                }
                var returnTypeDescriptor = getAssemblyType(returnType.type());
                out.printf(")%s\n", returnTypeDescriptor); // [???] I don't know if this works
                out.printf(".limit stack 100\n.limit locals %d\n", symbolses.getVariableCount());
                generateCode(out, symbolses, block);
                if (returnType.type() instanceof VoidType) {
                    out.println("return");
                }
                out.println(".end method\n");
            }
            case Return(ParserRuleContext ignored, Optional<Expression> value) -> {
                if(value.isPresent()){
                    var expr = value.get();
                    var type = tc.getType(symbols, expr);
                    generateCode(out, symbols, expr);
                    if (type.equals(PrimitiveType.Double))
                        out.println("dreturn");
                    else if (type.equals(PrimitiveType.Int) || type.equals(PrimitiveType.Boolean))
                        out.println("ireturn");
                    else if (type.equals(VoidType.Instance))
                        out.println("return");
                    else
                        out.println("areturn");
                }
                else
                    out.println("return");
            }
            case This(ParserRuleContext ignored) -> out.println("aload_0");
            case ExpressionStatement(ParserRuleContext ignored, Expression expr) -> {
                generateCode(out, symbols, expr);
                Type exprType = tc.getType(symbols, expr);
                if (exprType.equals(VoidType.Instance)){
                    //do nothing
                }
                else if (exprType.equals(PrimitiveType.Double))
                    out.print("pop2\n");
                else if (exprType.equals(PrimitiveType.Int) || exprType.equals(PrimitiveType.Boolean))
                    out.print("pop\n");
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
                    else if(varType.equals(PrimitiveType.Double)){
                        out.printf("dstore %d\n", var.getIndex());
                    }
                    else {
                        out.printf("astore %d\n", var.getIndex());
                    }

                }
            }
            case Print(ParserRuleContext ignored, List<Expression> expressions) -> {
                String printlnArg = "";
                var stringType = new ClassType("String");
                Type exprType;
                for (var expr : expressions) {
                    out.print("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                    generateCode(out, symbols, expr);
                    exprType = tc.getType(symbols, expr);
                    if (exprType.equals(PrimitiveType.Int))
                        printlnArg = "I";
                    else if (exprType.equals(PrimitiveType.Double))
                        printlnArg = "D";
                    else if (exprType.equals(PrimitiveType.Boolean))
                        printlnArg = "Z";
                    else if (exprType.equals(stringType)) {
                        printlnArg = "Ljava/lang/String;";
                    }
                    else {
                        printlnArg = "Ljava/lang/String;";
                        out.println("invokevirtual java/lang/Object.toString()Ljava/lang/String;");
                    }
                    out.printf("invokevirtual java/io/PrintStream/print(%s)V\n", printlnArg);
                }
                // new line after each print statement
                out.print("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                out.println("invokevirtual java/io/PrintStream/println()V");
            }
            case Assignment(ParserRuleContext ignored, Expression name, Expression expr) -> {
                Variable var = symbols.findVariable(((VariableAccess)name).variableName()).get();
                Type exprType = tc.getType(symbols, name);
                Type assigType = tc.getType(symbols, expr);

                if (var.isField())
                    out.println("aload_0");

                generateCode(out, symbols, expr);
                var stringType = new ClassType("String");
                if (exprType.equals(PrimitiveType.Double) && assigType.equals(PrimitiveType.Int)) {
                    out.print("i2d\n");
                    if (var.isField()) {
                        out.println("dup2_x1");
                        out.printf("putfield %s/%s D\n", symbols.getCompilingClassName(), var.getName());
                    } else {
                        out.print("dup2\n");
                        out.printf("dstore_%d\n", var.getIndex());
                    }
                }
                else if (exprType.equals(PrimitiveType.Int) || exprType.equals(PrimitiveType.Boolean)){
                    if (var.isField()) {
                        out.println("dup_x1");
                        out.printf("putfield %s/%s I\n", symbols.getCompilingClassName(), var.getName());
                    } else {
                        out.print("dup\n");
                        out.printf("istore %d\n", var.getIndex());
                    }
                }
                else if (exprType.equals(stringType)){
                    if (var.isField()) {
                        out.println("dup_x1");
                        out.printf("putfield %s/%s Ljava/lang/String;\n", symbols.getCompilingClassName(), var.getName());
                    } else {
                        out.print("dup\n");
                        out.printf("astore %d\n", var.getIndex());
                    }
                }
                else {
                    if (var.isField()) { // maybe this is going to work?
                        out.println("dup2_x1");
                        out.printf("putfield %s/%s D\n", symbols.getCompilingClassName(), var.getName());
                    } else {
                        out.printf("dup2\n");
                        out.printf("dstore %d\n", var.getIndex());
                    }
                }
            }
            case VariableAccess(ParserRuleContext ignored, String variableName) -> {
                Variable var = symbols.findVariable(variableName).get();
                var stringType = new ClassType("String");
                if (var.getType().equals(PrimitiveType.Double)) {
                    if (var.isField()) {
                        out.println("aload_0");
                        out.printf("getfield %s/%s D\n", symbols.getCompilingClassName(), variableName);
                    }
                    else
                        out.printf("dload %d\n", var.getIndex());
                } else if (var.getType().equals(stringType)) {
                    if (var.isField()) {
                        out.println("aload_0");
                        out.printf("getfield %s/%s Ljava/lang/String;\n", symbols.getCompilingClassName(), variableName);
                    }
                    else
                        out.printf("aload %d\n", var.getIndex());
                } else if (var.getType().equals(PrimitiveType.Int) || var.getType().equals(PrimitiveType.Boolean)) {
                    if (var.isField() && var.getIndex() == -99) {
                        out.println("aload_0");
                        out.printf("getfield %s/%s I\n", symbols.getCompilingClassName(), variableName);
                    }
                    else
                        out.printf("iload %d\n", var.getIndex());
                } else if (var.getType() instanceof StaticType) {
                    // do nothing
                } else {
                    if (var.isField()) {
                        out.println("aload_0");
                        var typeDescriptor = symbols.findJavaClass(((ClassType) var.getType()).getClassName()).get().descriptorString();
                        out.printf("getfield %s/%s %s\n", symbols.getCompilingClassName(), variableName, typeDescriptor);
                    } else {
                        out.printf("aload %d\n", var.getIndex());
                    }
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
                    out.print("i2d\n");
                } else if (type.type().equals(PrimitiveType.Int) && exprType.equals(PrimitiveType.Double)) {
                    out.print("d2i\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Int)) {
                    out.print("invokestatic java/lang/Integer.toString(I)Ljava/lang/String;\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Double)) {
                    out.print("invokestatic java/lang/Double.toString(D)Ljava/lang/String;\n");
                } else if (type.type().equals(stringType) && exprType.equals(PrimitiveType.Boolean)) {
                    out.print("invokestatic java/lang/String.valueOf(Z)Ljava/lang/String;\n");
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
                    if (varValue.isField()) {
                        out.printf("getfield %s/%s I\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("iload %d", varIndex));
                    }
                } else if (exprType == PrimitiveType.Double) {
                    if (varValue.isField()) {
                        out.printf("getfield %s/%s I\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("dload %d", varIndex));
                    }
                    out.println("dconst_1");
                    if (increment.equals("++"))
                        out.println("dadd");
                    else
                        out.println("dsub");
                    out.println("dup2");
                    if (varValue.isField()) {
                        out.printf("putfield %s/%s D\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("dstore %d", varIndex));
                    }
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
                    if (varValue.isField()) {
                        out.printf("getfield %s/%s I\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("iload %d", varIndex));
                    }
                    if (increment.equals("++"))
                        out.println(String.format("iinc %d 1", varIndex));
                    else
                        out.println(String.format("iinc %d -1", varIndex));
                } else if (exprType == PrimitiveType.Double) {
                    if (varValue.isField()) {
                        out.printf("getfield %s/%s D\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("dload %d", varIndex));
                    }
                    out.println("dup2");
                    out.println("dconst_1");
                    if (increment.equals("++"))
                        out.println("dadd");
                    else
                        out.println("dsub");
                    if (varValue.isField()) {
                        out.printf("putfield %s/%s D\n", symbols.getCompilingClassName(), varName);
                    } else {
                        out.println(String.format("dstore %d", varIndex));
                    }
                }
                else
                    throw new RuntimeException(String.format(
                            "Internal compiler error: type of pre-increment is %s", exprType));
            }
            case FieldAccess(ParserRuleContext ignored, Expression expression, String fieldName) -> {
                var stringType = new ClassType("String");
                var classType = tc.getType(symbols, expression);
                //find class path
                String classPath = symbols.findJavaClass(((ClassType) classType).getClassName()).get().descriptorString();
                classPath = classPath.substring(1, classPath.length()-1);
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
                if(classType instanceof StaticType) {
                        out.println("getstatic " + classPath + "." + fieldName + printArg);
                }
                else {
                    generateCode(out, symbols, expression);
                    out.println("getfield " + classPath + "/" + fieldName + printArg);
                }
            }
            case MethodCall(ParserRuleContext ignored, Optional<Expression> expr, String methodName, List<Expression> arguments) -> {
                // find classType and classPath based of expr
                ClassType classType = new ClassType(symbols.getCompilingClassName());
                if(expr.isPresent()) {
                    classType =(ClassType) tc.getType(symbols, expr.get());
                String classPath = symbols.findJavaClass(((ClassType) classType).getClassName()).get().descriptorString();
                classPath = classPath.substring(1, classPath.length()-1);
                    // find arguments Types in order to find the exact Method.
                    // from there we can find its return type.
                    List<Type> argumentTypes = new ArrayList<>();
                    for(var arg : arguments){
                        //generateCode(out, symbols, arg);
                        var argument = Reflect.typeFromClass(symbols.classFromType(tc.getType(symbols, arg)).get()).get();
                        argumentTypes.add(argument);
                    }
                    // find java method, then use it to find return type
                    var method = symbols.findMethod((ClassType)classType, methodName, argumentTypes);
                    var returnType = method.get().returnType();
                    // convert returnType to assembly equivalent
                    var returnTypeChar = getAssemblyType(returnType);
                    if(classType instanceof StaticType) {
                        for(var arg : arguments) {
                            generateCode(out, symbols, arg);
                        }
                        // begin printing actual assembly for static method call
                        out.print("invokestatic " + classPath + "/" + methodName + "(");
                        // print every argumentType
                        for (var type : argumentTypes) {
                            out.print(getAssemblyType(type));
                        }
                    // finish invokestatic line with returnType
                    out.println(")" + returnTypeChar);
                }
                // if the method is nonstatic
                else {
                        // start with generatingCode for expr
                        generateCode(out, symbols, expr.get());
                        for (var arg : arguments) {
                            generateCode(out, symbols, arg);
                        }
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
                                    type1 = type1.replace('.', '/');
                                    type1 = "L" + type1 + ";";
                                    out.print(type1);
                                }
                            }
                        }
                        // finish invokevirtual line with returnType
                        out.println(")" + returnTypeChar);
                    }
                } else {
                    List<Type> argumentTypes = new ArrayList<>();
                    for(var arg : arguments){
                        //generateCode(out, symbols, arg);
                        var argument = tc.getType(symbols, arg);
                        argumentTypes.add(argument);
                    }
                    out.println("aload_0");
                    for (var arg : arguments) {
                        generateCode(out, symbols, arg);
                    }
                    out.print("invokevirtual " + symbols.getCompilingClassName() + "/" + methodName + "(");
                    for (var type : argumentTypes) {
                        out.print(getAssemblyType(type));
                    }
                    var method = symbols.findMethod((ClassType)classType, methodName, argumentTypes);
                    var returnType = method.get().returnType();
                    var returnTypeChar = getAssemblyType(returnType);
                    out.println(")" + returnTypeChar);
                }
            }
            case ConstructorCall(ParserRuleContext ignored, String className, List<Expression> arguments) -> {
                var classPath = symbols.findJavaClass(className).get().descriptorString();
                classPath = classPath.substring(1, classPath.length()-1);
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
            case While(ParserRuleContext ignore, Expression condition, Statement body) -> {
                String loopStartLabel = symbols.newLabel("loop_start");
                String loopEndLabel = symbols.newLabel("loop_end");
                out.printf("%s:\n", loopStartLabel);
                generateCode(out, symbols, condition);
                // My condition is boolean, so the top of the stack is either true (1) or false (0)
                // If it's true (1), I should continue executing the body; [no branch]
                // if it's false (0), I should jump to the end of the loop. [branch]
                out.printf("ifeq %s\n", loopEndLabel);

                generateCode(out, symbols, body);

                // Return to the top of the loop to check the condition again
                out.printf("goto %s\n", loopStartLabel);
                out.printf("%s:\n", loopEndLabel); // Point to jump to when the condition fails
            }
            case If(ParserRuleContext ignore, Expression condition, Statement body) -> {
                String ifEndLabel = symbols.newLabel("if_end");
                generateCode(out, symbols, condition);
                // My condition is boolean, so the top of the stack is either true (1) or false (0)
                // If it's true (1), I should execute the body; [no branch]
                // if it's false (0), I should jump to the end of the if. [branch]
                out.printf("ifeq %s\n", ifEndLabel);

                generateCode(out, symbols, body);

                out.printf("%s:\n", ifEndLabel); // Point to jump to when the condition fails
            }
            case IfElse(ParserRuleContext ignore, Expression condition, Statement body, Statement elseBody) -> {
                // Conditional branch at top: if condition is false, jump to else
                // Unconditional branch at the end of the body: jump past the else
                String elseLabel = symbols.newLabel("else");
                String ifEndLabel = symbols.newLabel("if_end");
                generateCode(out, symbols, condition);
                // My condition is boolean, so the top of the stack is either true (1) or false (0)
                // If it's true (1), I should execute the body; [no branch]
                // if it's false (0), I should jump to the end of the if. [branch]
                out.printf("ifeq %s\n", elseLabel);
                generateCode(out, symbols, body);
                out.printf("goto %s\n", ifEndLabel);

                out.printf("%s:\n", elseLabel);
                generateCode(out, symbols, elseBody);

                out.printf("%s:\n", ifEndLabel); // Point to jump to when the condition fails
            }
            case RelationalOp(ParserRuleContext ignore, Expression left, Expression right, String op) -> {
                var leftType = tc.getType(symbols, left);
                var rightType = tc.getType(symbols, right);

                boolean bothNumeric = tc.isNumeric(leftType) && tc.isNumeric(rightType);
                boolean bothBoolean = leftType == PrimitiveType.Boolean && rightType == PrimitiveType.Boolean;
                boolean bothObjects = leftType instanceof ClassType && rightType instanceof ClassType;

                // Possibilities
                // < / > / <= / >= / == / != (ints)     if_cmp<op>
                // < / > / <= / >= / == / != (doubles)  dcmpg
                // == / != (booleans)                   if_icmp<op>
                // == / != (objects)                    if_acmp<op>
                // Goal: end up with 0 (false) or 1 (true) on the stack

                String instrOp = switch (op) {
                    case "==" -> "eq";
                    case "!=" -> "ne";
                    case "<" -> "lt";
                    case ">" -> "gt";
                    case "<=" -> "le";
                    case ">=" -> "ge";
                    default -> throw new RuntimeException("Internal compiler error: no applicable operator");
                };

                String trueLabel = symbols.newLabel("trueCase");
                String endLabel = symbols.newLabel("end");

                if (leftType == PrimitiveType.Double || rightType == PrimitiveType.Double) {
                    generateCode(out, symbols, left);
                    if(leftType == PrimitiveType.Int)
                        out.println("i2d");

                    generateCode(out, symbols, right);
                    if(rightType == PrimitiveType.Int)
                        out.println("i2d");

                    out.printf("dcmpg\n");  // this compares the two doubles on the stack
                    // now the top of the stack is either
                    //  - -1 if left < right
                    //  - 0 if left == right
                    //  - +1 if left > right

                    // Say we're evaluating a condition like x < y
                    // We now have -1, 0, or 1 on the stack; we should branch to the true case if it's -1
                    out.printf("if%s %s\n", instrOp, trueLabel);

                    // false case
                    out.println("iconst_0");
                    out.printf("goto %s\n", endLabel);

                    // true case
                    out.printf("%s:\n", trueLabel);
                    out.println("iconst_1");

                    out.printf("%s:\n", endLabel);
                } else if (leftType == PrimitiveType.Int && rightType == PrimitiveType.Int
                    || leftType == PrimitiveType.Boolean
                    || leftType instanceof ClassType) {
                    // if (!(left op right))
                    //      iconst_0 // push false
                    //      goto end
                    // else
                    // trueCase:
                    //      iconst_1 // push true
                    // end:
                    generateCode(out, symbols, left);
                    generateCode(out, symbols, right);

                    if(leftType instanceof ClassType){
                        out.printf("if_acmp%s %s\n", instrOp, trueLabel);
                    } else {
                        out.printf("if_icmp%s %s\n", instrOp, trueLabel);
                    }

                    // false case
                    out.println("iconst_0");
                    out.printf("goto %s\n", endLabel);

                    // true case
                    out.printf("%s:\n", trueLabel);
                    out.println("iconst_1");

                    out.printf("%s:\n", endLabel);
                } else
                    throw new RuntimeException("Unimplemented comparison case");
            }
            default -> {
                throw new SyntaxException(String.format("GenerateCode() unimplemented for node %s", node.getNodeDescription()));
            }
        }
    }
}
