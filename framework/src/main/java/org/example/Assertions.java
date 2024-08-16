package org.example;

import org.example.exceptions.AssertionException;

public class Assertions {

    public static void assertEquals(Object expected, Object actual, String message) throws AssertionException {
        if (expected.equals(actual)) {
            System.out.println(message);
        } else {
            throw new AssertionException("objects are not same", "Expected "
                    +  expected + ", but was " + actual + " not equal" + message);
        }
    }

    public static void assertNotEquals(Object expected, Object actual, String message) throws AssertionException {
        if (!expected.equals(actual)) {
            System.out.println(message);
        } else {
            throw new AssertionException("objects are same", "Expected "
                    +  expected + ", but was " + actual + " equal" + message);
        }
    }
}
