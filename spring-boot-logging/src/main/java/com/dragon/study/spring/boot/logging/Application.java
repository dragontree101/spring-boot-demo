package com.dragon.study.spring.boot.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/9/30.
 */
@SpringBootApplication
@Slf4j
public class Application {

  @PostConstruct
  public void logSomething() {
    log.debug("Sample Debug Message");
    log.info("Sample Info Message");
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args).close();
  }

}
