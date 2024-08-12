package com.example.tdd.user.password;

import lombok.Setter;

@Setter
public class StubWeakPasswordChecker implements WeakPasswordChecker{
    private boolean weak;

    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }

}
