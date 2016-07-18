package com.dragon.study.spring.boot.redis.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Created by dragon on 16/7/18.
 */
@Data
@ConfigurationProperties(prefix = "redis.pool")
public class RedisPoolProperties {

  private int maxTotal = 256;
  private int maxIdle = 32;
  private long maxWaitMillis = 1000;
  private boolean testOnBorrow = true;


}
