package com.dragon.study.spring.boot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

import javax.sql.DataSource;


/**
 * Created by dragon on 16/7/11.
 */
@Configuration
@Import(JdbcDataSourceConfiguration.class)
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class JdbcTemplateConfiguration {

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(
        dataSource);
    AnnotationTransactionAspect.aspectOf().setTransactionManager(dataSourceTransactionManager);
    return dataSourceTransactionManager;
  }

  @Bean
  public JdbcTemplateFactory jdbcTemplateFactory(DataSource dataSource) {
    return new JdbcTemplateFactory(dataSource);
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplateFactory jdbcTemplateFactory) {
    return new NamedParameterJdbcTemplate(jdbcTemplateFactory.getJdbcTemplate());
  }
}
