package com.dragon.study.spring.boot.jdbc;

import com.alibaba.fastjson.JSON;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
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

  protected <T> Optional<T> queryForObject(String sql,
      RowMapper<T> rowMapper, Map<String, ?> paramMap) {
    try {
      T result = getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, rowMapper);
      if (result != null) {
        return Optional.ofNullable(result);
      }
    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      log.debug("class:{} ,queryForObject sql: {} ,params:{} ,rowmapper:{} , no_result",
          this.getClass().getSimpleName(), sql, JSON.toJSONString(paramMap),
          rowMapper.getClass().getSimpleName());
    }
    return Optional.empty();
  }

  protected <T> Optional<List<T>> queryForList(String sql,
      RowMapper<T> rowMapper, Map<String, ?> paramMap) {
    try {
      List<T> result = getNamedParameterJdbcTemplate().query(sql, paramMap, rowMapper);
      return Optional.ofNullable(result);
    } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
      log.debug("class:{} ,queryForList sql: {} ,params:{} ,rowmapper:{}, no_result",
          this.getClass().getSimpleName(), sql, JSON.toJSONString(paramMap),
          rowMapper.getClass().getSimpleName());
    }
    return Optional.empty();
  }

}
