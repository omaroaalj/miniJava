
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
            /*
            // Generate code for program here 🙂
            for (var statement : block.statements()) {
                //generateCode(statement);
            }
            */


            // another way
            // program.statements().forEach(this::generateCode);

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
                for (var statement : statements)
                    generateCode(out, symbols, statement);
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
