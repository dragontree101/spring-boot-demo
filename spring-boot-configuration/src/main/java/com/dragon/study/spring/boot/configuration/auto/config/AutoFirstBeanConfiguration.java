package com.dragon.study.spring.boot.configuration.auto.config;

import com.dragon.study.spring.boot.configuration.bean.AutoFirstBean;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/8.
 */
@Slf4j
@Configuration
@AutoConfigureAfter(AutoSecondBeanConfiguration.class)
@Import(AutoThirdBeanConfiguration.class)
public class AutoFirstBeanConfiguration {

  @PostConstruct
  public void init() {
    log.info("log init auto first bean");
  }

  @Bean
  @ConditionalOnMissingBean
  public AutoFirstBean autoFirstBean() {
    log.info("log auto first bean");
    return new AutoFirstBean("autoFirstBean", 1);
  }

}
