package edu.westminstercollege.cmpt355.minijava.node;

public sealed interface Statement extends Node
        permits Block, Declarations, EmptyStatement, ExpressionStatement, Return {
}
