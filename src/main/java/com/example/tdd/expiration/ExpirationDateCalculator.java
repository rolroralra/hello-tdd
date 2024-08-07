package com.example.tdd.expiration;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public class ExpirationDateCalculator {

    public static final int PAYMENT_PRICE = 10_000;
    public static final int YEARLY_PAYMENT_PRICE = 100_000;

    public LocalDate calculateExpireDate(PaymentData paymentData) {
        Preconditions.checkArgument(
            paymentData.payAmount() >= PAYMENT_PRICE,
            "납부 금액은 %s원 이상이어야 합니다.", PAYMENT_PRICE
        );

        int addedMonth = calculateSubscriptionMonth(paymentData);

        LocalDate expirationDate = paymentData.billingDate().plusMonths(addedMonth);

        return Optional.ofNullable(paymentData.firstBillingDate())
            .map(firstBillingDate -> getLocalDateWithDayOfMonth(expirationDate, firstBillingDate.getDayOfMonth()))
            .orElse(expirationDate);
    }

    private static int calculateSubscriptionMonth(PaymentData paymentData) {
        if (paymentData.payAmount() == YEARLY_PAYMENT_PRICE) {
            return Month.values().length;
        }

        return paymentData.payAmount() / PAYMENT_PRICE;
    }

    private LocalDate getLocalDateWithDayOfMonth(LocalDate localDate, int dayOfMonth) {
        if (dayOfMonth > localDate.lengthOfMonth()) {
            return localDate.withDayOfMonth(localDate.lengthOfMonth());
        }

        return localDate.withDayOfMonth(dayOfMonth);
    }
}
