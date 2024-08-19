package com.example.tdd.calculator;

import java.math.BigInteger;

public class Calculator {
    public static int plus(int a, int b) {
        BigInteger result = BigInteger.valueOf((long) a + b);

        return result.intValueExact();
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) {
        return a / b;
    }
}
