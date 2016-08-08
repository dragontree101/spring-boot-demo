package com.dragon.study.spring.boot.jdbc;


import com.alibaba.druid.pool.DruidDataSource;
import com.dragon.study.spring.boot.jdbc.config.DynamicMysqlProperties;
import com.dragon.study.spring.boot.jdbc.config.JdbcPoolConfig;
import com.dragon.study.spring.boot.jdbc.config.MysqlProperties;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class DynamicDataSourcesConfiguration implements BeanFactoryPostProcessor, EnvironmentAware {
  private ConfigurableEnvironment environment;

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    DynamicMysqlProperties dynamicMysqlProperties = resolverSetting("mysql",
        DynamicMysqlProperties.class);
    if (dynamicMysqlProperties == null) {
      return;
    }
    Map<String, MysqlProperties> entity = dynamicMysqlProperties.getEntity();
    if (CollectionUtils.isEmpty(entity)) {
      return;
    }
    entity.entrySet().forEach(entry -> createBean(beanFactory, entry));
  }

  private void createBean(ConfigurableListableBeanFactory beanFactory,
      Map.Entry<String, MysqlProperties> entry) {
    JdbcPoolConfig jdbcPoolConfig = resolverSetting("mysql.pool", JdbcPoolConfig.class);
    if (jdbcPoolConfig == null) {
      return;
    }
    MysqlProperties mysqlProperties = entry.getValue();
    String prefixName = entry.getKey();
    String url = mysqlProperties.getUrl();
    DruidDataSource dataSource = new DruidDataSource();

    dataSource.setUrl(url);
    dataSource.setUsername(mysqlProperties.getUserName());
    dataSource.setPassword(mysqlProperties.getPassword());

    dataSource.setInitialSize(jdbcPoolConfig.getInitialSize());
    dataSource.setMinIdle(jdbcPoolConfig.getMinIdle());
    dataSource.setMaxActive(jdbcPoolConfig.getMaxActive());
    dataSource.setMaxWait(jdbcPoolConfig.getMaxWait());
    dataSource.setTimeBetweenEvictionRunsMillis(jdbcPoolConfig.getTimeBetweenEvictionRunsMillis());
    dataSource.setMinEvictableIdleTimeMillis(jdbcPoolConfig.getMinEvictableIdleTimeMillis());
    dataSource.setValidationQuery(jdbcPoolConfig.getValidationQuery());
    dataSource.setTestWhileIdle(jdbcPoolConfig.isTestWhileIdle());
    dataSource.setTestOnBorrow(jdbcPoolConfig.isTestOnBorrow());
    dataSource.setTestOnReturn(jdbcPoolConfig.isTestOnReturn());

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
    AnnotationTransactionAspect.aspectOf().setTransactionManager(transactionManager);
    JdbcTemplateFactory jdbcTemplateFactory = new JdbcTemplateFactory(dataSource);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
        jdbcTemplateFactory.getJdbcTemplate());

    register(beanFactory, transactionManager, prefixName + "TransactionManager", prefixName + "Tx");
    register(beanFactory, jdbcTemplateFactory, prefixName + "JdbcTemplateFactory", prefixName);
    register(beanFactory, dataSource, prefixName + "DataSource", prefixName + "Ds");
    register(beanFactory, namedParameterJdbcTemplate, prefixName + "NamedParameterJdbcTemplate",
        prefixName + "Npjt");
  }

  private void register(ConfigurableListableBeanFactory beanFactory, Object bean, String name,
      String alias) {
    beanFactory.registerSingleton(name, bean);
    if (!beanFactory.containsSingleton(alias)) {
      beanFactory.registerAlias(name, alias);
    }
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = (ConfigurableEnvironment) environment;
  }

  private <T> T resolverSetting(String targetName, Class<T> clazz) {
    PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<>(clazz);
    factory.setTargetName(targetName);
    factory.setPropertySources(environment.getPropertySources());
    factory.setConversionService(environment.getConversionService());
    try {
      factory.bindPropertiesToTarget();
      return (T) factory.getObject();
    } catch (BindException ex) {
      throw new FatalBeanException("Could not bind DataSourceSettings properties", ex);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }


}
