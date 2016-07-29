package com.dragon.study.spring.boot.jersey.test;


import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;
import com.dragon.study.spring.boot.hibernate.repository.AddressDetailRepository;
import com.dragon.study.spring.boot.hibernate.repository.BasicInfoRepository;
import com.dragon.study.spring.boot.hibernate.utils.EncryptUtils;
import com.dragon.study.spring.boot.jersey.Application;

import org.junit.Assert;
import org.junit.Before;
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
  private BasicInfoRepository basicInfoRepository;

  @Autowired
  private AddressDetailRepository addressDetailRepository;

  @Before
  public void clearTable() throws Exception {
    basicInfoRepository.deleteAll();
    addressDetailRepository.deleteAll();
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
    ResponseEntity<String> response = template.exchange("http://127.0.0.1:8088/jersey/spring-boot/register", HttpMethod.POST, httpEntity, String.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    String isSuccess = response.getBody();
    Assert.assertEquals(isSuccess, "success");

    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);

    //test cache
    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);
  }

  private void testQueryPerson(String phone, String email, String password, boolean hasSleep) throws Exception {
    ResponseEntity<PersonBasicInfo> responseEntity = template.getForEntity("http://127.0.0.1:8088/jersey/spring-boot/queryPerson/" + phone, PersonBasicInfo.class);
    HttpStatus status = responseEntity.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    PersonBasicInfo body = responseEntity.getBody();

    Assert.assertEquals(body.getPhone(), phone);
    Assert.assertEquals(body.getEmail(), email);
    Assert.assertEquals(body.getPassword(), EncryptUtils.encryptMD5(password));
    if(hasSleep) {
      Assert.assertEquals(body.getUpdateDate().getTime() - body.getCreateDate().getTime() > 8000, true);
    }
  }


}
