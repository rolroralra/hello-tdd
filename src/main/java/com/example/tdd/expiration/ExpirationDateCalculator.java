package com.example.tdd.expiration;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.Optional;

public class ExpirationDateCalculator {

    public static final int PAYMENT_PRICE = 10_000;
    public static final int ONE_MONTH = 1;

    public LocalDate calculateExpireDate(PaymentData paymentData) {
        Preconditions.checkArgument(
            paymentData.payAmount() >= PAYMENT_PRICE,
            "납부 금액은 %s원 이상이어야 합니다.", PAYMENT_PRICE
        );

        LocalDate expirationDate = paymentData.billingDate().plusMonths(ONE_MONTH);

        return Optional.ofNullable(paymentData.firstBillingDate())
            .map(firstBillingDate -> expirationDate.withDayOfMonth(firstBillingDate.getDayOfMonth()))
            .orElse(expirationDate);
    }
}
