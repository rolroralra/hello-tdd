package com.example.tdd.user.repository;

import com.example.tdd.user.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return storage.get(id);
    }
}
