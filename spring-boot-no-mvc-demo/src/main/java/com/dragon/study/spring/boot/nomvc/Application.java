package com.dragon.study.spring.boot.nomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dragon on 16/5/14.
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    app.setWebEnvironment(false);
    app.run(args);
  }
}
