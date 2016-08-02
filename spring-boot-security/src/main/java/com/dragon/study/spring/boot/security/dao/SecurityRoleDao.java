package com.dragon.study.spring.boot.security.dao;

import com.google.common.collect.Maps;

import com.dragon.study.spring.boot.security.dao.module.SecurityRole;
import com.dragon.study.spring.boot.security.dao.module.SecurityUser;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dragon on 16/8/1.
 */
@Repository
public class SecurityRoleDao extends BaseDao {

  private static final String SAVE_ROLE_SQL = "INSERT INTO security_role (username, role, create_date, update_date) " + " VALUES (:username, :role, :createDate, :updateDate)";

  private static final String GET_ROLE_SQL = "SELECT * FROM security_role WHERE username = :username";

  public boolean saveUser(SecurityUser securityUser) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(
        securityUser);

    int result = getNamedParameterJdbcTemplate().update(SAVE_ROLE_SQL, beanParamSource);
    return result > 0;
  }

  public List<SecurityRole> getRoles(String username) {
    Map<String, Object> params = Maps.newHashMap();
    params.put("username", username);
    Optional<List<SecurityRole>> userRoles = queryForList(GET_ROLE_SQL,
        BeanPropertyRowMapper.newInstance(SecurityRole.class), params);
    return userRoles.isPresent() ? userRoles.get() : new ArrayList<>();
  }

  //TODO 方便为了测试的时候直接清楚数据库信息
  public void truncateSecurityRoleTable() {
    String truncateTableSql = "truncate table security_role";

    getNamedParameterJdbcTemplate().update(truncateTableSql, Collections.emptyMap());
    return;
  }
}
