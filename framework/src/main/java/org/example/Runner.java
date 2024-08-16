package org.example;

import org.example.annotations.After;
import org.example.annotations.Before;
import org.example.annotations.Test;
import org.example.exceptions.AssertionException;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Runner {

    public static void run(String packageName) {
        int testPassedCount = 0;
        int testFailedCount = 0;
        final Reflections reflections = new Reflections(packageName, new MethodAnnotationsScanner());
        final Set<Method> methods = reflections.getMethodsAnnotatedWith(Before.class);
        methods.addAll(reflections.getMethodsAnnotatedWith(Test.class));
        methods.addAll(reflections.getMethodsAnnotatedWith(After.class));

        for (Method method : methods) {
            final String methodName = method.getName();
            try {
                method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance());
                testPassedCount++;
                System.out.println("Test " + methodName + " passed");
            }
            catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                if (e.getCause() instanceof AssertionException) {
                    testFailedCount++;
                    System.out.printf("Test on method: %s failed trace: %s%n", methodName, e.getCause().getMessage());
                }
            }
        }

        System.out.printf("Tests passed: %s, Tests failed: %s%n", testPassedCount, testFailedCount);
    }

    public static void main(String[] args) {
        run("org.example");
    }
}


