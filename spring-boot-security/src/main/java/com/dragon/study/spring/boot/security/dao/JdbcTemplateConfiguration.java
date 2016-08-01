package com.dragon.study.spring.boot.security.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


/**
 * Created by dragon on 16/7/11.
 */
@Configuration
@Import(JdbcDataSourceConfiguration.class)
public class JdbcTemplateConfiguration {
  @Bean
  public JdbcTemplateFactory jdbcTemplateFactory(DataSource dataSource) {
    return new JdbcTemplateFactory(dataSource);
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
      JdbcTemplateFactory jdbcTemplateFactory) {
    return new NamedParameterJdbcTemplate(jdbcTemplateFactory.getJdbcTemplate());
  }
}
