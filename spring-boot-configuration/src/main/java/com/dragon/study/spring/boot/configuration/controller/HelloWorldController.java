package com.dragon.study.spring.boot.configuration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@RestController
@RequestMapping("/spring-boot")
@Slf4j
public class HelloWorldController {

  private static final String WARN = "warn";

  @RequestMapping("/hello-world")
  public String helloWorld() {
    String info = "info";
    log.debug("hello world controller, log level is {}", "debug");
    log.info("hello world controller, log level is {}", info);
    log.warn("hello world controller, log level is {}", WARN);
    log.error("hello world controller, log level is error");

    return "Hello World";
  }


}
