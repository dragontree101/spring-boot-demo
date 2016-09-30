package com.dragon.study.spring.boot.mvc.model;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

/**
 * Created by dragon on 16/9/30.
 */
@Data
public class TestModel {
  @Range(min = 1, max = 9, message = "年级只能从1-9")
  private int grade;

  @Range(min = 1, max = 99, message = "班级只能从1-99")
  private int classroomNumber;
}
