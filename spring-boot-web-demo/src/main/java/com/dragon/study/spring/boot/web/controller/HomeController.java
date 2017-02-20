package com.dragon.study.spring.boot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dragon on 16/8/14.
 */
@RestController
@RequestMapping("/")
class HomeController {

  @RequestMapping("/pause")
  public String pause() throws InterruptedException {
    Thread.sleep(10000);
    return "Pause complete";
  }
}
