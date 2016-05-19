package com.dragon.study.springboot.app1.controller;

import com.dragon.study.springboot.app2.bean.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dragon on 16/5/14.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private Person dragon;

  @Autowired
  private Person monkey;

  @RequestMapping("/dragon")
  public String dragon() {
    return dragon.toString();
  }

  @RequestMapping("/monkey")
  public String monkey() {
    return monkey.toString();
  }

}
