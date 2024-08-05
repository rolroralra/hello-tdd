package com.example.tdd;

import java.math.BigInteger;

public class Calculator {
    public static int plus(int a, int b) {
        BigInteger result = BigInteger.valueOf((long) a + b);

        return result.intValueExact();
    }
}
