package com.dragon.study.spring.boot.jdbc.module;

import java.util.Date;

import lombok.Data;

/**
 * Created by dragon on 16/7/11.
 */
@Data
public class Economy {

  private Date date;
  private String money;
  private String address;
  private String goods;
}
