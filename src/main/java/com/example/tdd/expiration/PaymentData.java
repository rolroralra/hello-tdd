package com.example.tdd.expiration;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record PaymentData(
    LocalDate firstBillingDate,
    LocalDate billingDate,
    int payAmount
) {

}
