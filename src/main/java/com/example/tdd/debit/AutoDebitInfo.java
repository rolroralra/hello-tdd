package com.example.tdd.debit;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AutoDebitInfo {
    private final String userId;
    private String cardNumber;
    private final LocalDateTime registTime;
    private LocalDateTime updateTime;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime registTime) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registTime = registTime;
        this.updateTime = registTime;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        this.updateTime = LocalDateTime.now();
    }
}
