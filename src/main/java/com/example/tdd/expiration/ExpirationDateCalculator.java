package com.example.tdd.expiration;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.Optional;

public class ExpirationDateCalculator {
    public LocalDate calculateExpireDate(PaymentData paymentData) {
        Preconditions.checkArgument(
            paymentData.payAmount() >= 10_000,
            "납부 금액은 10,000원 이상이어야 합니다."
        );

        LocalDate expirationDate = paymentData.billingDate().plusMonths(1);

        return Optional.ofNullable(paymentData.firstBillingDate())
            .map(firstBillingDate -> expirationDate.withDayOfMonth(firstBillingDate.getDayOfMonth()))
            .orElse(expirationDate);
    }
}
