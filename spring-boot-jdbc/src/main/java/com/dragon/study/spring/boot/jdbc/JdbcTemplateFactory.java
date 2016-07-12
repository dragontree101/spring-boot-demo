package com.dragon.study.spring.boot.jdbc;


import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import lombok.Getter;

/**
 * Created by dragon on 16/7/11.
 */
@Getter
public class JdbcTemplateFactory {

  private JdbcTemplate jdbcTemplate;

  public JdbcTemplateFactory(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
  }
}
