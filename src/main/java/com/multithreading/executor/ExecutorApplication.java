package com.multithreading.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExecutorApplication.class, args);
	}

}
