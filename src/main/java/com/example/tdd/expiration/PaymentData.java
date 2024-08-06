package com.example.tdd.expiration;

import java.time.LocalDate;

public record PaymentData(
    LocalDate billingDate,
    int payAmount
) {

}
