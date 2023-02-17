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
    ;

    init
    : '=' NUMBER
    | '=' NAME
    ;

    DATATYPE
    : 'int'
    | 'double'
    | 'boolean'
    ;

    NAME
    : [A-Za-z_$]+([a-z]|[_$]|[0-9])*
    ;

    NUMBER
    : [0-9]+
    | [0-9]+ '.'? [0-9]+
    ;

    COMMENT
    : '//' .*? [/n]
    | '/*' .*? '*/'
    ;