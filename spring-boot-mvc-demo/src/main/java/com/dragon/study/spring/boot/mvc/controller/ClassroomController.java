package com.dragon.study.spring.boot.mvc.controller;

import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;
import com.dragon.study.spring.boot.mvc.service.IClassroomService;
import com.dragon.study.spring.boot.mvc.service.impl.ClassroomServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@RestController
@RequestMapping("/spring-boot")
@Slf4j
public class ClassroomController {

  @Autowired
  IClassroomService classroomService;

  @RequestMapping("/teacherName")
  public String teacherName(
      @RequestParam(name = "grade")
      int grade,
      @RequestParam(name = "classroom")
      int classroom) {
    return classroomService.getTeacherName(grade, classroom);
  }

  @RequestMapping("/classroom")
  public ClassroomModel classroom(
      @RequestParam(name = "grade")
      int grade,
      @RequestParam(name = "classroom")
      int classroom) {
    return classroomService.getClassroom(grade, classroom);
  }

  @RequestMapping("/topTen")
  public List<PersonModel> topTen(
      @RequestParam(name = "grade")
      int grade,
      @RequestParam(name = "classroom")
      int classroom) {
    return classroomService.getTopTenStudent(grade, classroom);
  }

  @RequestMapping("/biggerThan60")
  public List<PersonModel> biggerThan60(
      @RequestParam(name = "grade")
      int grade,
      @RequestParam(name = "classroom")
      int classroom) {
    return classroomService.getBiggerThan60(grade, classroom);
  }


}
