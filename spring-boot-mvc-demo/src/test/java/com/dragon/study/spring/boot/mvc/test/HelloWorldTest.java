package com.dragon.study.spring.boot.mvc.test;

import com.dragon.study.spring.boot.mvc.Application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dragon on 16/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class HelloWorldTest {

  RestTemplate template = new TestRestTemplate();

  @Test
  public void testHelloWorld() throws Exception {
    ResponseEntity responseEntity = template
        .getForEntity("http://127.0.0.1:8088/mvc/spring-boot/hello-world", String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    String body = (String) responseEntity.getBody();
    Assert.assertEquals(body, "Hello World");

  }
}
