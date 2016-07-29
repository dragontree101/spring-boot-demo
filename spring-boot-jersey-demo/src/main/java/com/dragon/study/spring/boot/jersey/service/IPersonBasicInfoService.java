package com.dragon.study.spring.boot.jersey.service;


import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;

/**
 * Created by dragon on 16/7/12.
 */
public interface IPersonBasicInfoService {

  boolean registerPerson(PersonBasicInfo personBasicInfo, String country);

  PersonBasicInfo queryPersonBasicInfo(String phone);

  boolean updatePersonInfo(PersonBasicInfo personBasicInfo);


}
