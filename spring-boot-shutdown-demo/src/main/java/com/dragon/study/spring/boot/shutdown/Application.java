package com.dragon.study.spring.boot.shutdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 2017/2/17.
 */
@SpringBootApplication
@RestController
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping("/pause")
  public String pause() throws InterruptedException {
    Thread.sleep(10000);
    return "Pause complete";
  }
}
