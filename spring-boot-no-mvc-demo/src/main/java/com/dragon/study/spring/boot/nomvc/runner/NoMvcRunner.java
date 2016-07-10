package com.dragon.study.spring.boot.nomvc.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by dragon on 16/7/10.
 */
@Component
public class NoMvcRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("hello world");
  }
}
