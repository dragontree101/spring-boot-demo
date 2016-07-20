package com.dragon.study.spring.boot.jdbc.module;

import java.util.Date;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
public class PersonAddressDetailInfo {

  private int id;
  private String phone;
  private String address;
  private String postId;
  private String country;
  private Date createDate;
  private Date updateDate;
}
