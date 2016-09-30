package com.dragon.study.spring.boot.mvc.controller;

import com.dragon.study.spring.boot.mvc.annotation.ParamValidator;
import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;
import com.dragon.study.spring.boot.mvc.model.TestModel;
import com.dragon.study.spring.boot.mvc.service.IClassroomService;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/10.
 */
@RestController
@RequestMapping("/spring-boot/classroom")
@Slf4j
@Validated
public class ClassroomController {

  @Autowired
  IClassroomService classroomService;

  @RequestMapping(value = "/teacherName", method = RequestMethod.GET)
  public String teacherName(
      @Range(min = 1, max = 9, message = "年级只能从1-9")
      @RequestParam(name = "grade", required = true)
      int grade,
      @Min(value = 1, message = "班级最小只能1")
      @Max(value = 99, message = "班级最大只能99")
      @RequestParam(name = "classroom", required = true)
      int classroom) {
    return classroomService.getTeacherName(grade, classroom);
  }

  @RequestMapping(value = "/classroom/{grade}/{classroom}", method = RequestMethod.GET)
  public ClassroomModel classroom(
      @PathVariable
      int grade,
      @PathVariable
      int classroom) {
    return classroomService.getClassroom(grade, classroom);
  }

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

  @RequestMapping(value = "/paramErrorTest", method = RequestMethod.GET)
  public String paramErrorTest(
      @Valid
      @ModelAttribute
      TestModel testModel, BindingResult result) {
    return classroomService.getTeacherName(testModel.getGrade(), testModel.getClassroomNumber());
  }

  @RequestMapping(value = "/paramValidator", method = RequestMethod.GET)
  public String paramValidator(
      @ParamValidator(isRequired = true, desc = "年级", range = "int:1~9", message = "年级只能从1-9")
      @RequestParam(name = "grade", required = true)
      int grade,
      @ParamValidator(isRequired = true, desc = "班级", range = "int:1~99", message = "班级只能从1-99")
      @RequestParam(name = "classroom", required = true)
      int classroom) {
    return classroomService.getTeacherName(grade, classroom);
  }

}
