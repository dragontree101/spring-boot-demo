package com.dragon.study.spring.boot.mybatis.controller;

import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mybatis.service.PersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/13.
 */
@RestController
@RequestMapping("/spring-boot/mybatis")
@Slf4j
public class PersonController {

  @Autowired
  private PersonBasicInfoService personBasicInfoService;

  @RequestMapping(value = "/queryPerson/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public PersonBasicInfo queryPersonBasicInfo(
      @PathVariable
      String phone) {
    return personBasicInfoService.queryPersonBasicInfo(phone);
  }
}
