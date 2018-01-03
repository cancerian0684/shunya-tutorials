package com.shunya.tutorials.demo;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface ShunyaCounterRepository extends CrudRepository<ShunyaCounter, CounterType> {

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    ShunyaCounter findOne(CounterType s);
}
