package com.dragon.study.spring.boot.jdbc;

import com.alibaba.fastjson.JSON;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public abstract class BaseDao {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
    return namedParameterJdbcTemplate;
  }

  protected <T> Optional<T> queryForObject(JdbcTemplate jdbcTemplate, String sql,
      RowMapper<T> rowMapper, Object... args) {
    try {
      T result = jdbcTemplate.queryForObject(sql, rowMapper, args);
      if (result != null) {
        return Optional.ofNullable(result);
      }
    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      log.debug("class:{} ,queryForObject sql: {} ,params:{} ,rowmapper:{} , no_result",
          this.getClass().getSimpleName(), sql, JSON.toJSONString(args),
          rowMapper.getClass().getSimpleName());
    }
    return Optional.empty();
  }

  protected <T> Optional<List<T>> queryForList(JdbcTemplate jdbcTemplate, String sql,
      RowMapper<T> rowMapper, Object... args) {
    try {
      List<T> result = jdbcTemplate.query(sql, rowMapper, args);
      return Optional.ofNullable(result);
    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      log.debug("class:{} ,queryForList sql: {} ,params:{} ,rowmapper:{}, no_result",
          this.getClass().getSimpleName(), sql, JSON.toJSONString(args),
          rowMapper.getClass().getSimpleName());
    }
    return Optional.empty();
  }

}
