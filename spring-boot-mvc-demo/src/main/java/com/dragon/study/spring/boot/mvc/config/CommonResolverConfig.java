package com.dragon.study.spring.boot.mvc.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.dragon.study.spring.boot.mvc.exception.resolver.MvcHandlerExceptionResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Created by dragon on 16/7/12.
 */
@Configuration
public class CommonResolverConfig {

  /**
   * 这个bean用来处理异常的
   * @return
   */
  @Bean
  public MvcHandlerExceptionResolver mvcHandlerExceptionResolver() {
    return new MvcHandlerExceptionResolver();
  }

  /**
   * 这个bean用来处理验证参数的
   * @return
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }

  /**
   * 这个bean用来转JSONField的
   * @return
   */
  @Bean
  public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
    return new FastJsonHttpMessageConverter();
  }
}
