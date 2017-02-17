package com.dragon.study.spring.boot.mybatis.service;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mybatis.mapper.PersonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dragon on 16/10/13.
 */
@Service
public class PersonBasicInfoService {

  @Autowired
  private PersonMapper personMapper;

  public PersonBasicInfo queryPersonBasicInfo(String phone) {
    return personMapper.findPersonBasicInfoByPhone(phone);
  }
}
