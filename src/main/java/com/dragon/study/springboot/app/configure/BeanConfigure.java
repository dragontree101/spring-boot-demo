package com.dragon.study.springboot.app.configure;

import com.dragon.study.springboot.app.bean.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/5/14.
 */
@Configuration
@EnableConfigurationProperties(value = {DragonPersonProperties.class, MonkeyPersonProperties.class})
public class BeanConfigure {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private DragonPersonProperties dragonPersonProperties;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private MonkeyPersonProperties monkeyPersonProperties;

  @Bean
  @ConditionalOnMissingBean
  public Person dragon() {
    Person dragon = new Person();
    dragon.setAge(dragonPersonProperties.getAge());
    dragon.setName(dragonPersonProperties.getName());
    dragon.setMale(dragonPersonProperties.isMale());
    return dragon;
  }

  @Bean
  @ConditionalOnMissingBean
  public Person monkey() {
    Person monkey = new Person();
    monkey.setAge(monkeyPersonProperties.getAge());
    monkey.setName(monkeyPersonProperties.getName());
    monkey.setMale(monkeyPersonProperties.isMale());
    return monkey;
  }

}
