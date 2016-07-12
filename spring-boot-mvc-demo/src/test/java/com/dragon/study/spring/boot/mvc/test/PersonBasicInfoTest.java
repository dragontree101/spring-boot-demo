package com.dragon.study.spring.boot.mvc.test;

import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;
import com.dragon.study.spring.boot.mvc.Application;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;
import com.dragon.study.spring.boot.mvc.model.CommonResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by dragon on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class PersonBasicInfoTest {

  RestTemplate template = new TestRestTemplate();

  @Autowired
  private PersonBasicInfoDao personBasicInfoDao;

  @Before
  public void clearTable() throws Exception {
    personBasicInfoDao.truncatePersonBasicInfoTable();
  }

  @Test
  public void testRegister() throws Exception {
    MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("phone", "18507313226");
    bodyMap.add("email", "dragonlong1986@126.com");
    bodyMap.add("password", "longlong0");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);
    ResponseEntity<CommonResponse> response = template.exchange("http://127.0.0.1:8088/mvc/spring-boot/register", HttpMethod.POST, httpEntity, CommonResponse.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    boolean isSuccess = response.getBody().getResult().isSuccess();
    Assert.assertEquals(isSuccess, true);
  }

  @Test
  public void testRegisterNoPhone() throws Exception {
    MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("email", "dragonlong1986@126.com");
    bodyMap.add("password", "longlong0");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);
    ResponseEntity<MvcExceptionModel> response = template.exchange("http://127.0.0.1:8088/mvc/spring-boot/register", HttpMethod.POST, httpEntity, MvcExceptionModel.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertEquals(status.value(), 404);

    MvcExceptionModel body = response.getBody();
    Assert.assertEquals(body.getErrorCode(), 10001);
    Assert.assertEquals(body.getHttpCode(), 404);
    Assert.assertEquals(body.getDetailMsg(), "没有输入电话号码");
    Assert.assertEquals(body.getErrorMsg(), "没有输入电话号码");
    Assert.assertNull(body.getRequestUri());
  }
}
