package com.dragon.study.spring.boot.mvc.service.impl;

import com.google.common.base.Strings;

import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDaoImpl;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;
import com.dragon.study.spring.boot.mvc.utils.EncryptUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/12.
 */
@Service
@Slf4j
public class PersonBasicInfoServiceImpl implements IPersonBasicInfoService {

  @Resource
  private PersonBasicInfoDaoImpl personBasicInfoDao;

  @Override
  public boolean registerPerson(String phone, String email, String password) {
    PersonBasicInfo personBasicInfo = new PersonBasicInfo();
    personBasicInfo.setPhone(phone);
    if(!Strings.isNullOrEmpty(email)) {
      personBasicInfo.setEmail(email);
    }

    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));

    return personBasicInfoDao.savePersonBasicInfo(personBasicInfo);
  }
}
