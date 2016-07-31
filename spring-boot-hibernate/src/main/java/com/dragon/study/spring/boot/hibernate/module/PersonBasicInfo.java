package com.dragon.study.spring.boot.hibernate.module;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
@Entity
@Table(name = "person_basic_info")
public class PersonBasicInfo {

  @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
  @FormParam("phone")
  private String phone;
  @FormParam("email")
  private String email = "";
  @FormParam("password")
  private String password;
  @JSONField(format = "yyyy-MM-dd HH:mm")
  private Date createDate;
  @JSONField(name = "update_date", format = "yyyy-MM-dd HH:mm")
  private Date updateDate = new Date();
}
