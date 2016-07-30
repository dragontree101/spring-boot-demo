package com.dragon.study.spring.boot.mvc.controller;

import com.google.common.base.Strings;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.exception.PersonBasicInfoException;
import com.dragon.study.spring.boot.mvc.model.CommonResponse;
import com.dragon.study.spring.boot.mvc.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
      PersonBasicInfo personBasicInfo,
      @RequestParam(value = "country", required = false)
      String country) {
    if (Strings.isNullOrEmpty(personBasicInfo.getPhone()) || Strings
        .isNullOrEmpty(personBasicInfo.getPassword())) {
      log.error("phone or password is empty or null");
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PHONE_FAILURE);
    }

    personBasicInfoService.registerPerson(personBasicInfo, country);
    return CommonResponse.of(true);
  }

  @RequestMapping(value = "/queryPerson/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public PersonBasicInfo queryPersonBasicInfo(
      @PathVariable
      String phone) {
    if (Strings.isNullOrEmpty(phone)) {
      log.error("phone is empty or null");
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PHONE_FAILURE);
    }

    return personBasicInfoService.queryPersonBasicInfo(phone);
  }

  @RequestMapping(value = "/updateInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public CommonResponse updatePersonBasicInfo(
      @ModelAttribute
      PersonBasicInfo personBasicInfo) {
    if (Strings.isNullOrEmpty(personBasicInfo.getPhone()) || Strings
        .isNullOrEmpty(personBasicInfo.getPassword())) {
      log.error("phone or password is empty or null");
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PHONE_FAILURE);
    }

    try {
      personBasicInfoService.updatePersonInfo(personBasicInfo);
      return CommonResponse.of(true);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return CommonResponse.of(false);
    }
  }
}
