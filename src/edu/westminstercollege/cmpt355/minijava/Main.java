package edu.westminstercollege.cmpt355.minijava;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Main {

    public static void main(String... args) throws IOException, SyntaxException {
        final String EVAL_FILE = "test_programs/hello.eval";
        final String CLASS_NAME = getClassNameFromPath(EVAL_FILE);

        System.out.printf("Compiling class %s from %s...\n", CLASS_NAME, EVAL_FILE);

        var lexer = new MiniJavaLexer(CharStreams.fromFileName(EVAL_FILE));
        var parser = new MiniJavaParser(new CommonTokenStream(lexer));


        var program = parser.goal().n;
        AST.print(program);

    }

    private static String getClassNameFromPath(String path) {
        Path p = Path.of(path);
        String filename = p.getFileName().toString();
        int index = filename.indexOf('.');
        return filename.substring(0, index);
    }
}

