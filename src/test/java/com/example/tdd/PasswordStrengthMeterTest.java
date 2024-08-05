package com.example.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @DisplayName("모든 규칙을 충족하는 경우, 강함을 반환한다.")
    @ParameterizedTest(name = "\"{0}\" 은 STRONG 비밀번호입니다.")
    @ValueSource(strings = {"ab12!@AB", "abc1!Add"})
    void meetsAllCriteria_Then_Strong(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.STRONG);
    }
}
