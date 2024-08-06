package com.example.tdd.expiration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ExpirationDateCalculatorTest {
    private final ExpirationDateCalculator expirationDateCalculator = new ExpirationDateCalculator();

    @ParameterizedTest(name = "{0} 납부하면 {1} 만료일이 됨")
    @CsvSource(
        value = {
            "2021-01-21, 2021-02-21",
            "2021-02-28, 2021-03-28",
        }
    )
    @DisplayName("만원 납부하면 한 달 뒤에 만료일이 됨 (납부일과 만료일이 같은 날인 경우)")
    void 만원_납부하면_한달_뒤가_만료일이_됨(LocalDate billingDate, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(
            new PaymentData(billingDate, 10_000));

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }

    @ParameterizedTest(name = "{0} 납부하면 {1} 만료일이 됨")
    @CsvSource(
        value = {
            "2021-03-31, 2021-04-30",
            "2021-01-31, 2021-02-28",
            "2021-05-31, 2021-06-30",
            "2020-01-31, 2020-02-29",
        }
    )
    @DisplayName("만원 납부하면 한 달 뒤에 만료일이 됨 (납부일과 만료일이 다른 날인 경우)")
    void 납부일과_만료일이_다른_날인_경우_만원_납부하면_한달_뒤가_만료일이_됨(LocalDate billingDate, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(
            new PaymentData(billingDate, 10_000));

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }
}