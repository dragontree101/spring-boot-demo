package com.dragon.study.spring.boot.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/7/12.
 */
@Configuration
@ComponentScan(value = "com.dragon.study.spring.boot.jdbc")
public class JdbcBeanConfig {

}
