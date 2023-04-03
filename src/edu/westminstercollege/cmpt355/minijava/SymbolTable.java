package edu.westminstercollege.cmpt355.minijava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SymbolTable {

    private Map<String, Variable> variables = new HashMap<>();
    // args is index 0
    public int index = 0;
    private int varIndex = 0;
    public int allocateLocalVariable(int size){
        return index += size;
    }
    public Variable registerVariable(String name) {
        Variable v = variables.get(name);
        if (v == null) {
            v = new Variable(name);
            variables.put(name, v);
        }

        return v;
    }

    public int getVariableCount() {
        return index;
    }

    public Optional<Variable> findVariable(String name) {
        return Optional.ofNullable(variables.get(name));
    }

    public static Optional<Class<?>> findJavaClass(String className) {
        var clazz = Reflect.classForName(className);
        if(clazz.isEmpty()){
            clazz = Reflect.classForName(String.format("java.lang." + className));
            if(clazz.isEmpty()){
                clazz = Reflect.classForName(String.format("java.util." + className));
                if(clazz.isEmpty()){
                    return Optional.empty();
                }
                else {
                    return clazz;
                }
            }
            else {
                return clazz;
            }
        }
        else {
            return clazz;
        }
    }

    public Optional<Class> classFromType(Type type){
        return Optional.empty();
    }

    public Optional<Field> findField(ClassType classType, String fieldName){
        return Optional.empty();
    }

    public Optional<Method> findMethod(ClassType classType, String methodName, List<Type> parameterTypes){
        return Optional.empty();
    }

    public Optional<Method> findConstructor(ClassType classType, List<Type> parameterTypes){
        return Optional.empty();
    }
}
