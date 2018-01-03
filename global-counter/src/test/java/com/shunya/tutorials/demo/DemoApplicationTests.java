package com.shunya.tutorials.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private FailSafeCounterService failSafeCounterService;

	@Test
	public void contextLoads() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=1; i<=50; i++) {
            executorService.execute(() -> {
                long l = failSafeCounterService.incrementAndGet();
                System.out.println("l = " + l);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        long lastValue = failSafeCounterService.incrementAndGet();
        assertThat(lastValue, equalTo(51L));
    }

}
