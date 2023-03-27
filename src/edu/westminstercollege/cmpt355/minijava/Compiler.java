
package edu.westminstercollege.cmpt355.minijava;

import edu.westminstercollege.cmpt355.minijava.node.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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
                case VariableAccess(ParserRuleContext ctx, String name) -> {
                    var nameVar = symbols.findVariable(name);
                    if (nameVar.isEmpty())
                        // no variable found
                        throw new SyntaxException(node, String.format("Variable '%s' used before declaration", name));
                    //System.out.println("[" + nameVar.get().getType().toString() + "]" + nameVar.get().getName());
                }
                case Declaration(ParserRuleContext ctx, String name, Optional<Expression> expression1) -> {
                    if(symbols.findVariable(name).isPresent()){
                        throw new SyntaxException(node, String.format("Variable '%s' already declared", name));
                    }
                    else {
                        symbols.registerVariable(name);
                    }
                }
                case Assignment(ParserRuleContext ctx, Expression exprName, Expression expression) -> {
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
            case EmptyStatement(ParserRuleContext ctx) -> {} // do nothing

            case Block(ParserRuleContext ctx, List<Statement> statements) -> {
                //System.out.println("This was called");
                for (var statement : statements) {
                    out.printf("\n.line %d\n", statement.ctx().getStart().getLine());
                    generateCode(out, symbols, statement);
                }
            }
            case ExpressionStatement(ParserRuleContext ctx, Expression expr) -> {
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
            case DoubleLiteral(ParserRuleContext ctx, String text) -> {
                out.printf("ldc2_w %f\n", Double.parseDouble(text));
            }
            case IntLiteral(ParserRuleContext ctx, String text) -> {
                out.printf("ldc %d\n", Integer.parseInt(text));
            }
            case BooleanLiteral(ParserRuleContext ctx, String text) -> {
                if (text.equals("true"))
                    out.printf("iconst_1\n");
                else
                    out.printf("iconst_0\n");
            }
            case StringLiteral(ParserRuleContext ctx1, String text) -> {
                out.printf("ldc %s\n", text);
            }
            case Declarations(ParserRuleContext ctx, TypeNode type, List<Declaration> decItems) -> {
                for(var decItem : decItems){
                    generateCode(out, symbols, decItem);
                }
            }
            case Declaration(ParserRuleContext ctx, String name, Optional<Expression> expression) -> {
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
            case Print(ParserRuleContext ctx, List<Expression> expressions) -> {
                // out.printf("getstatic java/lang/System/out Ljava/io/PrintStream;");
                String printlnArg = "";
                var stringType = new ClassType("String");
                Type exprType;
                for (var expr : expressions) {
                    out.printf("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
                    generateCode(out, symbols, expr);
                    exprType = tc.getType(symbols, expr);
                    if (exprType.equals(PrimitiveType.Int))
                        printlnArg = "I";
                    else if (exprType.equals(PrimitiveType.Double))
                        printlnArg = "D";
                    else if (exprType.equals(PrimitiveType.Boolean))
                        printlnArg = "Z";
                    else if (exprType.equals(stringType))
                        printlnArg = "Ljava/lang/String;";
                    else
                        throw new SyntaxException("Print argument Unimplemented");
                    out.printf(String.format("invokevirtual java/io/PrintStream/println(%s)V\n", printlnArg));
                }
            }
            case Assignment(ParserRuleContext ctx, Expression name, Expression expr) -> {
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
            case VariableAccess(ParserRuleContext ctx, String variableName) -> {
                Variable var = symbols.findVariable(variableName).get();
                var stringType = new ClassType("String");
                if(var.getType().equals(PrimitiveType.Double)){
                    out.printf("dload %d\n", var.getIndex());
                }
                else if (var.getType().equals(stringType)){
                    out.printf("aload %d\n", var.getIndex());
                }
                else
                    out.printf("iload %d\n", var.getIndex());
            }
            case BinaryOp(ParserRuleContext ctx, String operator, Expression left, Expression right) -> {
                int numberOfInts = 0;
                generateCode(out, symbols, left);

                // convert both expressions to double if any are ints, then convert back to int if both were ints

                var leftType = tc.getType(symbols, left);
                if (leftType.equals(PrimitiveType.Int)) {
                    out.println("i2d");
                    numberOfInts++;
                }
                generateCode(out, symbols, right);
                var rightType = tc.getType(symbols, right);
                if (rightType.equals(PrimitiveType.Int)) {
                    out.println("i2d");
                    numberOfInts++;
                }

                switch (operator) {
                    case "+" -> out.println("dadd");
                    case "-" -> out.println("dsub");
                    case "*" -> out.println("dmul");
                    case "/" -> out.println("ddiv");
                    case "%" -> out.println("drem");
                }
                if (numberOfInts == 2) // were both expressions ints?
                    out.println("d2i");
            }
            default -> {
                throw new SyntaxException("Unimplemented");
            }
        }
    }

    /*
    private void generateCode(Statement statement) {
        switch (statement) {
            case EmptyStatement() -> {
                //do nothing?
            }
            case Block(List<Statement> stmts) -> {

                for (var stmt : stmts)
                    generateCode(stmt);
                // args.forEach(this::generateCode);
                // generateCode should handle each kind of statement later
            }
            case Declarations(TypeNode type, List<Declaration> decItems) -> {
                for(var dec : decItems){
                    generateCode(dec);
                    //need to make private void generateCode(Declaration dec){}
                }

            }

            case Assignment(String varName, Expression value) -> {
                // x = ...
                Variable var = symbols.findVariable(varName).get();
                generateCode(value); // get value to be assigned on top of the stack
                out.printf("dstore %d\n", var.getIndex());
            }
            default -> {}
        }
    }

    private void generateCode(PrintArgument argument) {
        switch (argument) {
            case Expression e -> {
                out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
                generateCode(e);
                out.println("invokevirtual java/io/PrintStream/println(D)V");
            }
            case StringArgument(String text) -> {
                // out is a static variable
                out.println("getstatic java/lang/System/out Ljava/io/PrintStream;");
                // load constant
                out.printf("ldc %s\n", text);
                // invoke virtual - calling a method
                // L - class names
                // V - void return type
                out.println("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
            }

            default ->
                    throw new RuntimeException(String.format("Unimplemented: %s", argument.getNodeDescription()));
        }
    }

    private void generateCode(Expression expr) {
        switch (expr) {
            case Literal(String text) -> {
                // jasmin expects doubles/floats for numbers, 2 slots for doubles
                out.printf("ldc2_w %f\n", Double.parseDouble(text));
            }
            case Add(Expression left, Expression right) -> {
                generateCode(left);
                generateCode(right);
                out.println("dadd");
            }
            case Subtract(Expression left, Expression right) -> {
                generateCode(left);
                generateCode(right);
                out.println("dsub");
            }
            case Multiply(Expression left, Expression right) -> {
                generateCode(left);
                generateCode(right);
                out.println("dmul");
            }
            case Divide(Expression left, Expression right) -> {
                generateCode(left);
                generateCode(right);
                out.println("ddiv");
            }

            case Negate(Expression child) -> {
                generateCode(child);
                out.println("dneg");
            }

            case SquareRoot(Expression child) -> {
                generateCode(child);
                // call Math.sqrt() method
                out.println("invokestatic java/lang/Math/sqrt(D)D");
            }

            case Power(Expression left, Expression right) -> {
                generateCode(left);
                generateCode(right);
                // any method that takes two or more parameters, do not put commas
                out.println("invokestatic java/lang/Math/pow(DD)D");
            }

            case VariableAccess(String variableName) -> {
                Variable v = symbols.findVariable(variableName).get();
                // add \n when using printf
                out.printf("dload %d\n", v.getIndex());
            }

            case Input(List<PrintArgument> args) -> {
                // Print out the arguments
                for (var arg : args)
                    generateCode(arg);

                // Read a double value from the user (???)
                // Load the value of "in" (the static Scanner variable)
                out.printf("getstatic %s/in Ljava/util/Scanner;\n", className);
                // Call the nextDouble() method
                out.println("invokevirtual java/util/Scanner/nextDouble()D");
            }

            default ->
                    throw new RuntimeException(String.format("Unimplemented: %s", expr.getNodeDescription()));
        }
    }

    // */
}
