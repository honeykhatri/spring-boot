package com.multithreading.executor.controller;

import com.multithreading.executor.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/executor")
public class TestController {

  private final AsyncService asyncService;

  public TestController(AsyncService asyncService) {
    this.asyncService = asyncService;
  }


  @GetMapping("/run-task")
  public String runTask(){
    asyncService.executeAsyncTask();
    return "Task Started!!";
  }
}
