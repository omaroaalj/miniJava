package edu.westminstercollege.cmpt355.minijava;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Main {

    public static void main(String... args) throws IOException, SyntaxException {
        final String MINI_FILE = "test_programs/test.minijava";
        final String CLASS_NAME = getClassNameFromPath(MINI_FILE);

        System.out.printf("Compiling class %s from %s...\n", CLASS_NAME, MINI_FILE);

        var lexer = new MiniJavaLexer(CharStreams.fromFileName(MINI_FILE));
        var parser = new MiniJavaParser(new CommonTokenStream(lexer));


        var program = parser.goal().n;
        AST.print(program);

        /*
        var compiler = new Compiler(program, CLASS_NAME);
        compiler.compile(Path.of("test_output"));

        jasmin.Main.main(new String[] {
                "-d", "out/test_compiled",
                String.format("test_output/%s.j", CLASS_NAME)
        });
        */
    }

    private static String getClassNameFromPath(String path) {
        Path p = Path.of(path);
        String filename = p.getFileName().toString();
        int index = filename.indexOf('.');
        return filename.substring(0, index);
    }
}

