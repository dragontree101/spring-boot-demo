package com.dragon.study.spring.boot.jersey.service.impl;


import com.dragon.study.spring.boot.jersey.service.IHelloJerseyService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@Slf4j
@Service
public class HelloJerseyServiceImpl implements IHelloJerseyService {

  @Override
  public void helloJersey() {
    String info = "info";
    log.info("hello jersey service, log level is {}", info);
  }
}
