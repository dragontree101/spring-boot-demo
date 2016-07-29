package com.dragon.study.spring.boot.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by dragon on 16/7/26.
 */
@Configuration
@Import(DataSourceConfiguation.class)
@EnableJpaRepositories(basePackages = "com.dragon.study.spring.boot.hibernate.repository", entityManagerFactoryRef = "personEntityManagerFactory", transactionManagerRef = "personTransactionManager")
@EnableTransactionManagement
public class HibernateConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  public EntityManagerFactory personEntityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.dragon.study.spring.boot.hibernate.module");
    factory.setDataSource(dataSource);
    Properties p = new Properties();
    p.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    factory.setJpaProperties(p);
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  @DependsOn("personEntityManagerFactory")
  public PlatformTransactionManager personTransactionManager(
      EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }
}
