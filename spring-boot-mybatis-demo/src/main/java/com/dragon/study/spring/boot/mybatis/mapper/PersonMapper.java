package com.dragon.study.spring.boot.mybatis.mapper;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by dragon on 16/10/13.
 */
@Mapper
public interface PersonMapper {
  PersonBasicInfo findPersonBasicInfoByPhone(String phone);
}
