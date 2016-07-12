package com.dragon.study.spring.boot.mvc.service;

/**
 * Created by dragon on 16/7/12.
 */
public interface IPersonBasicInfoService {

  boolean registerPerson(String phone, String email, String password);
}
