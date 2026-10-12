package com.example.tdd.practice.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.abort;

import com.example.tdd.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * [미션 1] JUnit5 기본기
 *
 * 대상 클래스: {@link Calculator}
 *
 * 진행 방법
 * 1. 각 테스트의 abort(...) 을 지우고 TODO 를 채운다.
 * 2. 테스트를 실행해서 초록불을 확인한다.
 */
@DisplayName("[미션 1] JUnit5 기본")
class Mission1JUnit5Test {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // TODO: 매 테스트마다 새 Calculator 를 생성해서 필드에 할당하세요.
        // 힌트: @BeforeEach 는 각 @Test 실행 직전에 호출됩니다.
    }

    @Test
    @DisplayName("1-1. 2 + 3 = 5")
    void add() {
        // TODO: calculator.add(2, 3) 결과가 5 인지 검증하세요.
        // 힌트: assertEquals(expected, actual)  또는  assertThat(actual).isEqualTo(expected)
        abort("미션 1-1 을 완성하세요");
    }

    @Test
    @DisplayName("1-2. 사칙연산 결과를 한 번에 검증한다")
    void allOperations() {
        // TODO: 10, 2 로 add / subtract / multiply / divide 결과를 한 번에 검증하세요.
        //       (12, 8, 20, 5)
        // 힌트: assertAll(() -> assertEquals(...), () -> assertEquals(...), ...)
        //       assertAll 은 중간에 실패해도 나머지 검증을 모두 실행합니다.
        abort("미션 1-2 를 완성하세요");
    }

    @Test
    @DisplayName("1-3. 0 으로 나누면 ArithmeticException 이 발생한다")
    void divideByZero() {
        // TODO: calculator.divide(10, 0) 호출 시 ArithmeticException 이 발생하는지 검증하세요.
        // 힌트: assertThrows(ArithmeticException.class, () -> ...)
        //       또는 assertThatThrownBy(() -> ...).isInstanceOf(ArithmeticException.class)
        abort("미션 1-3 을 완성하세요");
    }

    @Nested
    @DisplayName("1-4. static 메서드 Calculator.plus()")
    class PlusTest {

        @Test
        @DisplayName("int 범위를 넘으면 ArithmeticException 이 발생한다")
        void overflow() {
            // TODO: Calculator.plus(Integer.MAX_VALUE, 1) 이 ArithmeticException 을 던지는지 검증하세요.
            // 힌트: plus() 는 내부에서 BigInteger.intValueExact() 를 사용합니다.
            //       @Nested 클래스로 관련 테스트를 그룹핑할 수 있습니다.
            abort("미션 1-4 를 완성하세요");
        }
    }
}
