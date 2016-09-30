package com.dragon.study.spring.boot.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by dragon on 16/9/9.
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class).web(true).run(args);
  }

//  @Bean
//  public ServletRegistrationBean zuulServlet() {
//    ServletRegistrationBean servlet = new ServletRegistrationBean(new ZuulServlet());
//    servlet.addUrlMappings("/");
//    return servlet;
//  }
//
//  @Bean
//  public FilterRegistrationBean contextLifecycleFilter() {
//    FilterRegistrationBean filter = new FilterRegistrationBean(new ContextLifecycleFilter());
//    filter.addUrlPatterns("/*");
//    return filter;
//  }
}
