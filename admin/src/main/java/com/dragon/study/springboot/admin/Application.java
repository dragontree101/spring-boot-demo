package com.dragon.study.springboot.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dragon on 16/5/14.
 */
@SpringBootApplication
@EnableAdminServer
public class Application {
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }
}
