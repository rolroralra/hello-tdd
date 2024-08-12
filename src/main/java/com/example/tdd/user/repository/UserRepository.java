package com.example.tdd.user.repository;

import com.example.tdd.user.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
