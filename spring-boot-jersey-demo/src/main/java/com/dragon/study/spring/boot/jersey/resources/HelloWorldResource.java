package com.dragon.study.spring.boot.jersey.resources;

import com.dragon.study.spring.boot.jersey.service.IHelloJerseyService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
  public String helloJersey(@QueryParam("name") String name) {
    String info = "info";
    log.debug("hello jersey controller, name is {}, log level is {}", name, "debug");
    log.info("hello jersey controller, name is {}, log level is {}", name, info);
    log.warn("hello jersey controller, name is {}, log level is {}", name, WARN);
    log.error("hello jersey controller, name is {}, log level is error", name);

    helloWorldService.helloJersey();
    return "Hello World";
  }

  @Path("/hello-jersey")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String helloJerseyPost(@FormParam("name") String name) {
    String info = "info";
    log.info("hello jersey post controller, name is {}, log level is {}", name, info);

    helloWorldService.helloJersey();
    return "Hello World";
  }
}
