package com.dragon.study.spring.boot.jdbc.dao;

import com.google.common.collect.Maps;

import com.dragon.study.spring.boot.jdbc.BaseDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dragon on 16/7/12.
 */
@Repository
public class PersonBasicInfoDao extends BaseDao {

  private static final String SAVE_ONE_PERSON_SQL = "INSERT INTO person_basic_info (phone, email, password, create_date, update_date) " + " VALUES (:phone, :email, :password, :createDate, :updateDate)";

  private static final String QUERY_ONE_PERSON_SQL = "SELECT * FROM person_basic_info WHERE phone = :phone";

  private static final String UPDATE_PERSON_SQL = "UPDATE person_basic_info set phone = :phone, email = :email, password = :password, update_date = :updateDate " + " WHERE phone = :phone";

  public boolean savePersonBasicInfo(PersonBasicInfo personBasicInfo) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(
        personBasicInfo);

    int result = getNamedParameterJdbcTemplate().update(SAVE_ONE_PERSON_SQL, beanParamSource);
    return result > 0;
  }

  public PersonBasicInfo getPersonBasicInfo(String phone) {
    Map<String, Object> params = Maps.newHashMap();
    params.put("phone", phone);
    Optional<PersonBasicInfo> personBasicInfo = queryForObject(QUERY_ONE_PERSON_SQL,
        BeanPropertyRowMapper.newInstance(PersonBasicInfo.class), params);
    return personBasicInfo.orElse(null);
  }

  public boolean updatePersonBasicInfo(PersonBasicInfo personBasicInfo) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(
        personBasicInfo);

    int result = getNamedParameterJdbcTemplate().update(UPDATE_PERSON_SQL, beanParamSource);
    return result > 0;
  }


  //TODO 方便为了测试的时候直接清楚数据库信息
  public void truncatePersonBasicInfoTable() {
    String truncateTableSql = "truncate table person_basic_info";

    getNamedParameterJdbcTemplate().update(truncateTableSql, Collections.emptyMap());
    return;
  }
}
