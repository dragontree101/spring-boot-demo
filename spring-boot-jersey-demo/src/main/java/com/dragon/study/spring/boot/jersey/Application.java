package com.dragon.study.spring.boot.jersey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/7/21.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
