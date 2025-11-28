package com.example.tdd.junit.lifecycle;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class JunitLifecycleTest {

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class JunitPerClassTest {
        private final AtomicInteger counter = new AtomicInteger(0);

        @BeforeAll
        static void beforeAll() {
            System.out.println("Before all outer test [" + JunitPerClassTest.class.getSimpleName() + "]");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("After all outer test  [" + JunitPerClassTest.class.getSimpleName() + "]");
        }

        @BeforeEach
        void setUp() {
            int count = counter.incrementAndGet();

            System.out.println("Before each outer test, count = " + count + ", instance = " + this.hashCode());
        }

        @AfterEach
        void tearDown() {
            int count = counter.get();

            System.out.println("After each outer test,  count = " + count + ", instance = " + this.hashCode());
        }

        @Test
        void test1() {
            int count = counter.get();

            System.out.println("Test 1, count = " + count + ", instance = " + this.hashCode());
        }

        @Test
        void test2() {
            int count = counter.get();

            System.out.println("Test 2,  count = " + count + ", instance = " + this.hashCode());
        }
    }

    @Nested
    @TestInstance(Lifecycle.PER_METHOD)
    class JunitPerMethodTest {
        private final AtomicInteger counter = new AtomicInteger(0);

        @BeforeAll
        static void beforeAll() {
            System.out.println("Before all outer test [" + JunitPerMethodTest.class.getSimpleName() + "]");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("After all outer test [" + JunitPerMethodTest.class.getSimpleName() + "]");
        }

        @BeforeEach
        void setUp() {
            int count = counter.incrementAndGet();

            System.out.println("Before each outer test, count = " + count + ", instance = " + this.hashCode());
        }

        @AfterEach
        void tearDown() {
            int count = counter.get();

            System.out.println("After each outer test,  count = " + count + ", instance = " + this.hashCode());
        }

        @Test
        void test1() {
            int count = counter.get();

            System.out.println("Test 1, count = " + count + ", instance = " + this.hashCode());
        }

        @Test
        void test2() {
            int count = counter.get();

            System.out.println("Test 2,  count = " + count + ", instance = " + this.hashCode());
        }
    }
}
