package edu.westminstercollege.cmpt355.minijava;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException, SyntaxException {
        final String MINI_FILE = "test_programs/test.minijava";
        final String CLASS_NAME = getClassNameFromPath(MINI_FILE);

        System.out.printf("Compiling class %s from %s...\n", CLASS_NAME, MINI_FILE);

        var lexer = new MiniJavaLexer(CharStreams.fromFileName(MINI_FILE));
        var parser = new MiniJavaParser(new CommonTokenStream(lexer));


        var block = parser.goal().n;
        AST.print(block);

        // added lines from Step 01 of project 7:
        var compiledClassPath = Path.of(String.format("out/test_compiled/%s.class", CLASS_NAME));
        Files.deleteIfExists(compiledClassPath);
        try {
            var compiler = new Compiler(block, CLASS_NAME);
            compiler.compile(Path.of("test_output"));

            jasmin.Main.main(new String[]{
                    "-d", "out/test_compiled",
                    String.format("test_output/%s.minijava", CLASS_NAME)
            });
        }
        catch (SyntaxException se) {
            System.out.println("ERROR syntax exception: " + se.getMessage());
            //System.out.println("ERROR: " + se.message + " [line " + se.getNode().ctx().start.getLine() + "]");
        }

        try {
            // Use reflection to find the class that was just compiled
            var compiledClass = Class.forName(CLASS_NAME);
            // Find its main() method
            var compiledMainMethod = compiledClass.getMethod("main", String[].class);

            System.out.printf("——— Running compiled class %s ———\n", CLASS_NAME);
            // Run the compiled main()
            compiledMainMethod.invoke(null, new Object[] { new String[0] });
            System.out.println("——— End of output ———");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException ex) {
            System.err.println("Unable to execute newly-compiled program: class or method not found!");
        } catch (InvocationTargetException ex) {
            // An exception was thrown by the compiled program (not a compiler problem 🙂)
            ex.getTargetException().printStackTrace();
        }
        // Find Field tests:
        //System.out.println(Reflect.findField(System.class, "out"));
        //System.out.println(Reflect.findField(java.awt.Point.class, "x"));
        //System.out.println(Reflect.findField(Math.class, "pie"));

        // Find Method tests:
        //System.out.println(Reflect.findMethod(Math.class, "pow", List.of(Double.TYPE, Double.TYPE)));
        //System.out.println(Reflect.findMethod(java.io.PrintStream.class, "println", List.of()));
        //System.out.println(Reflect.findMethod(Math.class, "zowie", List.of()));
        //System.out.println(Reflect.findMethod(Math.class, "pow", List.of(Integer.TYPE, Integer.TYPE)));

        // Find Constructor tests:
        //System.out.println(Reflect.findConstructor(Object.class, List.of()));
        //System.out.println(Reflect.findConstructor(String.class, List.of(String.class)));
        //System.out.println(Reflect.findConstructor(Math.class, List.of()));

        // SymbolTable methods (make sure to make methods static to test them)
            // findJavaClass
        //System.out.println(SymbolTable.findJavaClass("java.util.Scanner"));
            // classFromType
        //System.out.println(SymbolTable.classFromType(VoidType.Instance).get());
        //System.out.println(SymbolTable.classFromType(PrimitiveType.Int).get());
        //System.out.println(SymbolTable.classFromType(PrimitiveType.Double).get());
        //System.out.println(SymbolTable.classFromType(PrimitiveType.Boolean).get());
            // findField
        //System.out.println(SymbolTable.findField(new ClassType("Badger"), "Bob")); // hard to test w/out minijava file

    }

    private static String getClassNameFromPath(String path) {
        Path p = Path.of(path);
        String filename = p.getFileName().toString();
        int index = filename.indexOf('.');
        return filename.substring(0, index);
    }
}

