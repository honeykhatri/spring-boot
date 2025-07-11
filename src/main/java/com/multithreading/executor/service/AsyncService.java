package com.multithreading.executor.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
  @Async("fixedExecutor")
  public void executeAsyncTask(){
    System.out.println("Executing task in thread. " + Thread.currentThread().getName());
    try{
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("Task Completed.");
  }
}
