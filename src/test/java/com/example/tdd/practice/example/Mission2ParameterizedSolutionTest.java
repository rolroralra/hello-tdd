package com.example.tdd.practice.example;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.tdd.calculator.Calculator;
import com.example.tdd.password.PasswordStrength;
import com.example.tdd.password.PasswordStrengthMeter;
import com.example.tdd.strings.PalindromeValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * [미션 2 정답] @ParameterizedTest
 *
 * @see com.example.tdd.practice.Mission2ParameterizedTest
 */
@DisplayName("[미션 2 정답] ParameterizedTest")
class Mission2ParameterizedSolutionTest {

    @ParameterizedTest(name = "\"{0}\" 는 회문이다")
    @ValueSource(strings = {"racecar", "level", "Was it a car or a cat I saw"})
    @DisplayName("2-1. 회문이면 true")
    void palindrome(String input) {
        assertThat(PalindromeValidator.isPalindrome(input)).isTrue();
    }

    // 참고: isPalindrome("") 는 true 를 반환하므로 @NullAndEmptySource 를 쓰면 실패합니다.
    @ParameterizedTest
    @NullSource
    @DisplayName("2-2. null 이면 false")
    void nullIsNotPalindrome(String input) {
        assertThat(PalindromeValidator.isPalindrome(input)).isFalse();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({"1, 2, 3", "0, 0, 0", "-1, 1, 0"})
    @DisplayName("2-3. 덧셈")
    void add(int a, int b, int expected) {
        assertThat(new Calculator().add(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("2-4. CSV 파일로 대문자 변환 검증")
    void toUpperCase(String given, String expected) {
        assertThat(given.toUpperCase()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @MethodSource("passwordProvider")
    @DisplayName("2-5. 암호 강도 측정")
    void passwordStrength(String password, PasswordStrength expected) {
        assertThat(new PasswordStrengthMeter().meter(password)).isEqualTo(expected);
    }

    static Stream<Arguments> passwordProvider() {
        return Stream.of(
            Arguments.of("ab12!@AB", PasswordStrength.STRONG),   // 길이 O, 숫자 O, 대문자 O
            Arguments.of("ab12!@ab", PasswordStrength.NORMAL),   // 길이 O, 숫자 O, 대문자 X
            Arguments.of("abcdefgh", PasswordStrength.WEAK),     // 길이 O, 숫자 X, 대문자 X
            Arguments.of("abc", PasswordStrength.WEAK),          // 모두 X
            Arguments.of(null, PasswordStrength.INVALID),
            Arguments.of(" ", PasswordStrength.INVALID)
        );
    }
}
