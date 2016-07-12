package com.dragon.study.spring.boot.jdbc.dao;

import com.dragon.study.spring.boot.jdbc.BaseDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Created by dragon on 16/7/12.
 */
@Repository
public class PersonBasicInfoDao extends BaseDao {

  private static final String SAVE_ONE_PERSON_SQL = "INSERT INTO person_basic_info (phone, email, password, create_date, update_date) "
      + " VALUES (:phone, :email, :password, :createDate, :updateDate)";


  public boolean savePersonBasicInfo(PersonBasicInfo personBasicInfo) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(personBasicInfo);

    int result = getNamedParameterJdbcTemplate().update(SAVE_ONE_PERSON_SQL, beanParamSource);
    return result > 0;
  }
}
