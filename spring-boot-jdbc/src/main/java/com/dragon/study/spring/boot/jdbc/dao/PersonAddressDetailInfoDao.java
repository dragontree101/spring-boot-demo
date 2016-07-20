package com.dragon.study.spring.boot.jdbc.dao;

import com.dragon.study.spring.boot.jdbc.BaseDao;
import com.dragon.study.spring.boot.jdbc.module.PersonAddressDetailInfo;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;

/**
 * Created by dragon on 16/7/20.
 */
@Repository
public class PersonAddressDetailInfoDao extends BaseDao {

  private static final String ADD_ONE_ADDRESS_SQL = "INSERT INTO person_address_detail_info (phone, address, post_id, country, create_date, update_date) " + " VALUES (:phone, :address, :postId, :country, :createDate, :updateDate)";

  public boolean addPersonAddressDetailInfo(PersonAddressDetailInfo personAddressDetailInfo) {
    BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(
        personAddressDetailInfo);

    int result = getNamedParameterJdbcTemplate().update(ADD_ONE_ADDRESS_SQL, beanParamSource);
    return result > 0;
  }




  //TODO 方便为了测试的时候直接清楚数据库信息
  public void truncatePersonAddressDetailInfoTable() {
    String truncateTableSql = "truncate table person_address_detail_info";

    getNamedParameterJdbcTemplate().update(truncateTableSql, Collections.emptyMap());
    return;
  }
}
