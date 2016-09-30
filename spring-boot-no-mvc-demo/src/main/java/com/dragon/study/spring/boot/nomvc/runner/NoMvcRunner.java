package com.dragon.study.spring.boot.nomvc.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@Component
@Slf4j
public class NoMvcRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("hello world");
  }
}
