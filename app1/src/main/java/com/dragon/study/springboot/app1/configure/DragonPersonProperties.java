package com.dragon.study.springboot.app1.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by dragon on 16/5/14.
 */
@Data
@ConfigurationProperties(prefix = "person.dragon")
public class DragonPersonProperties {
  private String name;
  private int age;
  private boolean male;
}
