package com.dragon.study.spring.boot.security;

import com.dragon.study.spring.boot.security.service.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by dragon on 16/8/1. 这个类只能为spring-mvc作为安全认证,不能用于jersey模块
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //    对于根路径和home目录不需要认证,认证是到login的登录页
    http.authorizeRequests().antMatchers("/mvc/spring-boot/hello-world").permitAll().anyRequest()
        .authenticated().and().formLogin().and().httpBasic();
  }
}
