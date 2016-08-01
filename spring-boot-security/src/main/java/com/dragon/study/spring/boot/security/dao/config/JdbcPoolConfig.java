package com.dragon.study.spring.boot.security.dao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@Data
@ConfigurationProperties(prefix = "security.pool")
public class JdbcPoolConfig {

  private String driverClassName = "com.mysql.jdbc.Driver";
  private int initialSize = 1;
  private int minIdle = 1;
  private int maxActive = 10;
  private int maxWait = 10000;
  private int timeBetweenEvictionRunsMillis = 60000;
  private int minEvictableIdleTimeMillis = 300000;
  private String validationQuery = "SELECT 'x';";
  private boolean testWhileIdle = true;
  private boolean testOnBorrow = false;
  private boolean testOnReturn = true;
}
