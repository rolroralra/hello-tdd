package com.example.tdd.password.criteria;

public class PasswordUppercaseCriteria implements PasswordCriteria{
    @Override
    public boolean verify(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }
}
