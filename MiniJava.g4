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
    :
    ;

    expression
    :
    ;

    COMMENT
    : '//' .*? [/n]
    | '/*' .*? '*/'
    ;