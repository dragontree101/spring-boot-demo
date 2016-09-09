package com.dragon.study.spring.boot.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/9/9.
 */
@Data
@Slf4j
public class DemoPostFilter extends ZuulFilter {

  private String filterName = "demoPostFilter";
  private int order = 1000;

  @Override
  public String filterType() {
    return "post";
  }

  @Override
  public int filterOrder() {
    return this.order;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    log.info("running demoPostFilter");
    log.info(RequestContext.getCurrentContext().get("name").toString());
    return null;
  }
}
