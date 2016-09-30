package com.dragon.study.spring.boot.zuul.runner;

import com.dragon.study.spring.boot.zuul.filter.DemoPostFilter;
import com.dragon.study.spring.boot.zuul.filter.DemoPreFilter;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.monitoring.MonitoringHelper;

import org.springframework.stereotype.Component;

/**
 * Created by dragon on 16/9/9.
 */
@Component
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
//    SimpleHostRoutingFilter simpleHostRoutingFilter = new SimpleHostRoutingFilter(new ProxyRequestHelper(), new);
//    RibbonRoutingFilter ribbonRoutingFilter = new RibbonRoutingFilter(httpClientRibbonCommandFactory);
//    registry.put("demoRouteFilter", ribbonRoutingFilter);

    DemoPostFilter postFilter = new DemoPostFilter();
    registry.put(postFilter.getFilterName(), postFilter);


  }
}
