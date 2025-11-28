package com.example.tdd.assertion;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class AssertionTest {

    static class Person {
        final String name;
        final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() { return name; }
        int getAge() { return age; }
    }

    @Test
    void objectAssertion_example() {
        Person p = new Person("Alice", 30);

        assertThat(p)
            .isNotNull()
            .extracting(Person::getName, Person::getAge)
            .containsExactly("Alice", 30);
    }

    @Test
    void numberAssertion_example() {
        int result = 42;

        assertThat(result)
            .isGreaterThan(40)
            .isLessThanOrEqualTo(50)
            .isBetween(40, 50);
    }

    @Test
    void stringAssertion_example() {
        String text = "  hello AssertJ  ";

        assertThat(text)
            .isNotBlank()
            .contains("AssertJ")
            .startsWith("  hello")
            .endsWith("  ");
    }

    @Test
    void listAssertion_example() {
        List<String> list = List.of("apple", "banana", "cherry");

        assertThat(list)
            .hasSize(3)
            .contains("banana")
            .doesNotContain("durian")
            .containsExactly("apple", "banana", "cherry");
    }

    @Test
    void exceptionAssertion_example() {
        assertThatThrownBy(() -> { throw new IllegalArgumentException("invalid"); })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("invalid");
    }

    @Test
    void noExceptionAssertion_example() {
        assertThatNoException().isThrownBy(() -> {
            System.out.println("There is no exception");
        });
    }
}
