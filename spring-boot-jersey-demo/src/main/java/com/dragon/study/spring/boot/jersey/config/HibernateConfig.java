package com.dragon.study.spring.boot.jersey.config;

import com.dragon.study.spring.boot.hibernate.HibernateConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dragon on 16/7/29.
 */
@Configuration
@Import(HibernateConfiguration.class)
public class HibernateConfig {
}
