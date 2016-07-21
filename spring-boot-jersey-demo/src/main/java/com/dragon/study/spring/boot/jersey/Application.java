package com.dragon.study.spring.boot.jersey;

import com.dragon.study.spring.boot.jersey.annotation.EnableJersey;

import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
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
@EnableJersey(
    scanPackage = "com.dragon.study.spring.boot.jersey.resources",
    applicationPath = "/jersey/*",
    componentClasses = {
        RequestContextFilter.class
    }
)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
