// Taeden & Omar - CMPT 355 - 2/17/2023
grammar MiniJava;

    goal
        : methodBody EOF
        ;

    methodBody
        : statement*
        ;

    statement
        : ';'
        | '{' statement* '}'
        | declaration  // would include one or more variable declarations, possibly with initializations
        | expression ';'
        ;

    // type followed by a comma-separated list of "items", each being just a name or a name = value.
    declaration
        : type decItem (',' decItem)* ';'
        ;

    decItem
        : NAME
        | NAME '=' expression
        ;

    // print()
    expression
        : 'print' '(' (expression (',' expression)*)? ')'
        | INT
        | DOUBLE
        | BOOLEAN
        | STRING
        | NAME  // name (presumably of a variable)
        | '(' expression ')'
        | expression ('++' | '--')
        | ('++' | '--' | '+' | '-') expression
        | '(' type ')' expression
        | expression ('*' | '/' | '%') expression
        | expression ('+' | '-') expression
        | <assoc=right> expression '=' expression
        ;

    type
        : 'int'
        | 'double'
        | 'boolean'
        | NAME
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