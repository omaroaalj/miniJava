package edu.westminstercollege.cmpt355.minijava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

public class SymbolTable {

    private Map<String, Variable> variables = new HashMap<>();
    private Map<String, Class<?>> importedClasses = new HashMap<>();
    private List<String> importedPackages = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    // args is index 0
    private int index = 0;
    private int varIndex = 0;

    public enum Level {
        Class, Method, Block
    }

    private Level level;
    private SymbolTable parent;
    private String compilingClassName;

    public SymbolTable(Level level) {
        if (level == null)
            throw new IllegalArgumentException("Level cannot be null!");
        this.level = level;
        importedPackages.add("java.lang");
    }
    public Level getLevel(){
        return level;
    }
    public void setCompilingClassName(String compilingClassName){
        this.compilingClassName = compilingClassName;
    }
    public String getCompilingClassName() {
        if (compilingClassName == null && parent != null)
            return parent.getCompilingClassName();
        return compilingClassName;
    }
    public void setParent(SymbolTable parent){
        this.parent = parent;
    }
    public Optional<SymbolTable> getParent() {
        return Optional.ofNullable(parent);
    }
    public Variable registerField(String name, Type type){
        Variable v = variables.get(name);
        if (v == null) {
            v = new Variable(name);
            variables.put(name, v);
        }
        v.setField(true);

        return v;
    }
    public Method registerMethod(String name, List<Type> parameterTypes, Type returnType) {
        var method = new Method(new ClassType(getCompilingClassName()), name, parameterTypes, returnType);
        methods.add(method);
        return method;
    }
    public void importClass(Class<?> clazz) {
        importedClasses.put(clazz.getSimpleName(), clazz);
    }
    public void importPackage(String packageName) {
        importedPackages.add(packageName);
    }
    public Optional<Variable> findVariable(String name) {
        var maybeVariable = Optional.ofNullable(variables.get(name));
        if (maybeVariable.isEmpty() && parent != null)
            return parent.findVariable(name);
        return maybeVariable;
    }

    public int allocateVariable(int size) {
        if (level == Level.Method) {
            // the same code as before...
            return index += size;
        } else if (parent != null) {
            // bump it up a level
            return parent.allocateVariable(size);
        } else {
            throw new RuntimeException("Internal compiler error: symbol table");
        }
    }

    /*
    public int allocateLocalVariable(int size){
        return index += size;
    }
    */
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
    /*
    public Optional<Variable> findVariable(String name) {
        return Optional.ofNullable(variables.get(name));
    }
    */

    public Optional<Class<?>> findJavaClass(String className) {
        if (importedClasses.containsKey(className))
            return Optional.of(importedClasses.get(className));

        var maybeClass = Reflect.classForName(className);

        if (maybeClass.isEmpty() && !className.contains(".")) {
            for (var packageName : importedPackages) {
                maybeClass = Reflect.classForName(packageName + "." + className);
                if (maybeClass.isPresent())
                    break;
            }
        }

        if (maybeClass.isEmpty() && parent != null)
            return parent.findJavaClass(className);

        return maybeClass;
    }

    public Optional<Class<?>> classFromType(Type type) {
        return switch (type) {
            case PrimitiveType pt -> Optional.of(switch (pt) {
                case Int -> Integer.TYPE;
                case Double -> Double.TYPE;
                case Boolean -> Boolean.TYPE;
            });

            case VoidType vt -> Optional.of(Void.TYPE);

            case ClassType ct -> findJavaClass(ct.getClassName());
        };
    }
    public Optional<List<Class<?>>> classesFromTypes(List<? extends Type> types) {
        List<Class<?>> classes = new ArrayList<>(types.size());
        for (var type : types) {
            var maybeClass = classFromType(type);
            if (maybeClass.isEmpty())
                return Optional.empty();
            classes.add(maybeClass.get());
        }
        return Optional.of(classes);
    }


    public Optional<Field> findField(ClassType classType, String fieldName) {
        if (classType.getClassName().equals(getCompilingClassName())) {
            return findLocalField(fieldName);
        }
        var maybeField = classFromType(classType)
                .flatMap(c -> Reflect.findField(c, fieldName));
        if (maybeField.isEmpty() && parent != null)
            return parent.findField(classType, fieldName);
        return maybeField;
    }

    public Optional<Method> findMethod(ClassType classType, String methodName, List<Type> parameterTypes) {
        if (classType.getClassName().equals(getCompilingClassName())) {
            return findLocalMethod(methodName, parameterTypes);
        }

        var maybeParameterClasses = classesFromTypes(parameterTypes);
        if (maybeParameterClasses.isEmpty())
            return Optional.empty();

        var maybeMethod = classFromType(classType)
                .flatMap(c -> Reflect.findMethod(c, methodName, maybeParameterClasses.get()));
        if (maybeMethod.isEmpty() && parent != null)
            return parent.findMethod(classType, methodName, parameterTypes);
        return maybeMethod;
    }

    public Optional<Method> findConstructor(ClassType classType, List<Type> parameterTypes) {
        if (classType.getClassName().equals(getCompilingClassName())) {
            if (parameterTypes.isEmpty())
                return Optional.of(new Method(classType, "<init>", List.of(), VoidType.Instance));
            return Optional.empty();
        }

        var maybeParameterClasses = classesFromTypes(parameterTypes);
        if (maybeParameterClasses.isEmpty())
            return Optional.empty();
        var maybeCtor = classFromType(classType)
                .flatMap(c -> Reflect.findConstructor(c, maybeParameterClasses.get()));
        if (maybeCtor.isEmpty() && parent != null)
            return parent.findConstructor(classType, parameterTypes);
        return maybeCtor;
    }
    private Optional<Method> findLocalMethod(String name, List<Type> parameterTypes) {
        if (level != Level.Class)
            return parent.findLocalMethod(name, parameterTypes);

        var maybeMethod = methods.stream()
                .filter(m -> m.name().equals(name))
                .filter(m -> m.parameterTypes().equals(parameterTypes))
                .findAny();

        return maybeMethod;
    }

    private Optional<Field> findLocalField(String name) {
        if (level != Level.Class)
            return parent.findLocalField(name);

        var maybeVar = findVariable(name);
        if (maybeVar.isPresent() && maybeVar.get().isField())
            return Optional.of(new Field(new ClassType(getCompilingClassName()), name, maybeVar.get().getType()));
        return Optional.empty();
    }
}
