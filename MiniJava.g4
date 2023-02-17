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
    : declaration
    ;

    expression
    :
    ;

    declaration
    : DATATYPE NAME init? ';'
    | DATATYPE names init? ';'
    ;

    init
    : '=' NUMBER
    | '=' NAME
    ;

    names
    : NAME init?
    | NAME init? ',' names
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

    COMMENT
    : '//' .*? [/n]
    | '/*' .*? '*/'
    ;

    WS
    : [ ] -> skip
    ;