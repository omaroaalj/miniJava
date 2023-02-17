// comment to check if git is working
    grammar MiniJava;

    minijava
        : methodBody EOF
        ;

    methodBody
    : statement
    | expression
    ;

    statement
    : declaration ('\n'statement)?
    ;

    expression
    : 'print(' expression ')'
    | NUMBER
    | 'true'
    | 'false'
    | '"' (.*? | escape+) '"'
    | NAME
    | '(' expression ')'
    | expression ('++' | '--')
    | ('++' | '+' | '--' | '-') expression
    | '(' DATATYPE ')' expression
    | expression ('*' | '/' | '%') expression
    | expression ('+' | '-') expression
    ;

    declaration
    : DATATYPE NAME init? ';'
    | DATATYPE names init? ';'
    ;

    init
    : '=' NUMBER
    | '=' NAME
    ;

    escape
    : '\n'
    | '\t'
    | '\\'
    ;

    names
    : NAME init?
    | NAME init? ',' names
    ;

    COMMENT
    : '/*' .*? '*/' -> skip
    ;

    LINE_COMMENT
    : '//' .*? [\n] -> skip
    ;

    DATATYPE
    : 'int'
    | 'double'
    | 'boolean'
    ;

    NUMBER
    : [+-]? [0-9]+
    | [+-]? [0-9]+ '.'? [0-9]+
    | [0-9] '.' [0-9]+ [eE] [+-]? [0-9]+
    | [0-9]+ [eE] [+-]? [0-9]+
    ;

    NAME
    : [A-Za-z_$]+([a-z]|[_$]|[0-9])*
    ;

    WS
    : [ ] -> skip
    ;