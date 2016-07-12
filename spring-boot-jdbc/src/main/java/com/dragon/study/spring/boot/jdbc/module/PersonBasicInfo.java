package com.dragon.study.spring.boot.jdbc.module;

import java.util.Date;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
public class PersonBasicInfo {

  private String phone;
  private String email = "";
  private String password;
  private Date createTime;
  private Date updateTime;
}
