package com.dragon.study.spring.boot.jersey.resources;

import com.dragon.study.spring.boot.jersey.service.IHelloJerseyService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/21.
 */
@Path("/spring-boot/hello")
@Slf4j
//@PermitAll
public class HelloWorldResource {

  private static final String WARN = "warn";

  @Autowired
  IHelloJerseyService helloWorldService;


  //TODO curl -u 'dragon:longlong0' 'http://127.0.0.1:8088/jersey/spring-boot/hello/hello-jersey?name=longzhe'
  @Path("/hello-jersey")
  @GET
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
//  @RolesAllowed("user")
  public String helloJersey(
      @QueryParam("name")
      String name) {
    String info = "info";
    log.debug("hello jersey controller, name is {}, log level is {}", name, "debug");
    log.info("hello jersey controller, name is {}, log level is {}", name, info);
    log.warn("hello jersey controller, name is {}, log level is {}", name, WARN);
    log.error("hello jersey controller, name is {}, log level is error", name);

    helloWorldService.helloJersey();
    return "Hello World";
  }

  //TODO curl 'http://127.0.0.1:8088/jersey/spring-boot/hello/hello-jersey/user'
  @Path("/hello-jersey/{path}")
  @GET
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public String helloJerseyPath(
      @PathParam("path")
      String path) {
    log.info("hello jersey controller, path is {}, log level is {}", path, "INFO");

    helloWorldService.helloJersey();
    return "Hello World";
  }

  @Path("/hello-jersey")
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public String helloJerseyPost(
      @FormParam("name")
      String name) {
    String info = "info";
    log.info("hello jersey post controller, name is {}, log level is {}", name, info);

    helloWorldService.helloJersey();
    return "Hello World";
  }
}
