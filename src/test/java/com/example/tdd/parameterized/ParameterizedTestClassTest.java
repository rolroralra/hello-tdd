package com.example.tdd.parameterized;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestClassTest {

    @DisplayName("@ValueSource 테스트")
    @Nested
    class ValueSourceTest {
        @ParameterizedTest(name = "\"{0}\" is a palindrome")
        @DisplayName("isPalindrome() 메서드가 회문 파라미터를 넘긴 경우, true를 반환한다.")
        @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
        void palindromes(String candidate) {
            assertTrue(isPalindrome(candidate));
        }

        private boolean isPalindrome(String candidate) {
            return candidate.contentEquals(new StringBuilder(candidate).reverse());
        }
    }

    @DisplayName("@EnumSource 테스트")
    @Nested
    class EnumSourceTest {
        enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }

        @ParameterizedTest(name = "{0} is not null")
        @EnumSource(Topping.class)
        void testEnumSource(Topping topping) {
            assertNotNull(topping);
        }
    }

    @DisplayName("@NullAndEmptySource 테스트")
    @Nested
    class NullAndEmptySourceTest {

        @ParameterizedTest(name = "\"{0}\" is null or empty")
        @NullAndEmptySource
        void nullAndEmptyStrings(String text) {
            assertThat(text).isNullOrEmpty();
        }
    }

    @DisplayName("@CsvSource 테스트")
    @Nested
    class CsvSourceTest {

        @DisplayName("toUpperCase() 메서드가 소문자를 대문자로 변환한다.")
        @ParameterizedTest(name = "\"{0}\".toUpperCase() will return \"{1}\"")
        @CsvSource({
            "test, TEST",
            "tEst, TEST",
            "Java, JAVA"
        })
        void toUpperCase_ShouldGenerateTheExpectedUpperCaseValue(String input, String expected) {
            assertEquals(expected, input.toUpperCase());
        }
    }

    @DisplayName("@CsvFileSource 테스트")
    @Nested
    class CsvFileSourceTest {

        @DisplayName("toUpperCase() 메서드가 소문자를 대문자로 변환한다.")
        @ParameterizedTest(name = "\"{0}\".toUpperCase() will return \"{1}\"")
        @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
        void toUpperCase_ShouldGenerateTheExpectedUpperCaseValue(String input, String expected) {
            assertEquals(expected, input.toUpperCase());
        }
    }

    @DisplayName("@MethodSource 테스트")
    @Nested
    class MethodSourceTest {

        @DisplayName("String::length 메서드가 문자열의 길이를 반환한다.")
        @ParameterizedTest(name = "length of \"{0}\" is {1}")
        @MethodSource("stringAndLengthProvider")
        void testWithStringProvider(String argument, long length) {
            assertEquals(length, argument.length());
        }

        static Stream<Arguments> stringAndLengthProvider() {
            return Stream.of(
                Arguments.of("apple", 5),
                Arguments.of("banana", 6)
            );
        }
    }

    @DisplayName("@ArgumentsSource 테스트")
    @Nested
    class ArgumentsSourceTest {
        @DisplayName("CustomArgumentsProvider를 사용하여 테스트를 실행한다.")
        @ParameterizedTest(name = "argument \"{0}\"")
        @ArgumentsSource(CustomArgumentsProvider.class)
        void testWithArgumentsSource(String argument) {
            assertNotNull(argument);
        }

        static class CustomArgumentsProvider implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
                return Stream.of("apple", "banana").map(Arguments::of);
            }
        }
    }
}
