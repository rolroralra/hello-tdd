package com.example.tdd.password.criteria;

public class PasswordNumberCriteria implements PasswordCriteria {
    @Override
    public boolean verify(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }
}
