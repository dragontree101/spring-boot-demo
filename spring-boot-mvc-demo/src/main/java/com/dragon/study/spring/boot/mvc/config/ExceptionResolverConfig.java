package com.dragon.study.spring.boot.mvc.config;

import com.dragon.study.spring.boot.mvc.exception.resolver.MvcHandlerExceptionResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/7/12.
 */
@Configuration
public class ExceptionResolverConfig {

  @Bean
  public MvcHandlerExceptionResolver mvcHandlerExceptionResolver() {
    return new MvcHandlerExceptionResolver();
  }
}
