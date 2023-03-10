package edu.westminstercollege.cmpt355.minijava;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SymbolTable {

    private Map<String, Variable> variables = new HashMap<>();
    private int variableIndex = 1;
    // args is index 0

    public Variable registerVariable(String name) {
        Variable v = variables.get(name);
        if (v == null) {
            v = new Variable(name, variableIndex);
            variableIndex += 2;
            variables.put(name, v);
        }

        return v;
    }

    public int getVariableCount() {
        return variables.size();
    }

    public Optional<Variable> findVariable(String name) {
        return Optional.ofNullable(variables.get(name));
    }
}
