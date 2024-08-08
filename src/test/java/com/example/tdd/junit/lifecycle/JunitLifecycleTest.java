package com.example.tdd.junit.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JunitLifecycleTest {
  @BeforeAll
  static void beforeAll() {
    System.out.println("Before all");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("After all");
  }

  @BeforeEach
  void setUp() {
    System.out.println("Before each");
  }

  @AfterEach
  void tearDown() {
    System.out.println("After each");
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
