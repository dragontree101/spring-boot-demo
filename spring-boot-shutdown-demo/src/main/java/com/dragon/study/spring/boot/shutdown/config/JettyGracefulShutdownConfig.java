package com.dragon.study.spring.boot.shutdown.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import javax.servlet.Servlet;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 2017/2/17.
 */
@Configuration
@ConditionalOnClass({Servlet.class, Server.class})
@Slf4j
public class JettyGracefulShutdownConfig {

  @Value("${spring.shutdown.waitTime:20000}")
  private int waitTime;

  @Bean
  public JettyGracefulShutdown jettyGracefulShutdown() {
    log.info("init jetty graceful shutdown");
    return new JettyGracefulShutdown(waitTime);
  }

  @Bean
  public EmbeddedServletContainerCustomizer jettyCustomizer() {
    return (container) -> {
      if (container instanceof JettyEmbeddedServletContainerFactory) {
        ((JettyEmbeddedServletContainerFactory) container)
            .addServerCustomizers(jettyGracefulShutdown());
      }
    };
  }


  private static class JettyGracefulShutdown
      implements JettyServerCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Server server;

    private final int waitTime;

    public JettyGracefulShutdown(int waitTime)  {
      this.waitTime = waitTime;
    }

    @Override
    public void customize(Server server) {
      this.server = server;
      log.info("Jetty version: " + server.getVersion());

      // Configure shutdown wait time.
      if (this.waitTime > 0) {
        // Add StatsticsHandler, in order for graceful shutdown to work.
        StatisticsHandler handler = new StatisticsHandler();
        handler.setHandler(server.getHandler());
        server.setHandler(handler);

        log.info("Shutdown wait time: " + this.waitTime + "s");
        server.setStopTimeout(this.waitTime);

        // We will stop it through JettyGracefulShutdownConfig class.
        server.setStopAtShutdown(false);
      }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
      if (server == null) {
        log.error("Jetty server variable is null, this should not happen!");
        return;
      }
      log.info("Entering shutdown for Jetty.");
      if (!(server.getHandler() instanceof StatisticsHandler)) {
        log.error("Root handler is not StatisticsHandler, graceful shutdown may not work at all!");
      } else {
        log.info(
            "Active requests: " + ((StatisticsHandler) server.getHandler()).getRequestsActive());
      }
      try {
        long begin = System.currentTimeMillis();
        server.stop();
        log.info("Shutdown took " + (System.currentTimeMillis() - begin) + " ms.");
      } catch (Exception e) {
        log.error("Fail to shutdown gracefully.", e);
      }
    }
  }
}
