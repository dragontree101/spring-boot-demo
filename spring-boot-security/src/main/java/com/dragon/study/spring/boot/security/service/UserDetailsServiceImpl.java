package com.dragon.study.spring.boot.security.service;

import com.dragon.study.spring.boot.security.dao.SecurityRoleDao;
import com.dragon.study.spring.boot.security.dao.SecurityUserDao;
import com.dragon.study.spring.boot.security.dao.module.SecurityRole;
import com.dragon.study.spring.boot.security.dao.module.SecurityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/8/1.
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private SecurityUserDao userDao;

  @Autowired
  private SecurityRoleDao roleDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SecurityUser user = userDao.getUser(username);
    if (user == null) {
      log.warn("user is null, user name is {}", username);
      return null;
    }
    String password = user.getPassword();

    List<SecurityRole> roles = roleDao.getRoles(username);
    if (roles == null || roles.isEmpty()) {
      log.warn("role is null or empty, user name is {}", username);
      return null;
    }

    List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(
        roles.stream().map(r -> r.getRole()).collect(Collectors.joining(",")));

    return new User(username, password, auth);
  }
}
