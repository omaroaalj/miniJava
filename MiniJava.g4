// Taeden & Omar - CMPT 355 - 2/17/2023
grammar MiniJava;

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

        $n = new Block(statements);
    }
    ;

statement
    returns [Statement n]
    : ';' {
        $n = new EmptyStatement(); // shouldn't take any parameters?
    }
    | '{' stmt+=statement* '}' {
        // is there a statment? if not, empty statement
        if(stmt.isEmpty()) {
            $n = new EmptyStatement();
        } else {
            $n = $statement.n;
        }
    }
    | decs+=declaration  {
        var declarations = new ArrayList<Declarations>();
        for(var dec : $decs)
            declarations.add(dec.n);

        $n = new Statement(declarations); // will this be a possible parameter of Statement?
    }// would include one or more variable declarations, possibly with initializations
    | expression ';' {
        $n = new ExpressionStatement($expression.n);
    }
    ;

// type followed by a comma-separated list of "items", each being just a name or a name = value.
declaration
    returns [Declaration n] // Declaration contains TypeNode and name of variable
    : type items+=decItem (',' items+=decItem)* ';' {
        //type is a TypeNode, how would Declaration take as parameter?

        var itemlist = new ArrayList<DecItem>();
        for(var item : item)
            itemlist.add(item.n);

        $n = new Declaration($type.text, itemList) //each DecItem possibly contains info on value if initialized?
    }
    ;

decItem
    returns[DecItem n] // DecItem is a name, may contain info for IntLiteral, DoubleLiteral, or VarAccess
    : NAME {
        $n = $NAME.text;
    }
    | NAME '=' expression {
        $n = new DecItem($NAME.text, $expression.n); //expression here must resolve to IntLiteral etc?
    }
    ;

// print()
expression
    returns[ExpressionStatement n]
    : 'print' '(' (args+=expression (',' args+=expression)*)? ')' {
        var prints = new ArrayList<Print>();
        for(var arg : args)
            prints.add(arg.n);

        $n = new Print(prints);
    }
    | INT {
        $n = new IntLiteral($INT.text);
    }
    | DOUBLE {
        $n = new DoubleLiteral($DOUBLE.text);
    }
    | BOOLEAN {
        $n = new BooleanLiteral($BOOLEAN.text);
    }
    | STRING {
        $n = new StringLiteral($STRING.text);
    }
    | NAME  {
        $n = $NAME.text; // match what I have for DecItem?
    }// name (presumably of a variable)
    | '(' expression ')' {
        $n = $expression.n;
    }
    | expression op+=('++' | '--') {
            $n = new PostIncrement($expression.n, $op.text); // $op.text may be ++ or --
    }
    | op+=('++' | '--' | '+' | '-') expression {
        if($op.text.equals("++") || $op.text.equals("--")){
            $n = new PreIncrement($expression.n, $op.text);
        }
        else if($op.text.equals("-")) {
            $n = new Negate($expression.n);
        }
        else {
            $n = $expression.n;
        }
    }
    | '(' type ')' expression {
        $n = new Cast($type.text, $expression);
    }
    | l+=expression op+=('*' | '/' | '%') r+=expression {
        $n = new BinaryOp($op.text, $l.n, $r.n);
    }
    | l+=expression op+=('+' | '-') r+=expression {
        $n = new BinaryOp($op.text, $l.n, $r.n);
    }
    | <assoc=right> expression '=' expression
    ;

type
    returns[TypeNode]
    : 'int' {
        $n = "int";
    }
    | 'double' {
        $n = "double";
    }
    | 'boolean' {
        $n = "boolean";
    }
    | NAME {
        $n = $NAME.text;
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