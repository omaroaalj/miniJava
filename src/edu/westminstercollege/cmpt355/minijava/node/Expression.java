package edu.westminstercollege.cmpt355.minijava.node;

public sealed interface Expression extends Node
        permits Assignment, BinaryOp, BooleanLiteral, Cast, ConstructorCall, DoubleLiteral, FieldAccess, IntLiteral, MethodCall, Negate, PostIncrement, PreIncrement, Print, StringLiteral, This, VariableAccess {

}
