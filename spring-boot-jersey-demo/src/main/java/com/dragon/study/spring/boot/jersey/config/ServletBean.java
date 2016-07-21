package com.dragon.study.spring.boot.jersey.config;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dragon on 16/7/21.
 */
@Configuration
@Import(JerseyAnnotationConfiguration.class)
public class ServletBean {

  @Autowired
  private JerseyConfig config;

  @Bean
  @ConditionalOnMissingBean(name = "jerseyServletRegistration")
  public ServletRegistrationBean jerseyServletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), JerseyConfig.getConfigurationParam().getPath());
    registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, config.getClass().getName());
    registration.setName("jerseyServlet");
    return registration;
  }
}
