package com.dragon.study.spring.boot.mvc.model;

import java.util.List;

import lombok.Data;

/**
 * Created by dragon on 16/7/10.
 */
@Data
public class ClassroomModel {
  private int grade;
  private int classroomNumber;
  private String teacher;
  private List<PersonModel> personModels;
}
