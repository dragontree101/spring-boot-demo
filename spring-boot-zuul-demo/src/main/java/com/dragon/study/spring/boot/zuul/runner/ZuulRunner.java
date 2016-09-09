package com.dragon.study.spring.boot.zuul.runner;

import com.dragon.study.spring.boot.zuul.filter.DemoPostFilter;
import com.dragon.study.spring.boot.zuul.filter.DemoPreFilter;
import com.dragon.study.spring.boot.zuul.filter.DemoRouteFilter;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.monitoring.MonitoringHelper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory;
import org.springframework.stereotype.Component;

/**
 * Created by dragon on 16/9/9.
 */
//@Component
public class ZuulRunner {

//  @Override
  public void run(String... strings) throws Exception {
    MonitoringHelper.initMocks();
    initJavaFilters();
  }

  private void initJavaFilters() {
    final FilterRegistry registry = FilterRegistry.instance();
    DemoPreFilter preFilter = new DemoPreFilter();
    registry.put(preFilter.getFilterName(), preFilter);

//    DemoRouteFilter routeFilter = new DemoRouteFilter();
    HttpClientRibbonCommandFactory httpClientRibbonCommandFactory = new HttpClientRibbonCommandFactory(new SpringClientFactory());
    RibbonRoutingFilter ribbonRoutingFilter = new RibbonRoutingFilter(httpClientRibbonCommandFactory);
    registry.put("demoRouteFilter", ribbonRoutingFilter);

    DemoPostFilter postFilter = new DemoPostFilter();
    registry.put(postFilter.getFilterName(), postFilter);


  }
}
