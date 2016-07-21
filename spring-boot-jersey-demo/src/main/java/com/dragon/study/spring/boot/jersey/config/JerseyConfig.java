package com.dragon.study.spring.boot.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;

/**
 * Created by dragon on 16/7/21.
 */
public class JerseyConfig extends ResourceConfig {

  private static JerseyConfigurationParam configurationParam;

  public JerseyConfig() {
    packages(configurationParam.getScanPackage());

    for (Class clazz : configurationParam.getComponentClasses()) {
      if (clazz != null) {
        register(clazz);
      }
    }

    Map<String, Object> properties = Maps.newHashMap();
    properties.put(ServerProperties.WADL_FEATURE_DISABLE, true);
    addProperties(properties);
  }

  public static JerseyConfigurationParam getConfigurationParam() {
    return configurationParam;
  }

  public static void setConfigurationParam(JerseyConfigurationParam configurationParam) {
    JerseyConfig.configurationParam = configurationParam;
  }
}
