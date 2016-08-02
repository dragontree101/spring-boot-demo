package com.dragon.study.spring.boot.security.dao.module;

import lombok.Data;

/**
 * Created by dragon on 16/8/2.
 */
@Data
public class SecurityRole {
  private int id;
  private String username;
  private String role;
}
