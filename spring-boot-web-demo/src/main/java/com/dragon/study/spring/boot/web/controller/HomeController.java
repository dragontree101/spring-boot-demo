package com.dragon.study.spring.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by dragon on 16/8/14.
 */
@Controller
class HomeController {

  @RequestMapping("/")
  String index(Model model) {
    model.addAttribute("now", LocalDateTime.now());
    return "index";
  }

  @RequestMapping("properties")
  @ResponseBody
  java.util.Properties properties() {
    return System.getProperties();
  }
}
