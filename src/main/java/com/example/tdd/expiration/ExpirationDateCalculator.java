package com.example.tdd.expiration;

import com.google.common.base.Preconditions;
import java.time.LocalDate;

public class ExpirationDateCalculator {
    public LocalDate calculateExpireDate(LocalDate billingDate, int payAmount) {
        Preconditions.checkArgument(
            payAmount >= 10_000,
            "납부 금액은 10,000원 이상이어야 합니다."
        );

        return billingDate.plusMonths(1);
    }
}
