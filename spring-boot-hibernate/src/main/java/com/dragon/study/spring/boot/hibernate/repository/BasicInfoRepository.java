package com.dragon.study.spring.boot.hibernate.repository;

import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by dragon on 16/7/26.
 */
public interface BasicInfoRepository extends JpaRepository<PersonBasicInfo, String> {

  @Modifying
  @Query("UPDATE PersonBasicInfo p set p.phone = ?1, p.email = ?2, p.password = ?3, p.update_date = ?4 WHERE p.phone = ?1")
  boolean update(String phone, String email, String password, Date updateDate);
}
