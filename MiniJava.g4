// Taeden & Omar - CMPT 355 - 2/17/2023
grammar MiniJava;

@parser::header {
import edu.westminstercollege.cmpt355.minijava.node.*;
import java.util.Optional;
import java.util.ArrayList;
}

goal
    returns [Block n]
    : classNode {
        $n = $classNode.n;
    }
    ;

classNode
    returns [ClassNode n]
    : (classStmts+=classStatement)* EOF {
        var statements = new ArrayList<Statement>();
        for(var stmt : $classStmts)
            statements.add(stmt.n);
        $n = new ClassNode($ctx, statements);
    }
    ;

classStatement
    returns [Statement n]
    : 'import ' importNames+=NAME ('.' importNames+=NAME)* ';' {
        var importParts = new ArrayList<String>();
        for (var importName : $importNames)
            importParts.add(importName.text);
        $n = new ClassImport($ctx, importParts);
    }
    | 'import ' importNames+=NAME '.' (importNames+=NAME '.')* '*;' {
        var importParts = new ArrayList<String>();
        for (var importName : $importNames)
            importParts.add(importName.text);
        $n = new PackageImport($ctx, importParts);
    }
    | type NAME ';' {
        $n = new FieldDefinition($ctx, $type.n, $NAME.text, Optional.empty());
    }
    | type NAME '=' e=expression ';' {
        $n = new FieldDefinition($ctx, $type.n, $NAME.text, Optional.of($e.n));
    }
    | type NAME '(' (parameters+=parameter (',' parameters+=parameter)*)? ')' '{' methodBody returnStmt? '}' {
        var parameterList = new ArrayList<Parameter>();
        for (var p : $parameters)
            parameterList.add(p.n);

        var statementList = new ArrayList<Statement>();
        for (var s : $stmts)
            statementList.add(s.n);

        $n = new MethodDefinition($ctx, $type.n, $NAME,text, parameterList, statementList);
        // DID NOT USE OPTIONALS
    }
    | 'void main()' '{' methodBody '}' {
        var statementList = new ArrayList<Statement>();
        for (var s : $stmts)
            statementList.add(s.n);

        $n = new MainMethodDefinition($ctx, statementList);
    }
    ;

methodBody
    returns [Block n]
    : (stmt+=statement)* {
        var statements = new ArrayList<Statement>();
        for(var stmt : $classStmts)
            statements.add(stmt.n);
        $n = new Block($ctx, statements);
    }
    ;

parameter
    returns [Parameter n]
    : type NAME {
        $n = new Parameter($ctx, $type.n, $NAME.text);
    }
    ;

returnStmt
    returns [Return n]
    : 'return ' e=expression ';' {
        $n = new Return($ctx, Optional.of($e.n));
    }
    | 'return;' {
        $n = new Return($ctx, Optional.empty());
    }
    ;

statement
    returns [Statement n]
    : ';' {
        $n = new EmptyStatement($ctx);
    }
    | '{' stmts+=statement* '}' {
        // is there a statment? if not, empty statement
            var stmtList = new ArrayList<Statement>();
            for(var stmt : $stmts){
                stmtList.add(stmt.n);
            }
            $n = new Block($ctx, stmtList);
    }
    // would include one or more variable declarations, possibly with initializations
    | declaration  {
        $n = $declaration.n;
    }
    | expression ';' {
        $n = new ExpressionStatement($ctx, $expression.n);
    }
    ;

// type followed by a comma-separated list of "items", each being just a name or a name = value.
declaration
    returns [Declarations n] // Declaration contains TypeNode and name of variable
    : type items+=decItem (',' items+=decItem)* ';' {
        //Declarations parameters: TypeNode, list of Declaration
        //Declaration parameters: String name, Optional<Expression>

        var itemList = new ArrayList<Declaration>();
        for(var item : $items)
            itemList.add(item.n);

        $n = new Declarations($ctx, $type.n, itemList);
    }
    ;

decItem
    returns[Declaration n] // DecItem is a name, may contain info for IntLiteral, DoubleLiteral, or VarAccess
    : NAME {
        $n = new Declaration($ctx, $NAME.text, Optional.empty());
    }
    | NAME '=' e=expression {
        $n = new Declaration($ctx, $NAME.text, Optional.of($e.n));
    }
    ;

expression
    returns[Expression n]
    : '_print' '(' (args+=expression (',' args+=expression)*)? ')' {
        var prints = new ArrayList<Expression>();
        for(var arg : $args)
            prints.add(arg.n);

        $n = new Print($ctx, prints);
    }
    | INT {
        $n = new IntLiteral($ctx, $INT.text);
    }
    | DOUBLE {
        $n = new DoubleLiteral($ctx, $DOUBLE.text);
    }
    | BOOLEAN {
        $n = new BooleanLiteral($ctx, $BOOLEAN.text);
    }
    | 'this' {
        $n = new This();
    }
    | STRING {
        $n = new StringLiteral($ctx, $STRING.text);
    }
    // name (presumably of a variable)
    | NAME  {
        $n = new VariableAccess($ctx, $NAME.text);
    }
    | e=expression '.' NAME {
        $n = new FieldAccess($ctx, $e.n, $NAME.text);
    }
    | e=expression '.' NAME '(' (args+=expression (',' args+=expression)*)? ')' {
        var methodArgs = new ArrayList<Expression>();
        for (var arg : $args)
            methodArgs.add(arg.n);
        $n = new MethodCall($ctx, $e.n, $NAME.text, methodArgs);
    }
    | 'new' NAME '(' (args+=expression (',' args+=expression)*)? ')' {
        var constructorArgs = new ArrayList<Expression>();
        for (var arg : $args)
            constructorArgs.add(arg.n);
        $n = new ConstructorCall($ctx, $NAME.text, constructorArgs);
    }
    | '(' e=expression ')' {
        $n = $e.n;
    }
    | e=expression op=('++' | '--') {
            $n = new PostIncrement($ctx, $e.n, $op.text); // $op.text may be ++ or --
    }
    | op=('++' | '--' | '+' | '-') e=expression {
        if($op.text.equals("++") || $op.text.equals("--")){
            $n = new PreIncrement($ctx, $e.n, $op.text);
        }
        else if($op.text.equals("-")) {
            $n = new Negate($ctx, $e.n);
        }
        else {
            $n = $e.n;
        }
    }
    | '(' type ')' e=expression {
        $n = new Cast($ctx, $type.n, $e.n);
    }
    | l=expression op=('*' | '/' | '%') r=expression {
        $n = new BinaryOp($ctx, $op.text, $l.n, $r.n);
    }
    | l=expression op=('+' | '-') r=expression {
        $n = new BinaryOp($ctx, $op.text, $l.n, $r.n);
    }
    | <assoc=right> l=expression '=' r=expression {
        $n = new Assignment($ctx, $l.n, $r.n);
    }
    ;

type
    returns[TypeNode n]
    : 'int' {
        $n = new TypeNode($ctx, PrimitiveType.Int);
    }
    | 'double' {
        $n = new TypeNode($ctx, PrimitiveType.Double);
    }
    | 'boolean' {
        $n = new TypeNode($ctx, PrimitiveType.Boolean);
    }
    | NAME {
        $n = new TypeNode($ctx, new ClassType($NAME.text));
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

NAME
    : [a-zA-Z_$] [a-zA-Z_$0-9]*
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