package com.dragon.study.springboot.app1.controller;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.dragon.study.springboot.app2.bean.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by dragon on 16/5/14.
 */
@RestController
@EnableScheduling
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private MetricRegistry registry;

  @Autowired
  private Person dragon;

  @Autowired
  private Person monkey;

  @Value("${refresh.value:unknown}")
  private String refreshValue;

  private Map<String, Counter> counterMap = new HashMap<>();

  private Map<String, Histogram> histogramMap = new HashMap<>();

  private int index = 0;


  @RequestMapping("/dragon")
  public String dragon() {
    Random random = new Random();
    registry.histogram("dragon_histogram").update((index++/10 + 1) * random.nextInt(100));
    return dragon.toString() + "---" + refreshValue;
  }

  @RequestMapping("/monkey")
  public String monkey() {
    Random random = new Random();
    registry.histogram("monkey_histogram").update(random.nextInt(100));
    return monkey.toString();
  }

  @RequestMapping("/counter")
  public String counter() {
    counterMap.forEach(
        (k, v) -> System.out.println("counter key is " + k + ", value is " + v.getCount()));

    histogramMap.forEach((k, v) -> System.out.println(
        "histograms key is " + k + ", mean value is " + v.getSnapshot()
            .getMean() + ", median value is " + v.getSnapshot().getMedian()
            + ", 95th value is " + v.getSnapshot().get95thPercentile()));
    return "counter ok";
  }

  @Scheduled(initialDelay = 10000L, fixedDelay = 10000L)
  public void scheduledCounter() {
    counterMap = registry.getCounters();
    registry.removeMatching(new MetricFilter() {
      @Override
      public boolean matches(String s, Metric metric) {
        if (metric instanceof Counter) {
          return true;
        }
        return false;
      }
    });
  }

  @Scheduled(initialDelay = 10000L, fixedDelay = 10000L)
  public void scheduledHistogram() {
    histogramMap = registry.getHistograms();
    registry.removeMatching(new MetricFilter() {
      @Override
      public boolean matches(String s, Metric metric) {
        if (metric instanceof Histogram) {
          return true;
        }
        return false;
      }
    });
  }


}
