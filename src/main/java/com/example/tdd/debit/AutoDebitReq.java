package com.example.tdd.debit;

import lombok.Getter;

@Getter
public class AutoDebitReq {
    private final String userId;
    private final String cardNumber;

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }

}
