package com.dragon.study.spring.boot.redis;

import com.dragon.study.spring.boot.redis.conf.RedisPoolProperties;
import com.dragon.study.spring.boot.redis.conf.RedisProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by dragon on 16/7/18.
 */
@Configuration
@EnableConfigurationProperties({RedisProperties.class, RedisPoolProperties.class})
@Slf4j
public class RedisConfiguration {

  @Bean
  public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(redisProperties.getHostName());
    jedisConnectionFactory.setPort(redisProperties.getPort());
    jedisConnectionFactory.setPassword(redisProperties.getPassword());
    jedisConnectionFactory.setTimeout(redisProperties.getTimeout());
    jedisConnectionFactory.setDatabase(redisProperties.getDatabase());
    jedisConnectionFactory.setUsePool(redisProperties.isUsePool());
    return jedisConnectionFactory;
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig(RedisPoolProperties redisPoolProperties) {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxTotal(redisPoolProperties.getMaxTotal());
    jedisPoolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
    jedisPoolConfig.setMaxWaitMillis(redisPoolProperties.getMaxWaitMillis());
    return jedisPoolConfig;
  }
}
