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

  @Nested
    class InnerTest {
        @BeforeAll
        static void beforeAll() {
          System.out.println("Before all inner test");
        }

        @AfterAll
        static void afterAll() {
          System.out.println("After all inner test");
        }

        @BeforeEach
        void setUp() {
          System.out.println("Before each inner test");
        }

        @AfterEach
        void tearDown() {
          System.out.println("After each inner test");
        }

        @Test
        void innerTest1() {
          System.out.println("Inner Test 1");
        }

        @Test
        void innerTest2() {
          System.out.println("Inner Test 2");
        }
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
  void outerTest1() {
    System.out.println("Outer Test 1");
  }

  @Test
  void outerTest2() {
    System.out.println("Outer Test 2");
  }
}
