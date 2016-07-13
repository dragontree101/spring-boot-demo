package com.dragon.study.spring.boot.mvc.service;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;

/**
 * Created by dragon on 16/7/12.
 */
public interface IPersonBasicInfoService {

  boolean registerPerson(PersonBasicInfo personBasicInfo);

  PersonBasicInfo queryPersonBasicInfo(String phone);

  boolean updatePersonInfo(PersonBasicInfo personBasicInfo);
}
