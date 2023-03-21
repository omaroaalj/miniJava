package edu.westminstercollege.cmpt355.minijava;

public class Variable {

    private Type type;
    private String name;
    private int index;

    public Variable(String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public String getName() {
        return name;
    }

    public int getIndex() { return index; }

    public String toString() { return name + "[" + type.toString() + "]"; }

}
