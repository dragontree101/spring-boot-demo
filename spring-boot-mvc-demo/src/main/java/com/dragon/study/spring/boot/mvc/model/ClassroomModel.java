package com.dragon.study.spring.boot.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

import lombok.Data;

/**
 * Created by dragon on 16/7/10.
 */
@Data
public class ClassroomModel {
  private int grade;
  @JSONField(name = "classroom_number")
  private int classroomNumber;
  private String teacher;
  @JSONField(name = "person_models")
  private List<PersonModel> personModels;
}
