package com.dragon.study.springboot.app.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.LinkedHashMap;

/**
 * Created by dragon on 16/5/15.
 */
public class ApplicationEnvPrepareListener
    implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

  public static String PROPERTY_SOURCE_MAP = "dragon-map";

  @Override
  public void onApplicationEvent(
      ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
    Environment environment = applicationEnvironmentPreparedEvent.getEnvironment();
    MutablePropertySources mutablePropertySources = ((ConfigurableEnvironment) environment)
        .getPropertySources();

    LinkedHashMap<String, Object> properties1 = new LinkedHashMap<>();
    properties1.put("person.dragon.name", "dragon-copy-1");
    properties1.put("person.dragon.mail", true);
    addPropertySource(mutablePropertySources, properties1);

    LinkedHashMap<String, Object> properties2 = new LinkedHashMap<>();
    properties2.put("person.dragon.name", "dragon-copy-2");
    properties2.put("person.dragon.age", 9999);
    addPropertySource(mutablePropertySources, properties2);
  }

  private void addPropertySource(MutablePropertySources mutablePropertySources, LinkedHashMap<String, Object> properties) {
    PropertySource propertySource = new MapPropertySource(PROPERTY_SOURCE_MAP, properties);

    if (mutablePropertySources != null) {
      if(!mutablePropertySources.contains(propertySource.getName())) {
        mutablePropertySources.addFirst(propertySource);
      } else {
        PropertySource exitsPropertySource = mutablePropertySources.get(propertySource.getName());
        LinkedHashMap<String, Object> source = (LinkedHashMap<String, Object>)exitsPropertySource.getSource();
        properties.forEach((key,value) -> source.put(key, value));
      }
    }
  }
}
