package com.example.tdd.debit.repository;

import com.example.tdd.debit.AutoDebitInfo;

public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}
