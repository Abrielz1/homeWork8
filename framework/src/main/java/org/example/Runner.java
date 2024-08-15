package org.example;

import org.example.annotations.After;
import org.example.annotations.Before;
import org.example.annotations.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Runner extends org.junit.runner.Runner {

    public Runner() {
    }

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
                e.printStackTrace();
            }
        }

        System.out.printf("Tests passed: %d, Tests failed: %d%n", testPassedCount, testFailedCount);
    }

    @Override
    public Description getDescription() {
        return null;
    }

    @Override
    public void run(RunNotifier runNotifier) {

    }
}


