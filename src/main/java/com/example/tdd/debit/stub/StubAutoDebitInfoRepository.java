package com.example.tdd.debit.stub;

import com.example.tdd.debit.AutoDebitInfo;
import com.example.tdd.debit.repository.AutoDebitInfoRepository;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {

    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}
