package com.dragon.study.spring.boot.configuration.config;

import com.dragon.study.spring.boot.configuration.bean.FirstBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/8.
 */
@Configuration
@Slf4j
public class FirstBeanConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public FirstBean firstBean() {
    log.info("log first bean");
    return new FirstBean("firstBean", 1);
  }
}
