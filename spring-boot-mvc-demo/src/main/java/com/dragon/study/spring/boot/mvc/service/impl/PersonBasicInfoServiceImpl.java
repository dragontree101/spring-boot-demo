package com.dragon.study.spring.boot.mvc.service.impl;

import com.dragon.study.spring.boot.jdbc.dao.PersonAddressDetailInfoDao;
import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;
import com.dragon.study.spring.boot.jdbc.module.PersonAddressDetailInfo;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.exception.PersonBasicInfoException;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;
import com.dragon.study.spring.boot.mvc.utils.EncryptUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Autowired
  private PersonAddressDetailInfoDao personAddressDetailInfoDao;

  @Override
  @CacheEvict(value = "personInfo", key = "#personBasicInfo.getPhone()")
  @Transactional(rollbackFor = Exception.class)
  public boolean registerPerson(PersonBasicInfo personBasicInfo, String country) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setCreateDate(new Date());
    personBasicInfo.setUpdateDate(new Date());
    personBasicInfoDao.savePersonBasicInfo(personBasicInfo);

    PersonAddressDetailInfo personAddressDetailInfo = new PersonAddressDetailInfo();
    personAddressDetailInfo.setPhone(personBasicInfo.getPhone());
    personAddressDetailInfo.setAddress("");
    personAddressDetailInfo.setPostId("000000");
    personAddressDetailInfo.setCreateDate(new Date());
    personAddressDetailInfo.setUpdateDate(new Date());
    if (country == null) {
      personAddressDetailInfo.setCountry("中国");
    } else {
      personAddressDetailInfo.setCountry(country);
    }
    personAddressDetailInfoDao.addPersonAddressDetailInfo(personAddressDetailInfo);

    return true;
  }

  @Override
  @Cacheable(value = "personInfo", keyGenerator = "phoneKeyGenerator")
  public PersonBasicInfo queryPersonBasicInfo(String phone) {
    PersonBasicInfo personBasicInfo = personBasicInfoDao.getPersonBasicInfo(phone);
    if (personBasicInfo == null) {
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PERSON_FAILURE);
    }

    return personBasicInfo;
  }


  @Override
  @CacheEvict(value = "personInfo", key = "#personBasicInfo.getPhone()")
  public boolean updatePersonInfo(PersonBasicInfo personBasicInfo) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setUpdateDate(new Date());

    return personBasicInfoDao.updatePersonBasicInfo(personBasicInfo);
  }
}
