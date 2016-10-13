package com.dragon.study.spring.boot.mvc.controller;

import com.dragon.study.spring.boot.mvc.service.IHelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@RestController
@RequestMapping("/spring-boot")
@Slf4j
//@PreAuthorize("hasAuthority('user')")
public class HelloWorldController {

  private static final String WARN = "warn";

  @Autowired
  IHelloWorldService helloWorldService;

  @RequestMapping("/hello-world")
//  @PreAuthorize("hasAuthority('admin')")
  public String helloWorld() {
    String info = "info";
    log.debug("hello world controller, log level is {}", "debug");
    log.info("hello world controller, log level is {}", info);
    log.warn("hello world controller, log level is {}", WARN);
    log.error("hello world controller, log level is error");

    helloWorldService.helloWorld();
    return "Hello World";
  }


}
