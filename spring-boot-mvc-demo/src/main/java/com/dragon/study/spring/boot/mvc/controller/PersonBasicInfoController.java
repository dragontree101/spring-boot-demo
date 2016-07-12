package com.dragon.study.spring.boot.mvc.controller;

import com.google.common.base.Strings;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.model.CommonResponse;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
  private IPersonBasicInfoService personBasicInfoService;

  @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public CommonResponse registerPerson(
      @ModelAttribute
      PersonBasicInfo personBasicInfo) {
    if(Strings.isNullOrEmpty(personBasicInfo.getPhone()) || Strings.isNullOrEmpty(personBasicInfo.getPassword())) {
      log.error("phone or password is empty or null");
      return CommonResponse.of(false);
    }

    try {
      personBasicInfoService.registerPerson(personBasicInfo);
      return CommonResponse.of(true);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return CommonResponse.of(false);
    }
  }
}
