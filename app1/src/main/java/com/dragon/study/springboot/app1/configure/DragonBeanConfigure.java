package com.dragon.study.springboot.app1.configure;


import com.dragon.study.springboot.app2.bean.Person;
import com.dragon.study.springboot.app2.configure.MonkeyBeanConfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/5/14.
 */
@Configuration
@AutoConfigureAfter({MonkeyBeanConfigure.class})
public class DragonBeanConfigure{

  @Value("${person.dragon.age}")
  private int age;

  @Value("${person.dragon.name}")
  private String name;

  @Value("${person.dragon.male}")
  private boolean male;

  @Bean(name = "dragon")
//  @ConditionalOnBean(name = "monkey")
  public Person dragon() {
    Person dragon = new Person();
    dragon.setAge(age);
    dragon.setName(name);
    dragon.setMale(male);
    System.out.println("create dragon person");
    return dragon;
  }

}
