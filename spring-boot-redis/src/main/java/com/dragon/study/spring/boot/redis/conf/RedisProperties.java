package com.dragon.study.spring.boot.redis.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Created by dragon on 16/7/18.
 */
@Data
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

  private String hostName = "127.0.0.1";
  private int port = 6379;
  private String password;
  private int timeout = 1000;
  private int database = 0;
  private boolean usePool = true;
}
