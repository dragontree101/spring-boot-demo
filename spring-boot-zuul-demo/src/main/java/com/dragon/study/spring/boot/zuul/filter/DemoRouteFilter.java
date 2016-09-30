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
public class DemoRouteFilter extends ZuulFilter {

  private String filterName = "demoRouteFilter";
  private int order = 1000;

  @Override
  public String filterType() {
    return "route";
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
    log.info("running demoRoutingFilter");
//    try {
      RequestContext.getCurrentContext().getResponse();
//    } catch (IOException e) {
//      log.error(e.getMessage(), e);
//    }
    return null;
  }
}
