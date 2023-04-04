package edu.westminstercollege.cmpt355.minijava;
import java.util.Objects;

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
        if (obj == null || !(obj instanceof ClassType)) {
            return false;
        }
        ClassType other = (ClassType) obj;
        return Objects.equals(this.className, other.className);
    }


    public int hashCode() {
        return super.hashCode();
    }
}
