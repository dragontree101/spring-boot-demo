package com.dragon.study.spring.boot.security.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * Created by dragon on 16/7/11.
 */
@Configuration
@Import(JdbcDataSourceConfiguration.class)
public class JdbcTemplateConfiguration {

  @Resource(name = "securityDataSource")
  private DataSource dataSource;

  @Bean
  public JdbcTemplateFactory jdbcTemplateFactory() {
    return new JdbcTemplateFactory(dataSource);
  }

  @Bean(name = "securityJdbcTemplate")
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
      JdbcTemplateFactory jdbcTemplateFactory) {
    return new NamedParameterJdbcTemplate(jdbcTemplateFactory.getJdbcTemplate());
  }
}
