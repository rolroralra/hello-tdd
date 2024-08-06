package com.example.tdd.password.criteria;

public class PasswordLengthCriteria implements PasswordCriteria {
    @Override
    public boolean verify(String password) {
        return password.length() >= 8;
    }
}
