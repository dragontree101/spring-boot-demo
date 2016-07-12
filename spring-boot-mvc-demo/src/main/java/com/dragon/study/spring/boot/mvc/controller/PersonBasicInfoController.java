package com.dragon.study.spring.boot.mvc.controller;

import com.google.common.base.Strings;

import com.dragon.study.spring.boot.mvc.model.CommonResponse;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/12.
 */
@RestController
@RequestMapping("/spring-boot")
@Slf4j
public class PersonBasicInfoController {

  @Autowired
  IPersonBasicInfoService personBasicInfoService;

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public CommonResponse registerPerson(
      @RequestParam(name = "phone")
      String phone,
      @RequestParam(name = "email")
      String email,
      @RequestParam(name = "password")
      String password) {
    if(Strings.isNullOrEmpty(phone) || Strings.isNullOrEmpty(password)) {
      log.error("phone or password is empty or null");
      return CommonResponse.of(false);
    }

    try {
      personBasicInfoService.registerPerson(phone, email, password);
      return CommonResponse.of(true);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return CommonResponse.of(false);
    }
  }
}
