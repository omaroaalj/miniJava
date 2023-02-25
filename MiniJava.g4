// Taeden & Omar - CMPT 355 - 2/17/2023
    grammar MiniJava;

    minijava
        : methodBody EOF
        ;

    methodBody
        : statement*
        ;

    statement
        : '{' '}' (statement|expression|declaration)*
        | ';' (statement|expression|declaration)*
        | '{'(statement|expression|declaration)* '}'
        | expression ';'
        | declaration
        ;

    declaration
        : DATATYPE DATATYPE init? ';'
        | DATATYPE names init? ';'
        ;

    expression
        : 'print(' (exprbodies* | escape*) ')'
        | expression init
        | NUMBER
        | STRING
        | 'true'
        | 'false'
        | '"' (.*? | escape+) '"'
        | NAME
        | '(' expression ')'
        | expression ('++' | '--')
        | ('++' | '+' | '--' | '-') expression
        | '(' DATATYPE ')' expression
        | '(' NAME ')' expression
        | expression ('*' | '/' | '%') expression
        | expression ('+' | '-') expression
        | <assoc=right> expression '=' expression
        ;

    exprbodies
        : expression
        | expression ',' exprbodies
        ;

    init
        : '=' expression
        ;

    escape
        : '\\n'
        | '\\t'
        | '\\\\'
        ;

    names
        : NAME init?
        | NAME init? ',' names
        ;

    COMMENT
        : '/*' .*? '*/' -> skip
        ;

    LINE_COMMENT
        : '//' .*? '\n' -> skip
        ;

    DATATYPE
        : 'int'
        | 'double'
        | 'boolean'
        | NAME
        ;

    NUMBER
        : [+-]? [0-9]+
        | [+-]? [0-9]+ '.'? [0-9]*
        | [+-]? [0-9]* '.'? [0-9]+
        | [0-9] '.' [0-9]* [eE] [+-]? [0-9]+
        | '.'[0-9]+ [eE] [+-]? [0-9]+
        | [0-9] [eE] [+-]? [0-9]+
        ;

    RESERVED
        : 'abstract' | 'assert' | 'break'
        | 'case' | 'catch' | 'class' | 'const'
        | '_' | 'continue' | 'default' | 'do'
        | 'else' | 'enum' | 'extends' | 'final'
        | 'finally' | 'float' | 'for'| 'if'
        | 'goto' | 'implements' | 'import'
        | 'instanceof' | 'interface' | 'long'
        | 'native' | 'new' | 'package' | 'private'
        | 'protected' | 'public' | 'return'
        | 'short' | 'static' | 'strictfp' | 'super'
        ;

    NAME
        : [A-Za-z_$]+([a-z]|[_$]|[0-9])*
        ;

    STRING
        : '"' .*? '"'
        ;

    WS
        : [ \r\t\n] -> skip
        ;