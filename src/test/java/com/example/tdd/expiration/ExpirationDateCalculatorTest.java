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
            PaymentData.builder()
                .billingDate(billingDate)
                .payAmount(10_000)
                .build()
           );

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
            PaymentData.builder()
                .billingDate(billingDate)
                .payAmount(10_000)
                .build());

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }

    @ParameterizedTest(name = "첫 납부일이 {0}이고 {1} 납부하면, {2} 만료일이 됨 ")
    @CsvSource(
        value = {
            "2019-01-31, 2019-02-28, 2019-03-31",
            "2019-01-30, 2019-02-28, 2019-03-30",
        }
    )
    @DisplayName("첫 납부일과 만료일 일자가 다를때 만원 납부하면 첫 납부일 기준으로 다음 만료일이 계산됨")
    void 첫_납부일과_만료일_일자가_다를때_만원_납부하면_첫_납부일_기준으로_다음_만료일이_계산됨(LocalDate firstBillingDate, LocalDate billingDate, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(
            PaymentData.builder()
                .firstBillingDate(firstBillingDate)
                .billingDate(billingDate)
                .payAmount(10_000)
                .build());

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }

    @ParameterizedTest(name = "납부일이 {0}이고 {1}원 납부하면, {2} 만료일이 됨 ")
    @CsvSource(
        value = {
            "2021-01-21, 20000, 2021-03-21",
            "2021-02-28, 30000, 2021-05-28",
            "2021-03-31, 20000, 2021-05-31",
            "2021-01-31, 30000, 2021-04-30",
            "2021-05-31, 20000, 2021-07-31",
            "2020-01-31, 30000, 2020-04-30",
        }
    )
    @DisplayName("2만원 이상 납부하면 비례해서 만료일이 계산된다.")
    void 이만원_이상_납부하면_비례해서_만료일이_계산됨(LocalDate billingDate, int payAmount, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(
            PaymentData.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build());

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }

    @ParameterizedTest(name = "첫 납부일이 {0}이고 {1}에 {2}원 납부하면, {3} 만료일이 됨 ")
    @CsvSource(
        value = {
            "2019-01-31, 2019-02-28, 20000, 2019-04-30",
            "2019-01-31, 2019-02-28, 30000, 2019-05-31",
        }
    )
    @DisplayName("첫 납부일과 만료일 일자가 다를때 만원이상 납부하면 첫 납부일 기준으로 납부액과 비례해서 다음 만료일이 계산됨")
    void 첫_납부일과_만료일_일자가_다를때_만원이상_납부하면_첫_납부일_기준으로_납부액에_비례해서_다음_만료일이_계산됨(
        LocalDate firstBillingDate, LocalDate billingDate, int payAmount,
        LocalDate expectedExpirationDate
    ) {
        LocalDate expirationDate = expirationDateCalculator.calculateExpireDate(
            PaymentData.builder()
                .firstBillingDate(firstBillingDate)
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build());

        assertThat(expirationDate).isEqualTo(expectedExpirationDate);
    }
}