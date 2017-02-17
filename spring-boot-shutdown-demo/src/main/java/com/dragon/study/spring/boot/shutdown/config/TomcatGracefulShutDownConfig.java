package com.dragon.study.spring.boot.shutdown.config;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.servlet.Servlet;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 2017/2/17.
 */
@Configuration
@ConditionalOnClass({Servlet.class, Tomcat.class })
@Slf4j
public class TomcatGracefulShutDownConfig {

  @Value("${spring.shutdown.waitTime:20000}")
  private int waitTime;

  @Bean
  public TomcatGracefulShutdown tomcatGracefulShutdown() {
    log.info("init tomcat graceful shutdown");
    return new TomcatGracefulShutdown(waitTime);
  }

  @Bean
  public EmbeddedServletContainerCustomizer tomcatCustomizer() {
    return (container) -> {
        if (container instanceof TomcatEmbeddedServletContainerFactory) {
          ((TomcatEmbeddedServletContainerFactory) container)
              .addConnectorCustomizers(tomcatGracefulShutdown());
        }
    };
  }

  private static class TomcatGracefulShutdown
      implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private final int waitTime;

    private volatile Connector connector;

    public TomcatGracefulShutdown(int waitTime) {
      this.waitTime = waitTime;
    }

    @Override
    public void customize(Connector connector) {
      this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
      this.connector.pause();
      Executor executor = this.connector.getProtocolHandler().getExecutor();
      if (executor instanceof ThreadPoolExecutor) {
        try {
          ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
          threadPoolExecutor.shutdown();
          log.info("Active requests: {}", threadPoolExecutor.getActiveCount());
          if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.MILLISECONDS)) {
            log.error(
                "Tomcat thread pool did not shut down gracefully within 30 seconds. Proceeding with forceful shutdown");
          }
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}
