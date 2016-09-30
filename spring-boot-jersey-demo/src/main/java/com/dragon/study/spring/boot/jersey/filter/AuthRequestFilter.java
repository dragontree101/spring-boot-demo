package com.dragon.study.spring.boot.jersey.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 * Created by dragon on 16/8/18.
 */
@Priority(1000)
public class AuthRequestFilter implements ContainerRequestFilter {


  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

  }
}
