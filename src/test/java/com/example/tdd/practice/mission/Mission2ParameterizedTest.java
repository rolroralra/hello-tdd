package com.example.tdd.practice.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.abort;

import com.example.tdd.calculator.Calculator;
import com.example.tdd.password.PasswordStrength;
import com.example.tdd.password.PasswordStrengthMeter;
import com.example.tdd.strings.PalindromeValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

/**
 * [미션 2] @ParameterizedTest
 *
 * 대상 클래스: {@link PalindromeValidator}, {@link Calculator}, {@link PasswordStrengthMeter}
 *
 * 진행 방법
 * 1. 각 메서드에 알맞은 @ParameterizedTest + Source 어노테이션을 붙인다.
 *    (지금은 @ParameterizedTest 가 없어서 실행되지 않습니다)
 * 2. abort(...) 을 지우고 검증 코드를 작성한다.
 */
@DisplayName("[미션 2] ParameterizedTest")
class Mission2ParameterizedTest {

    // TODO: @ParameterizedTest(name = "\"{0}\" 는 회문이다") 와
    //       @ValueSource(strings = {"racecar", "level", "Was it a car or a cat I saw"}) 를 붙이세요.
    // 힌트: 공백과 대소문자는 무시됩니다.
    @DisplayName("2-1. 회문이면 true")
    void palindrome(String input) {
        // TODO: PalindromeValidator.isPalindrome(input) 이 true 인지 검증하세요.
        abort("미션 2-1 을 완성하세요");
    }

    // TODO: @ParameterizedTest 와 @NullSource 를 붙이세요.
    // 힌트: @NullSource 는 파라미터로 null 하나를 넘겨줍니다.
    //       (@NullAndEmptySource 도 있지만, isPalindrome("") 의 결과는 무엇일까요? 직접 확인해보세요.)
    @DisplayName("2-2. null 이면 false")
    void nullIsNotPalindrome(String input) {
        // TODO: PalindromeValidator.isPalindrome(input) 이 false 인지 검증하세요.
        abort("미션 2-2 를 완성하세요");
    }

    // TODO: @ParameterizedTest(name = "{0} + {1} = {2}") 와 @CsvSource 를 붙이세요.
    // 힌트: @CsvSource({"1, 2, 3", "0, 0, 0", "-1, 1, 0"})
    //       각 줄이 한 번의 테스트, 콤마로 구분된 값이 순서대로 파라미터에 바인딩됩니다.
    @DisplayName("2-3. 덧셈")
    void add(int a, int b, int expected) {
        // TODO: new Calculator().add(a, b) 가 expected 인지 검증하세요.
        abort("미션 2-3 을 완성하세요");
    }

    // TODO: @ParameterizedTest 와 @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1) 를 붙이세요.
    // 힌트: src/test/resources/test-data.csv 파일을 열어 컬럼 구성을 확인해보세요.
    //       numLinesToSkip = 1 은 헤더 줄을 건너뜁니다.
    @DisplayName("2-4. CSV 파일로 대문자 변환 검증")
    void toUpperCase(String given, String expected) {
        // TODO: given.toUpperCase() 가 expected 인지 검증하세요.
        abort("미션 2-4 를 완성하세요");
    }

    // TODO: @ParameterizedTest 와 @MethodSource("passwordProvider") 를 붙이세요.
    // 힌트: @MethodSource 는 같은 클래스의 static 메서드가 반환하는 Stream<Arguments> 를 사용합니다.
    @DisplayName("2-5. 암호 강도 측정")
    void passwordStrength(String password, PasswordStrength expected) {
        // TODO: new PasswordStrengthMeter().meter(password) 가 expected 인지 검증하세요.
        abort("미션 2-5 를 완성하세요");
    }

    static Stream<Arguments> passwordProvider() {
        // TODO: (암호, 기대 강도) 쌍을 채워 넣으세요.
        // 규칙: 길이 8 이상 / 숫자 포함 / 대문자 포함
        //   - 3개 모두 충족 -> STRONG
        //   - 2개 충족     -> NORMAL
        //   - 1개 이하 충족 -> WEAK
        //   - null 또는 공백 -> INVALID
        // 예시: Arguments.of("ab12!@AB", PasswordStrength.STRONG)
        return Stream.of(
            // Arguments.of("ab12!@AB", PasswordStrength.STRONG),
            // Arguments.of(..., PasswordStrength.NORMAL),
            // Arguments.of(..., PasswordStrength.WEAK)
        );
    }
}
