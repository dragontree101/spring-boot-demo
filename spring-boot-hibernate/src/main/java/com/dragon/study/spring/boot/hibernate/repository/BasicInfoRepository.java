package com.dragon.study.spring.boot.hibernate.repository;

import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dragon on 16/7/26.
 */
public interface BasicInfoRepository extends JpaRepository<PersonBasicInfo, String> {
}
