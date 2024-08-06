package com.example.tdd.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @DisplayName("모든 규칙을 충족하는 경우, STRONG을 반환한다.")
    @ParameterizedTest(name = "\"{0}\" 은 STRONG 비밀번호입니다.")
    @ValueSource(strings = {"ab12!@AB", "abc1!Add"})
    void meetsAllCriteria_Then_Strong(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.STRONG);
    }

    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우, NORMAL을 반환한다.")
    @ParameterizedTest(name = "\"{0}\" 은 NORMAL 비밀번호입니다.")
    @ValueSource(strings = {"ab12!@A", "abc1!Ad"})
    void meetsOtherCriteria_except_for_Length_Then_Normal(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우, NORMAL을 반환한다.")
    @ParameterizedTest(name = "\"{0}\" 은 NORMAL 비밀번호입니다.")
    @ValueSource(strings = {"ab!@ABqwer"})
    void meetsOtherCriteria_except_for_Number_Then_Normal(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @DisplayName("값이 없는 경우, INVALID를 반환한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void nullOrEmpty_Then_Invalid(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.INVALID);
    }

    @DisplayName("대문자를 포함하지 않고 나머지 조건은 충족하는 경우, NORMAL을 반환한다.")
    @ParameterizedTest(name = "\"{0}\" 은 NORMAL 비밀번호입니다.")
    @ValueSource(strings = {"ab12!@df", "abc12!@dd"})
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(String givenPassword) {
        PasswordStrength result = meter.meter(givenPassword);
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }
}
