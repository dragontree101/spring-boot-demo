package com.dragon.study.spring.boot.jdbc.dao;

import com.google.common.collect.Maps;

import com.dragon.study.spring.boot.jdbc.BaseDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by dragon on 16/7/12.
 */
@Repository("personBasicInfoDao")
public class PersonBasicInfoDaoImpl extends BaseDao {

  private static final String SAVE_ONE_PERSON_SQL = "INSERT INTO person (phone, email, password, create_date, update_date) "
      + " VALUES (:personBasicInfo.phone, :personBasicInfo.email, :personBasicInfo.password, :personBasicInfo.createDate, :personBasicInfo.updateDate)";


  public boolean savePersonBasicInfo(PersonBasicInfo personBasicInfo) {
    Map<String, Object> paramMap = Maps.newHashMap();
    paramMap.put("personBasicInfo", personBasicInfo);

    int result = getNamedParameterJdbcTemplate().update(SAVE_ONE_PERSON_SQL, paramMap);
    return result > 0;
  }
}
