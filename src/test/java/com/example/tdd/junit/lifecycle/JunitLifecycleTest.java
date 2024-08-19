package com.example.tdd.junit.lifecycle;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JunitLifecycleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all outer test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all outer test");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each outer test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each outer test");
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }
}
