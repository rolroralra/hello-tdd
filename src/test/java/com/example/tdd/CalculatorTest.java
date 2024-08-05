package com.example.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CalculatorTest {

    @DisplayName("두 정수를 더한 결과를 반환한다.")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(value = {"1, 2, 3", "4, 1, 5", "-1, 10, 9"})
    void plus(int a, int b, int expected) {
        int result = Calculator.plus(a, b);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Integer Overflow 발생시 예외를 던진다.")
    @Test
    void plusWhenOverflowThenThrowException() {
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> Calculator.plus(Integer.MAX_VALUE, 1));
    }

}
