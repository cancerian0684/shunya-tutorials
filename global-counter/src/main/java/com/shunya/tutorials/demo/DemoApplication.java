package com.shunya.tutorials.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ShunyaCounterService counterService) {
		return (args) -> {
			counterService.initialize(CounterType.FILE_SEQ);
			counterService.initialize(CounterType.ID_SEQ);
		};
	}
}
