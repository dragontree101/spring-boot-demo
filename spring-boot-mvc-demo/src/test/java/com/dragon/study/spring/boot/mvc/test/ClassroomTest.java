package com.dragon.study.spring.boot.mvc.test;

import com.alibaba.fastjson.JSON;
import com.dragon.study.spring.boot.mvc.Application;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;
import com.dragon.study.spring.boot.mvc.model.ClassroomModel;
import com.dragon.study.spring.boot.mvc.model.PersonModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by dragon on 16/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassroomTest {

  @Autowired
  TestRestTemplate template;

  @Test
  public void testTeacherName() throws Exception {
    ResponseEntity responseEntity = template
        .getForEntity("/spring-boot/classroom/teacherName?grade=1&classroom=10",
            String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    String body = (String) responseEntity.getBody();
    Assert.assertEquals(body.length(), UUID.randomUUID().toString().length());
  }

  @Test
  public void testErrorGrade() throws Exception {
    ResponseEntity responseEntity = template
        .getForEntity("/spring-boot/classroom/teacherName?grade=1&classroom=10000",
            String.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is4xxClientError());
    String body = (String)responseEntity.getBody();
    MvcExceptionModel model = JSON.parseObject(body, MvcExceptionModel.class);
    Assert.assertEquals(model.getHttpCode(), 400);
    Assert.assertEquals(model.getErrorCode(), 4000001);
    Assert.assertEquals(model.getErrorMsg(), "Parameter error!");
    Assert.assertEquals(model.getDetailMsg(), "班级最大只能99");
  }

  @Test
  public void testClassroom() throws Exception {
    ResponseEntity<ClassroomModel> responseEntity = template
        .getForEntity("/spring-boot/classroom/classroom/1/10", ClassroomModel.class);
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
        .getForEntity("/spring-boot/classroom/topTen?grade=1&classroom=10",
            PersonModel[].class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    List<PersonModel> body = Arrays.asList(responseEntity.getBody());
    Assert.assertEquals(body.size(), 10);
  }

  @Test
  public void testBiggerThan60() throws Exception {
    ResponseEntity<PersonModel[]> responseEntity = template
        .getForEntity("/spring-boot/classroom/biggerThan60?grade=1&classroom=10",
            PersonModel[].class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    List<PersonModel> body = Arrays.asList(responseEntity.getBody());
    Assert.assertEquals(body.stream().filter(b -> b.getScore() < 60).count(), 0);

  }
}
