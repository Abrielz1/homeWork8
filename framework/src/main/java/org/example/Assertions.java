package org.example;

import org.example.exceptions.AssertionException;

public class Assertions {

    public static void assertEquals(Object expected, Object actual, String message) throws AssertionException {
        if (expected.equals(actual)) {
            throw new AssertionException("objects are not same", "Expected "
                    +  expected + ", but was " + actual + " not equal" + message);
        }
    }

    public static void assertNotEquals(Object expected, Object actual, String message) throws AssertionException {
        if (!expected.equals(actual)) {
            throw new AssertionException("objects are not same", "Expected "
                    +  expected + ", but was " + actual + " not equal" + message);
        }
    }
}
