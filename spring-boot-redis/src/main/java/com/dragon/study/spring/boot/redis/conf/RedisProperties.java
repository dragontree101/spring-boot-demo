package com.dragon.study.spring.boot.redis.conf;

import lombok.Data;

/**
 * Created by dragon on 16/7/18.
 */
@Data
public class RedisProperties {

  private String hostName;
  private int port;
  private String password;
  private int timeout = 1000;
  private int database = 0;
  private boolean usePool = true;
}
