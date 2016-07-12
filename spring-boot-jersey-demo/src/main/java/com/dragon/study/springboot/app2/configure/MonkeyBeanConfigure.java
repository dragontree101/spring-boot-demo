package com.dragon.study.springboot.app2.configure;


import com.dragon.study.springboot.app2.bean.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/5/14.
 */
@Configuration
@EnableConfigurationProperties(MonkeyPersonProperties.class)
public class MonkeyBeanConfigure  {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private MonkeyPersonProperties monkeyPersonProperties;

  @Bean
  public Person monkey() {
    Person monkey = new Person();
    monkey.setAge(monkeyPersonProperties.getAge());
    monkey.setName(monkeyPersonProperties.getName());
    monkey.setMale(monkeyPersonProperties.isMale());
    System.out.println("create monkey person");
    return monkey;
  }


}
