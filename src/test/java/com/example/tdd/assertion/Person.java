package com.example.tdd.assertion;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String fullName;
    private int age;
    private List<String> nickNames;

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
        this.nickNames = new ArrayList<>();
    }

    public void addNickname(String nickname) {
        nickNames.add(nickname);
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public List<String> getNickNames() {
        return nickNames;
    }
}
