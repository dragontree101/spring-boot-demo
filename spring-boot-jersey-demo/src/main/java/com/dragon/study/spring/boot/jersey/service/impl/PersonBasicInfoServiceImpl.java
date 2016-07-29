package com.dragon.study.spring.boot.jersey.service.impl;


import com.dragon.study.spring.boot.hibernate.module.PersonAddressDetailInfo;
import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;
import com.dragon.study.spring.boot.hibernate.repository.AddressDetailRepository;
import com.dragon.study.spring.boot.hibernate.repository.BasicInfoRepository;
import com.dragon.study.spring.boot.hibernate.utils.EncryptUtils;
import com.dragon.study.spring.boot.jersey.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
  private BasicInfoRepository basicInfoRepository;

  @Autowired
  private AddressDetailRepository addressDetailRepository;

  @Override
  //  @CacheEvict(value = "personInfo", key = "#personBasicInfo.getPhone()")
  //  @Transactional(rollbackFor = Exception.class)
  public boolean registerPerson(PersonBasicInfo personBasicInfo, String country) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setCreateDate(new Date());
    personBasicInfo.setUpdateDate(new Date());
    basicInfoRepository.save(personBasicInfo);

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
    addressDetailRepository.save(personAddressDetailInfo);

    return true;
  }

  @Override
  //  @Cacheable(value = "personInfo", keyGenerator = "phoneKeyGenerator")
  public PersonBasicInfo queryPersonBasicInfo(String phone) {
    PersonBasicInfo personBasicInfo = basicInfoRepository.findOne(phone);
    if (personBasicInfo == null) {
      throw new RuntimeException("person basic info is null");
    }

    return personBasicInfo;
  }


  @Override
  //  @CacheEvict(value = "personInfo", key = "#personBasicInfo.getPhone()")
  public boolean updatePersonInfo(PersonBasicInfo personBasicInfo) {
    String password = personBasicInfo.getPassword();
    personBasicInfo.setPassword(EncryptUtils.encryptMD5(password));
    personBasicInfo.setUpdateDate(new Date());

    return basicInfoRepository.update(personBasicInfo.getPhone(), personBasicInfo.getEmail(),
        personBasicInfo.getPassword(), personBasicInfo.getUpdateDate());
  }
}
