package com.dragon.study.spring.boot.mvc.test;

import com.dragon.study.spring.boot.mvc.Application;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dragon on 16/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldTest {

  @Autowired
  TestRestTemplate template;

  @Test
  public void testHelloWorld() throws Exception {
    String plainCreds = "longzhe:longlong0";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64Creds);
    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity responseEntity = template
        .exchange("/mvc/spring-boot/hello-world", HttpMethod.GET, request, String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    String body = (String) responseEntity.getBody();
    Assert.assertEquals(body, "Hello World");

  }

  @Test
  public void testHelloWorldNoAuth() throws Exception {
    String plainCreds = "longzhe:longlong1";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64Creds);
    HttpEntity<String> request = new HttpEntity<>(headers);

    ResponseEntity responseEntity = template
        .exchange("/mvc/spring-boot/hello-world", HttpMethod.GET, request, String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is4xxClientError());
  }
}
