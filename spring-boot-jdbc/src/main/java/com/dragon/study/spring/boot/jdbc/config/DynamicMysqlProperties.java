package com.dragon.study.spring.boot.jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

import lombok.Data;

/**
 * Created by dragon on 16/8/7.
 */
@Data
@ConfigurationProperties(prefix = "mysql")
public class DynamicMysqlProperties {
  Map<String, MysqlProperties> entity;
}
