package com.dragon.study.spring.boot.zuul;

import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.http.ZuulServlet;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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
