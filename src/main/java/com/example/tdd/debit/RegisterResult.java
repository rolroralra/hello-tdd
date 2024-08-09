package com.example.tdd.debit;

import lombok.Getter;

@Getter
public class RegisterResult {
    private final boolean success;
    private final CardValidity validity;

    public RegisterResult(boolean success, CardValidity validity) {
        this.success = success;
        this.validity = validity;
    }

    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult(false, validity);
    }

    public static RegisterResult success() {
        return new RegisterResult(true, CardValidity.VALID);
    }
}
