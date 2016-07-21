package com.dragon.study.spring.boot.jersey.config;

import com.dragon.study.spring.boot.jersey.annotation.EnableJersey;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * Created by dragon on 16/7/21.
 */

@Configuration
@AutoConfigureBefore(JerseyAutoConfiguration.class)
@ConditionalOnBean(annotation = EnableJersey.class)
public class JerseyAnnotationConfiguration implements ApplicationContextAware {


  private ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Bean
  @ConditionalOnMissingBean
  public JerseyConfig jerseyConfig() {
    String[] beanNames = applicationContext.getBeanNamesForAnnotation(EnableJersey.class);
    if (beanNames != null) {
      Class entrance = applicationContext.getBean(beanNames[0]).getClass();
      EnableJersey enableJersey = AnnotationUtils.getAnnotation(entrance, EnableJersey.class);
      if (enableJersey != null) {
        JerseyConfigurationParam param = new JerseyConfigurationParam(
            enableJersey.applicationPath(), enableJersey.scanPackage(),
            enableJersey.componentClasses());
        JerseyConfig.setConfigurationParam(param);
        return new JerseyConfig();
      }

    }
    return null;
  }

}
