package com.example.tdd.practice.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.tdd.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * [미션 1 정답] JUnit5 기본기
 *
 * @see com.example.tdd.practice.Mission1JUnit5Test
 */
@DisplayName("[미션 1 정답] JUnit5 기본")
class Mission1JUnit5SolutionTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("1-1. 2 + 3 = 5")
    void add() {
        // JUnit 스타일
        assertEquals(5, calculator.add(2, 3));

        // AssertJ 스타일
        assertThat(calculator.add(2, 3)).isEqualTo(5);
    }

    @Test
    @DisplayName("1-2. 사칙연산 결과를 한 번에 검증한다")
    void allOperations() {
        assertAll(
            () -> assertEquals(12, calculator.add(10, 2)),
            () -> assertEquals(8, calculator.subtract(10, 2)),
            () -> assertEquals(20, calculator.multiply(10, 2)),
            () -> assertEquals(5, calculator.divide(10, 2))
        );
    }

    @Test
    @DisplayName("1-3. 0 으로 나누면 ArithmeticException 이 발생한다")
    void divideByZero() {
        // JUnit 스타일
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));

        // AssertJ 스타일
        assertThatThrownBy(() -> calculator.divide(10, 0))
            .isInstanceOf(ArithmeticException.class);
    }

    @Nested
    @DisplayName("1-4. static 메서드 Calculator.plus()")
    class PlusTest {

        @Test
        @DisplayName("int 범위를 넘으면 ArithmeticException 이 발생한다")
        void overflow() {
            assertThatThrownBy(() -> Calculator.plus(Integer.MAX_VALUE, 1))
                .isInstanceOf(ArithmeticException.class);
        }
    }
}
