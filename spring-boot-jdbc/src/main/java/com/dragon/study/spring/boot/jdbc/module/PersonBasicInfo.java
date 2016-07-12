package com.dragon.study.spring.boot.jdbc.module;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
public class PersonBasicInfo {

  private String phone;
  private String email = "";
  private String password;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
}
