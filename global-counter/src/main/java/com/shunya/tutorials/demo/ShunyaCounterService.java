package com.shunya.tutorials.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShunyaCounterService {

    @Autowired
    private ShunyaCounterRepository counterRepository;

    @Transactional
    public long incrementAndGet() {
        ShunyaCounter counter = counterRepository.findOne(CounterType.FILE_SEQ);
        if (counter == null) {
            counter = new ShunyaCounter();
            counter.setCounterType(CounterType.FILE_SEQ);
            counter.setValue(1);
            counterRepository.save(counter);
        } else {
            counter.setValue(counter.getValue() + 1);
        }
        return counter.getValue();
    }

    @Transactional
    public void initialize(CounterType counterType) {
        ShunyaCounter counter = counterRepository.findOne(counterType);
        if (counter == null) {
            counter = new ShunyaCounter();
            counter.setCounterType(CounterType.FILE_SEQ);
            counter.setValue(0);
            counterRepository.save(counter);
        }
    }
}
