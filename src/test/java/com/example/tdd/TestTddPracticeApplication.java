package com.example.tdd;

import org.springframework.boot.SpringApplication;

public class TestTddPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.from(TddPracticeApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
