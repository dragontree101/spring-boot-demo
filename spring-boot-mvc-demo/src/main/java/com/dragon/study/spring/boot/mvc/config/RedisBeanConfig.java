package com.dragon.study.spring.boot.mvc.config;

import com.dragon.study.spring.boot.redis.RedisCacheConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dragon on 16/7/18.
 */
@Configuration
@Import(RedisCacheConfiguration.class)
public class RedisBeanConfig {
}
