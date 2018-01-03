package com.shunya.tutorials.demo;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class FailSafeCounterService {
    final ShunyaCounterService shunyaCounterService;

    @Autowired
    public FailSafeCounterService(ShunyaCounterService shunyaCounterService) {
        this.shunyaCounterService = shunyaCounterService;
    }

    @Retryable(maxAttempts = 10, value = {StaleStateException.class, ObjectOptimisticLockingFailureException.class}, backoff = @Backoff(value = 1000, multiplier = 2))
    public long incrementAndGet() {
        try {
            return shunyaCounterService.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Recover
    public void recover(StaleStateException exception) {
        exception.printStackTrace();
    }
}
