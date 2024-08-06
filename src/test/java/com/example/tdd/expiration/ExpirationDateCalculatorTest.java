package com.example.tdd.expiration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ExpirationDateCalculatorTest {
    private final ExpirationDateCalculator expirationDateCalculator = new ExpirationDateCalculator();

    @ParameterizedTest
    @CsvSource(
        value = {
            "2021-01-21, 2021-02-21",
            "2021-02-28, 2021-03-28",
            "2021-03-31, 2021-04-30",
        }
    )
    @DisplayName("만원 납부하면 한 달 뒤에 만료일이 됨")
    void 만원_납부하면_한달_뒤가_만료일이_됨(LocalDate billingDate, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(billingDate,
            10_000);

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }

}