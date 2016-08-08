package com.dragon.study.spring.boot.jersey;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.dragon.study.spring.boot.jersey.annotation.EnableJersey;
import com.dragon.study.spring.boot.jersey.exception.JerseyExceptionMapper;
import com.dragon.study.spring.boot.jersey.provider.UnderlineJsonProvider;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 16/7/21.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJersey(
    scanPackage = "com.dragon.study.spring.boot.jersey.resources",
    applicationPath = "/jersey/*",
    componentClasses = {
        RequestContextFilter.class,
        //把驼峰格式字段字符转换成下划线
        UnderlineJsonProvider.class,
        //通过利用fastjson的注解来转换字段
        FastJsonProvider.class,
        //统一异常处理
        JerseyExceptionMapper.class, RolesAllowedDynamicFeature.class,
    })
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
