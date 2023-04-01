package edu.westminstercollege.cmpt355.minijava;

public sealed class ClassType implements Type permits StaticType {
    String className;
    public ClassType(String className){
        this.className = className;
    }
    public String getClassName() {
        return this.className;
    }
    public String toString(){
        return String.format("ClassType[" + className + "]");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
