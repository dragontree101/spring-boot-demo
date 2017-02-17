package com.dragon.study.spring.boot.jersey.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by dragon on 16/7/21.
 */
@Data
@AllArgsConstructor
public class JerseyConfigurationParam {
  private final String scanPackage;
  private final Class[] componentClasses;

}
