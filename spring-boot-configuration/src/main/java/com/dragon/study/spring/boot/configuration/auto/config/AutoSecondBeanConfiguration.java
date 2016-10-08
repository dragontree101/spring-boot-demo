package com.dragon.study.spring.boot.configuration.auto.config;

import com.dragon.study.spring.boot.configuration.bean.AutoSecondBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/8.
 */
@Slf4j
@Configuration
public class AutoSecondBeanConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AutoSecondBean autoSecondBean() {
    log.info("log auto second bean");
    return new AutoSecondBean("autoSecondBean", 2);
  }
}
