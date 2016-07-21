package com.dragon.study.spring.boot.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.Getter;

/**
 * Created by dragon on 16/7/21.
 */
@Component
@Getter
public class JerseyConfig extends ResourceConfig {

  private final String path;

  public JerseyConfig(final String path, final String scanPackage, final Class[] componentClasses) {
    this.path = path;

    packages(scanPackage);

    for (Class clazz : componentClasses) {
      if (clazz != null) {
        register(clazz);
      }
    }

    Map<String, Object> properties = Maps.newHashMap();
    properties.put(ServerProperties.WADL_FEATURE_DISABLE, true);
    addProperties(properties);
  }
}
