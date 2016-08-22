package com.dragon.study.spring.boot.jersey.provider;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;

/**
 * Created by dragon on 16/8/22.
 */
@PreMatching
@Priority(100)
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

  }

  @Override
  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) throws IOException {
    responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    responseContext.getHeaders()
        .add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    responseContext.getHeaders().add("Access-Control-Max-Age", "1728000");
    responseContext.getHeaders().add("Access-Control-Allow-Headers",
        requestContext.getHeaderString("Access-Control-Request-Headers"));
    if ("options".equalsIgnoreCase(requestContext.getMethod())) {
      responseContext.setStatus(204);
    }
  }
}