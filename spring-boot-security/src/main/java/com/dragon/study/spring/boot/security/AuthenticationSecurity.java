package com.dragon.study.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by dragon on 16/8/3.
 */
@Configuration
@Import(SecurityConfigurer.class)
public class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }
}
