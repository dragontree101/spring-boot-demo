package com.dragon.study.spring.boot.hibernate.repository;

import com.dragon.study.spring.boot.hibernate.module.PersonAddressDetailInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dragon on 16/7/26.
 */
public interface AddressDetailRepository extends JpaRepository<PersonAddressDetailInfo, Integer> {
}
