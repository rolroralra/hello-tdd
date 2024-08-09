package com.example.tdd.debit.repository;

import com.example.tdd.debit.AutoDebitInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private final Map<String, AutoDebitInfo> userIdToAutoDebitInfoMapping = new ConcurrentHashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        userIdToAutoDebitInfoMapping.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return userIdToAutoDebitInfoMapping.get(userId);
    }

}
