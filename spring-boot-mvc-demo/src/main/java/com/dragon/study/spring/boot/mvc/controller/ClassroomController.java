package com.dragon.study.spring.boot.mvc.controller;

import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;
import com.dragon.study.spring.boot.mvc.service.IClassroomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@PreAuthorize("hasAuthority('user')")
public class ClassroomController {

  @Autowired
  IClassroomService classroomService;

  @RequestMapping(value = "/teacherName", method = RequestMethod.GET)
  public String teacherName(
      @RequestParam(name = "grade", required = true)
      int grade,
      @RequestParam(name = "classroom", required = true)
      int classroom) {
    return classroomService.getTeacherName(grade, classroom);
  }

  @PreAuthorize("hasAuthority('coder')")
  @RequestMapping(value = "/classroom/{grade}/{classroom}", method = RequestMethod.GET)
  public ClassroomModel classroom(
      @PathVariable
      int grade,
      @PathVariable
      int classroom) {
    return classroomService.getClassroom(grade, classroom);
  }

  @PreAuthorize("hasAuthority('admin')")
  @RequestMapping(value = "/topTen", method = RequestMethod.GET)
  public List<PersonModel> topTen(
      @RequestParam(name = "grade", required = true)
      int grade,
      @RequestParam(name = "classroom", required = true)
      int classroom) {
    return classroomService.getTopTenStudent(grade, classroom);
  }

  @RequestMapping(value = "/biggerThan60", method = RequestMethod.GET)
  public List<PersonModel> biggerThan60(
      @RequestParam(name = "grade", required = true)
      int grade,
      @RequestParam(name = "classroom", required = true)
      int classroom) {
    return classroomService.getBiggerThan60(grade, classroom);
  }


}
