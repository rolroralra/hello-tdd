package com.example.tdd.tag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class TagAnnotationTest {
    @Test
    @Tag("unitTest")
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface UnitTest {

    }

    @Test
    @Tag("integrationTest")
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface IntegrationTest {

    }

    @Test
    @Tag("acceptanceTest")
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface AcceptanceTest {

    }

    @UnitTest
    @DisplayName("단위 테스트")
    void unitTest() {
        Assertions.assertTrue(true);
    }

    @IntegrationTest
    @DisplayName("통합 테스트")
    void integrationTest() {
        Assertions.assertTrue(true);
    }

    @AcceptanceTest
    @DisplayName("인수 테스트")
    void acceptanceTest() {
        Assertions.assertTrue(true);
    }
}
