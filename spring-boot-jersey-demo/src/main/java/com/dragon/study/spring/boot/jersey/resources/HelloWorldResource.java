package com.dragon.study.spring.boot.jersey.resources;

import com.dragon.study.spring.boot.jersey.service.IHelloJerseyService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/21.
 */
@Path("/spring-boot")
@Slf4j
public class HelloWorldResource {

  private static final String WARN = "warn";

  @Autowired
  IHelloJerseyService helloWorldService;

  @Path("/hello-jersey")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String helloJersey() {
    String info = "info";
    log.debug("hello jersey controller, log level is {}", "debug");
    log.info("hello jersey controller, log level is {}", info);
    log.warn("hello jersey controller, log level is {}", WARN);
    log.error("hello jersey controller, log level is error");

    helloWorldService.helloJersey();
    return "Hello World";
  }
}
