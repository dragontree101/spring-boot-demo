package com.dragon.study.spring.boot.security.dao;

import com.google.common.collect.Maps;

import com.dragon.study.spring.boot.security.dao.module.SecurityUser;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dragon on 16/8/1.
 */
@Repository
public class SecurityUserDao extends BaseDao {

  private static final String SAVE_USER_SQL = "INSERT INTO security_user (username, password, create_date, update_date) " + " VALUES (:username, :password, :createDate, :updateDate)";

  private static final String GET_USER_SQL = "SELECT * FROM security_user WHERE username = :username";

  public boolean saveUser(SecurityUser securityUser) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(
        securityUser);

    int result = getNamedParameterJdbcTemplate().update(SAVE_USER_SQL, beanParamSource);
    return result > 0;
  }

  public SecurityUser getUser(String username) {
    Map<String, Object> params = Maps.newHashMap();
    params.put("username", username);
    Optional<SecurityUser> user = queryForObject(GET_USER_SQL,
        BeanPropertyRowMapper.newInstance(SecurityUser.class), params);
    return user.orElse(null);
  }

  //TODO 方便为了测试的时候直接清楚数据库信息
  public void truncatePersonAddressDetailInfoTable() {
    String truncateTableSql = "truncate table security_user";

    getNamedParameterJdbcTemplate().update(truncateTableSql, Collections.emptyMap());
    return;
  }
}
