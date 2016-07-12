package com.dragon.study.spring.boot.mvc.config;

import com.dragon.study.spring.boot.jdbc.JdbcTemplateConfiguration;
import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by dragon on 16/7/12.
 */
@Configuration
@Import(JdbcTemplateConfiguration.class)
public class JdbcBeanConfig {


  @Bean
  public PersonBasicInfoDao mysqlPassInviteCodeStorage(
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    PersonBasicInfoDao personBasicInfoDao = new PersonBasicInfoDao();
    personBasicInfoDao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
    return personBasicInfoDao;
  }

}
