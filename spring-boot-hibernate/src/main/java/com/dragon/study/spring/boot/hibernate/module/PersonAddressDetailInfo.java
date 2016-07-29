package com.dragon.study.spring.boot.hibernate.module;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
@Entity
@Table(name = "person_address_detail_info")
public class PersonAddressDetailInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String phone;
  private String address;
  private String postId;
  private String country;
  @JSONField(format = "yyyy-MM-dd HH:mm")
  private Date createDate;
  @JSONField(name = "update_date", format = "yyyy-MM-dd HH:mm")
  private Date updateDate = new Date();
}
