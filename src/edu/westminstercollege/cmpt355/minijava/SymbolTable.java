package edu.westminstercollege.cmpt355.minijava;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SymbolTable {

    private Map<String, Variable> variables = new HashMap<>();
    // args is index 0
    public int index = 0;
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
}
