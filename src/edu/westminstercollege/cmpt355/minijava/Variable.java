package edu.westminstercollege.cmpt355.minijava;

public class Variable {

    private Type type;
    private final String name;
    private int index = -99;
    private boolean isField = false;
    public Variable(String name) {
        this.name = name;
    }
    public void setField(boolean isField){
        this.isField = isField;
    }
    public boolean isField(){
        return isField;
    }
    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public String getName() {
        return name;
    }

    public int getIndex() { return index; }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toString() { return name + "[" + type.toString() + "]"; }

}
