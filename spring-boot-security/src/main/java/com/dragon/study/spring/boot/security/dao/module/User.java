package com.dragon.study.spring.boot.security.dao.module;

import lombok.Data;

/**
 * Created by dragon on 16/8/1.
 */
@Data
public class User {

  private String username;
  private String password;
  private Role role;


}

