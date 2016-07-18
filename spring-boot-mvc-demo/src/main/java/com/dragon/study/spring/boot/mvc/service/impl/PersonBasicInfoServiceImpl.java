package com.dragon.study.spring.boot.mvc.service.impl;

import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.exception.PersonBasicInfoException;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;
import com.dragon.study.spring.boot.mvc.utils.EncryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    personBasicInfo.setCreateDate(new Date());
    personBasicInfo.setUpdateDate(new Date());

    return personBasicInfoDao.savePersonBasicInfo(personBasicInfo);
  }

  @Override
  public PersonBasicInfo queryPersonBasicInfo(String phone) {
    PersonBasicInfo personBasicInfo = personBasicInfoDao.getPersonBasicInfo(phone);
    if(personBasicInfo == null) {
      throw new PersonBasicInfoException(PersonBasicInfoException.Exception.NO_PERSON_FAILURE);
    }

    return personBasicInfo;
  }


  @Override
  public boolean updatePersonInfo(PersonBasicInfo personBasicInfo) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setUpdateDate(new Date());

    return personBasicInfoDao.updatePersonBasicInfo(personBasicInfo);
  }
}
