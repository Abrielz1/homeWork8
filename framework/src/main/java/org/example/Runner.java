package org.example;

import org.example.annotations.After;
import org.example.annotations.Before;
import org.example.annotations.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Runner {

     public static void main(String[] args) {
         Runner.run("org.example");
    }

    public static void run(String packageName) {
      List<Class<?>> classes = Runner.getAllClassesFrom(packageName);
        for (Class<?> aClass : classes) {
            for (Method method : aClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)){

                }

                if (method.isAnnotationPresent(Before.class)){

                }

                if (method.isAnnotationPresent(After.class)){

                }
            }
        }
    }

    private static List<Class<?>> getAllClassesFrom(String packageName) {

        return new Reflections(packageName, new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
