package com.dragon.study.spring.boot.mvc.test;

import com.dragon.study.spring.boot.mvc.Application;
import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;

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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by dragon on 16/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ClassroomTest {

  RestTemplate template = new TestRestTemplate();

  @Test
  public void testTeacherName() throws Exception {
    ResponseEntity responseEntity = template
        .getForEntity("http://127.0.0.1:8088/mvc/spring-boot/teacherName?grade=1&classroom=10",
            String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    String body = (String) responseEntity.getBody();
    Assert.assertEquals(body.length(), UUID.randomUUID().toString().length());
  }

  @Test
  public void testClassroom() throws Exception {
    ResponseEntity<ClassroomModel> responseEntity = template
        .getForEntity("http://127.0.0.1:8088/mvc/spring-boot/classroom/1/10", ClassroomModel.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    ClassroomModel body = responseEntity.getBody();
    Assert.assertEquals(body.getGrade(), 1);
    Assert.assertEquals(body.getClassroomNumber(), 10);
    Assert.assertEquals(body.getPersonModels().size(), 40);
  }

  @Test
  public void testTopTen() throws Exception {
    ResponseEntity<PersonModel[]> responseEntity = template
        .getForEntity("http://127.0.0.1:8088/mvc/spring-boot/topTen?grade=1&classroom=10",
            PersonModel[].class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    List<PersonModel> body = Arrays.asList(responseEntity.getBody());
    Assert.assertEquals(body.size(), 10);

  }

  @Test
  public void testBiggerThan60() throws Exception {
    ResponseEntity<PersonModel[]> responseEntity = template
        .getForEntity("http://127.0.0.1:8088/mvc/spring-boot/biggerThan60?grade=1&classroom=10",
            PersonModel[].class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    List<PersonModel> body = Arrays.asList(responseEntity.getBody());
    Assert.assertEquals(body.stream().filter(b -> b.getScore() < 60).count(), 0);

  }
}
