// comment to check if git is working
    grammar MiniJava;

    minijava
        : methodBody EOF
        ;

    methodBody
    : '\n'* statement ('\n'* statement|'\n'* expression |'\n'* declaration)* '\n'*
    | '\n'* expression ('\n'* statement|'\n'* expression |'\n'* declaration)* '\n'*
    | '\n'* declaration ('\n'* statement|'\n'* expression |'\n'* declaration)* '\n'*
    ;

    statement
    : '{' '\n'* '}' ('\n'* statement|'\n'* expression |'\n'* declaration)*
    | ';' ('\n'* statement|'\n'* expression |'\n'* declaration)*
    | '{' '\n'* ('\n'* statement|'\n'* expression |'\n'* declaration)* '}'
    ;

    declaration
    : DATATYPE NAME init? ';'
    | DATATYPE names init? ';'
    ;

    expression
    : 'print(' (expressions* | escape*) ')' ';'
    | expression init ';'
    | NUMBER
    | STRING
    | 'true'
    | 'false'
    | '"' (.*? | escape+) '"'
    | NAME
    | '(' expression ')'
    | expression ('++' | '--') ';'?
    | ('++' | '+' | '--' | '-') expression
    | '(' DATATYPE ')' expression
    | expression ('*' | '/' | '%') expression
    | expression ('+' | '-') expression
    ;

    expressions
    : expression
    | expression ',' expressions
    ;

    init
    : '=' expression
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
    | 'String'
    | 'byte'
    | 'char'
    ;

    NUMBER
    : [+-]? [0-9]+
    | [+-]? [0-9]+ '.'
    | [+-]? [0-9]+ '.'? [0-9]+
    | '.' [0-9]+
    | [0-9] '.' [0-9]+ [eE] [+-]? [0-9]+
    | [0-9]+ [eE] [+-]? [0-9]+
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