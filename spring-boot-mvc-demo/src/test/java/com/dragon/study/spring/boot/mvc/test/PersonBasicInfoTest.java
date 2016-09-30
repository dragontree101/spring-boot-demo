package com.dragon.study.spring.boot.mvc.test;

import com.dragon.study.spring.boot.jdbc.dao.PersonAddressDetailInfoDao;
import com.dragon.study.spring.boot.jdbc.dao.PersonBasicInfoDao;
import com.dragon.study.spring.boot.jdbc.module.PersonBasicInfo;
import com.dragon.study.spring.boot.mvc.Application;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;
import com.dragon.study.spring.boot.mvc.model.CommonResponse;
import com.dragon.study.spring.boot.mvc.utils.EncryptUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

/**
 * Created by dragon on 16/7/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonBasicInfoTest {

  @Autowired
  TestRestTemplate template;

  @Autowired
  private PersonBasicInfoDao personBasicInfoDao;

  @Autowired
  private PersonAddressDetailInfoDao personAddressDetailInfoDao;

  @Autowired
  private RedisTemplate redisTemplate;

  @Before
  public void clearTable() throws Exception {
    personBasicInfoDao.truncatePersonBasicInfoTable();
    personAddressDetailInfoDao.truncatePersonAddressDetailInfoTable();
    redisTemplate.getConnectionFactory().getConnection().flushDb();
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
    ResponseEntity<CommonResponse> response = template
        .exchange("/mvc/spring-boot/person/register", HttpMethod.POST, httpEntity,
            CommonResponse.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    boolean isSuccess = response.getBody().getResult().isSuccess();
    Assert.assertEquals(isSuccess, true);

    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);

    //test cache
    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);
  }

  @Test
  public void testRegisterWithCountry() throws Exception {
    MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("phone", "18507313226");
    bodyMap.add("email", "dragonlong1986@126.com");
    bodyMap.add("password", "longlong0");
    bodyMap.add("country", "中华人民共和国");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);
    ResponseEntity<CommonResponse> response = template
        .exchange("/mvc/spring-boot/person/register", HttpMethod.POST, httpEntity,
            CommonResponse.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    boolean isSuccess = response.getBody().getResult().isSuccess();
    Assert.assertEquals(isSuccess, true);

    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);

    //test cache
    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);
  }

  @Test
  public void testRegisterWithErrorCountry() throws Exception {
    MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("phone", "18507313226");
    bodyMap.add("email", "dragonlong1986@126.com");
    bodyMap.add("password", "longlong0");
    bodyMap.add("country", "中华人民共和国中华人民共和国中华人民共和国中华人民共和国中华人民共和国");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);
    ResponseEntity<MvcExceptionModel> response = template
        .exchange("/mvc/spring-boot/person/register", HttpMethod.POST, httpEntity,
            MvcExceptionModel.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertEquals(status.value(), 500);

    MvcExceptionModel body = response.getBody();
    Assert.assertEquals(body.getErrorCode(), 5000001);
    Assert.assertEquals(body.getHttpCode(), 500);
    Assert.assertEquals(body.getErrorMsg(), "Service internal error!");
    Assert.assertEquals(body.getDetailMsg(), "");
    Assert.assertNull(body.getRequestUri());

    testNoQueryPerson();
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
    ResponseEntity<MvcExceptionModel> response = template
        .exchange("/mvc/spring-boot/person/register", HttpMethod.POST, httpEntity,
            MvcExceptionModel.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertEquals(status.value(), 404);

    MvcExceptionModel body = response.getBody();
    Assert.assertEquals(body.getErrorCode(), 10001);
    Assert.assertEquals(body.getHttpCode(), 404);
    Assert.assertEquals(body.getDetailMsg(), "没有输入电话号码");
    Assert.assertEquals(body.getErrorMsg(), "没有输入电话号码");
    Assert.assertNull(body.getRequestUri());
  }

  @Test
  public void testNoQueryPerson() throws Exception {
    ResponseEntity<MvcExceptionModel> responseEntity = template
        .getForEntity("/mvc/spring-boot/person/queryPerson/18507313227",
            MvcExceptionModel.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertEquals(status.value(), 404);

    MvcExceptionModel body = responseEntity.getBody();
    Assert.assertEquals(body.getErrorCode(), 20001);
    Assert.assertEquals(body.getHttpCode(), 404);
    Assert.assertEquals(body.getDetailMsg(), "查找的用户不存在");
    Assert.assertEquals(body.getErrorMsg(), "查找的用户不存在");
    Assert.assertNull(body.getRequestUri());
  }

  private void testQueryPerson(String phone, String email, String password, boolean hasSleep)
      throws Exception {
    ResponseEntity<PersonBasicInfo> responseEntity = template
        .getForEntity("/mvc/spring-boot/person/queryPerson/" + phone,
            PersonBasicInfo.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    PersonBasicInfo body = responseEntity.getBody();

    Assert.assertEquals(body.getPhone(), phone);
    Assert.assertEquals(body.getEmail(), email);
    Assert.assertEquals(body.getPassword(), EncryptUtils.encryptMD5(password));
    if (hasSleep) {
      Assert.assertEquals(body.getUpdateDate().getTime() - body.getCreateDate().getTime() > 8000,
          true);
    }
  }

  @Test
  public void testUpdatePersonBasicInfo() throws Exception {
    testRegister();

    Thread.sleep(10000);

    MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("phone", "18507313226");
    bodyMap.add("email", "dragontree101@souhu.com");
    bodyMap.add("password", "longlong1");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodyMap, headers);
    ResponseEntity<CommonResponse> response = template
        .exchange("/mvc/spring-boot/person/updateInfo", HttpMethod.POST, httpEntity,
            CommonResponse.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    boolean isSuccess = response.getBody().getResult().isSuccess();
    Assert.assertEquals(isSuccess, true);

    testQueryPerson("18507313226", "dragontree101@souhu.com", "longlong1", true);
  }
}
