package com.multithreading.executor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AsyncConfig {
  @Bean("fixedExecutor")
  public Executor taskExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("AsyncFixedTaskExecutor - ");
    executor.initialize();
    return executor;
  }

  @Bean("cacheExecutor")
  public Executor cacheExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(0);
    executor.setMaxPoolSize(Integer.MAX_VALUE);
    executor.setQueueCapacity(0);
    executor.setThreadNamePrefix("Cached - ");
    executor.initialize();
    return executor;
  }

  @Bean("scheduledExecutor")
  public ScheduledExecutorService scheduledExecutorService(){
    return Executors.newScheduledThreadPool(5);
  }
}
