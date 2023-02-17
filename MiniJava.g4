    grammar MiniJava;

    minijava
        : methodBody EOF
        ;

    methodBody
        : statement
        | expression
        ;

    statement
    :
    ;

    expression
    :
    ;

    COMMENT
    : '//' .*? [/n]
    | '/*' .*? '*/'
    ;