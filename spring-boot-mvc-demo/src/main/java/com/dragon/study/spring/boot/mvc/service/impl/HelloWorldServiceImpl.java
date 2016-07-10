package com.dragon.study.spring.boot.mvc.service.impl;

import com.dragon.study.spring.boot.mvc.service.IHelloWorldService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@Slf4j
@Service
public class HelloWorldServiceImpl implements IHelloWorldService {

  @Override
  public void helloWorld() {
    String info = "info";
    log.info("hello world service, log level is {}", info);
  }
}
