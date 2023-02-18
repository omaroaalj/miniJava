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
    : exprbodies ';'
    ;

    exprbody
    : 'print(' (exprbodies* | escape*) ')'
    | exprbody init
    | NUMBER
    | STRING
    | 'true'
    | 'false'
    | '"' (.*? | escape+) '"'
    | NAME
    | '(' exprbody ')'
    | exprbody ('++' | '--')
    | ('++' | '+' | '--' | '-') exprbody
    | '(' DATATYPE ')' exprbody
    | '(' NAME ')' exprbody
    | exprbody ('*' | '/' | '%') exprbody
    | exprbody ('+' | '-') exprbody
    | <assoc=right> exprbody '=' exprbody
    ;

    exprbodies
    : exprbody
    | exprbody ',' exprbodies
    ;

    init
    : '=' exprbody
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
    : '//' .*? '\n' -> skip
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