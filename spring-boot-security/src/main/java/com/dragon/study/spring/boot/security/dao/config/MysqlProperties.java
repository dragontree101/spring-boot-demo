package com.dragon.study.spring.boot.security.dao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Created by Reilost on 15/11/16.
 */
@Data
@ConfigurationProperties(prefix = "security")
public class MysqlProperties {
  private String userName;
  private String password = "";
  private String url;
}
