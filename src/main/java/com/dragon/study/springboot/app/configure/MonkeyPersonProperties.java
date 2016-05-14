package com.dragon.study.springboot.app.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Created by dragon on 16/5/14.
 */
@Data
@ConfigurationProperties(prefix = "person.monkey")
public class MonkeyPersonProperties {
  private String name;
  private int age;
  private boolean male;
}
