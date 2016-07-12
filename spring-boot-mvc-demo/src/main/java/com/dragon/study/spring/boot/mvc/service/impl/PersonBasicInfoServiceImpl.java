package com.dragon.study.spring.boot.mvc.service.impl;

import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;
import com.dragon.study.spring.boot.mvc.utils.EncryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/12.
 */
@Service
@Slf4j
public class PersonBasicInfoServiceImpl implements IPersonBasicInfoService {

  @Autowired
  private PersonBasicInfoDao personBasicInfoDao;

  @Override
  public boolean registerPerson(PersonBasicInfo personBasicInfo) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setCreateDate(LocalDateTime.now());
    personBasicInfo.setUpdateDate(LocalDateTime.now());

    return personBasicInfoDao.savePersonBasicInfo(personBasicInfo);
  }
}
