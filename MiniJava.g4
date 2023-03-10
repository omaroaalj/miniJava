// Taeden & Omar - CMPT 355 - 2/17/2023
grammar MiniJava;

@parser::header {
import edu.westminstercollege.cmpt355.minijava.node.*;
import java.util.Optional;
import java.util.ArrayList;
}

goal
    returns [Block n]
    : methodBody {
        $n = $methodBody.n;
    }
    ;

methodBody
    returns [Block n]
    : (stmts+=statement)* EOF {
        var statements = new ArrayList<Statement>();
        for(var stmt : $stmts)
            statements.add(stmt.n);

        $n = new Block($ctx, statements);
    }
    ;

statement
    returns [Statement n]
    : ';' {
        $n = new EmptyStatement($ctx);
    }
    | '{' stmts+=statement* '}' {
        // is there a statment? if not, empty statement
            var stmtList = new ArrayList<Statement>();
            for(var stmt : $stmts){
                stmtList.add(stmt.n);
            }
            $n = new Block($ctx, stmtList);
    }
    // would include one or more variable declarations, possibly with initializations
    | declaration  {
        $n = $declaration.n;
    }
    | expression ';' {
        $n = new ExpressionStatement($ctx, $expression.n);
    }
    ;

// type followed by a comma-separated list of "items", each being just a name or a name = value.
declaration
    returns [Declarations n] // Declaration contains TypeNode and name of variable
    : type items+=decItem (',' items+=decItem)* ';' {
        //Declarations parameters: TypeNode, list of Declaration
        //Declaration parameters: String name, Optional<Expression>

        var itemList = new ArrayList<Declaration>();
        for(var item : $items)
            itemList.add(item.n);

        $n = new Declarations($ctx, $type.n, itemList);
    }
    ;

decItem
    returns[Declaration n] // DecItem is a name, may contain info for IntLiteral, DoubleLiteral, or VarAccess
    : NAME {
        $n = new Declaration($ctx, $NAME.text, Optional.empty());
    }
    | NAME '=' expression {
        $n = new Declaration($ctx, $NAME.text, Optional.of($expression.n));
    }
    ;

expression
    returns[Expression n]
    : 'print' '(' (args+=expression (',' args+=expression)*)? ')' {
        var prints = new ArrayList<Expression>();
        for(var arg : $args)
            prints.add(arg.n);

        $n = new Print($ctx, prints);
    }
    | INT {
        $n = new IntLiteral($ctx, $INT.text);
    }
    | DOUBLE {
        $n = new DoubleLiteral($ctx, $DOUBLE.text);
    }
    | BOOLEAN {
        $n = new BooleanLiteral($ctx, $BOOLEAN.text);
    }
    | STRING {
        $n = new StringLiteral($ctx, $STRING.text);
    }
    // name (presumably of a variable)
    | NAME  {
        $n = new VariableAccess($ctx, $NAME.text);
    }
    | '(' expression ')' {
        $n = $expression.n;
    }
    | e=expression op=('++' | '--') {
            $n = new PostIncrement($ctx, $e.n, $op.text); // $op.text may be ++ or --
    }
    | op=('++' | '--' | '+' | '-') expression {
        if($op.text.equals("++") || $op.text.equals("--")){
            $n = new PreIncrement($ctx, $expression.n, $op.text);
        }
        else if($op.text.equals("-")) {
            $n = new Negate($ctx, $expression.n);
        }
        else {
            $n = $expression.n;
        }
    }
    | '(' type ')' expression {
        $n = new Cast($ctx, $type.n, $expression.n);
    }
    | l=expression op=('*' | '/' | '%') r=expression {
        $n = new BinaryOp($ctx, $op.text, $l.n, $r.n);
    }
    | l=expression op=('+' | '-') r=expression {
        $n = new BinaryOp($ctx, $op.text, $l.n, $r.n);
    }
    | <assoc=right> l=expression '=' r=expression {
        $n = new Assignment($ctx, $l.n, $r.n);
    }
    ;

type
    returns[TypeNode n]
    : 'int' {
        $n = new TypeNode($ctx, "int");
    }
    | 'double' {
        $n = new TypeNode($ctx, "double");
    }
    | 'boolean' {
        $n = new TypeNode($ctx, "boolean");
    }
    | NAME {
        $n = new TypeNode($ctx, $NAME.text);
    }
    ;

RESERVED_WORD
    : 'abstract'   | 'continue'   | 'for'          | 'new'         | 'switch'
    | 'assert'     | 'default'    | 'if'           | 'package'     | 'synchronized'
    | 'boolean'    | 'do'         | 'goto'         | 'private'     | 'this'
    | 'break'      | 'double'     | 'implements'   | 'protected'   | 'throw'
    | 'byte'       | 'else'       | 'import'       | 'public'      | 'throws'
    | 'case'       | 'enum'       | 'instanceof'   | 'return'      | 'transient'
    | 'catch'      | 'extends'    | 'int'          | 'short'       | 'try'
    | 'char'       | 'final'      | 'interface'    | 'static'      | 'void'
    | 'class'      | 'finally'    | 'long'         | 'strictfp'    | 'volatile'
    | 'const'      | 'float'      | 'native'       | 'super'       | 'while'
    | '_'
    ;

// letters, numbers, dollar signs '$', underscores '_', but not starting with a digit
NAME
    : [a-zA-Z_$] [a-zA-Z_$0-9]*
    ;

WHITESPACE
    : [ \n\r\t]+ -> skip
    ;

// fragment: doesn't generate tokens, but can be used in other lexer rules
fragment DIGITS
    : [0-9]+
    ;

fragment FIXED_POINT
    : [0-9]+ '.' [0-9]*
    | [0-9]* '.' [0-9]+
    ;

INT
    : DIGITS
    ;

DOUBLE
    :  FIXED_POINT
    | FIXED_POINT [Ee] [+-]? INT
    | DIGITS [Ee] [+-]? DIGITS
    ;

BOOLEAN
    : 'true'
    | 'false'
    ;

STRING
    : '"' .*? '"'
    ;

LINE_COMMENT
    : '//' .*? ('\n' | EOF) -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/'         -> skip
    ;