package com.example.tdd.assertion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.tdd.assertion.AssertionTest.Person;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @see <a href=https://www.baeldung.com/java-assertj-soft-assertions>Soft Assertions (baeldung)</a>
 */
@Disabled("Test when assertion failed")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class SoftAssertionTest {

    @Nested
    @Order(1)
    class HardAssertionTest {
        @Test
        void test() {
            Person person = new Person("John", 25);

            assertThat(person.getName()).isEqualTo("James");
            assertThat(person.getAge()).isEqualTo(20);
        }
    }

    @Nested
    @Order(2)
    class AssertAllTest {
        @Test
        void test() {
            Person person = new Person("John", 25);

            assertAll(
                () -> assertThat(person.getName()).isEqualTo("James"),
                () -> assertThat(person.getAge()).isEqualTo(20)
            );
        }
    }

    @Nested
    @Order(3)
    class SoftAssertionsTest {
        @Test
        void test() {
            Person person = new Person("John", 25);

            SoftAssertions soft = new SoftAssertions();

            soft.assertThat(person.getName()).isEqualTo("James");
            soft.assertThat(person.getAge()).isEqualTo(20);
            soft.assertAll();
        }
    }

    @Nested
    @Order(4)
    @ExtendWith(SoftAssertionsExtension.class)
    class SoftAssertionExtensionTest {
        @Test
        void test(SoftAssertions soft) {  // 자동 주입
            Person person = new Person("John", 25);

            // assertAll() 자동 호출됨
            soft.assertThat(person.getName()).isEqualTo("James");
            soft.assertThat(person.getAge()).isEqualTo(20);
        }
    }
}
