package com.example.tdd.strings;

public class PalindromeValidator {
    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }

        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        int length = cleaned.length();
        int forward = 0;
        int backward = length - 1;

        while (backward > forward) {
            char forwardChar = cleaned.charAt(forward++);
            char backwardChar = cleaned.charAt(backward--);

            if (forwardChar != backwardChar) {
                return false;
            }
        }

        return true;
    }
}
