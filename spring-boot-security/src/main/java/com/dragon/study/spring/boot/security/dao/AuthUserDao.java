package com.dragon.study.spring.boot.security.dao;

import com.google.common.collect.Maps;

import com.dragon.study.spring.boot.security.dao.module.User;

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
public class AuthUserDao extends BaseDao {

  private static final String SAVE_USER_SQL = "INSERT INTO security_user (username, password, create_date, update_date) " + " VALUES (:username, :password, :createDate, :updateDate)";

  private static final String GET_USER_SQL = "SELECT * FROM security_user WHERE username = :username";

  public boolean saveUser(User user) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);

    int result = getNamedParameterJdbcTemplate().update(SAVE_USER_SQL, beanParamSource);
    return result > 0;
  }

  public User getUser(String username) {
    Map<String, Object> params = Maps.newHashMap();
    params.put("username", username);
    Optional<User> user = queryForObject(GET_USER_SQL,
        BeanPropertyRowMapper.newInstance(User.class), params);
    return user.orElse(null);
  }

  //TODO 方便为了测试的时候直接清楚数据库信息
  public void truncatePersonAddressDetailInfoTable() {
    String truncateTableSql = "truncate table security_user";

    getNamedParameterJdbcTemplate().update(truncateTableSql, Collections.emptyMap());
    return;
  }
}
