package com.dragon.study.spring.boot.configuration.auto.config;

import com.dragon.study.spring.boot.configuration.bean.AutoThirdBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/8.
 */
@Slf4j
@Configuration
public class AutoThirdBeanConfiguration {

  @PostConstruct
  public void init() {
    log.info("log init auto third bean");
  }

  @Bean
  @ConditionalOnMissingBean
  public AutoThirdBean autoThirdBean() {
    log.info("log auto third bean");
    return new AutoThirdBean("autoThirdBean", 2);
  }
}
