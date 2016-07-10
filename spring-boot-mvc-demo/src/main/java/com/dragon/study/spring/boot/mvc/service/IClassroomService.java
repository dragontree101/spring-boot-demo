package com.dragon.study.spring.boot.mvc.service;

import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;

import java.util.List;

/**
 * Created by dragon on 16/7/10.
 */
public interface IClassroomService {

  String getTeacherName(int grade, int classroom);
  List<PersonModel> getTopTenStudent(int grade, int classroom);
  List<PersonModel> getBiggerThan60(int grade, int classroom);
  ClassroomModel getClassroom(int grade, int classroom);

}
