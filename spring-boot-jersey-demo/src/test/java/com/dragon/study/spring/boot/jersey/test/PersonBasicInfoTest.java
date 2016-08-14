package com.dragon.study.spring.boot.jersey.test;


import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;
import com.dragon.study.spring.boot.hibernate.repository.AddressDetailRepository;
import com.dragon.study.spring.boot.hibernate.repository.BasicInfoRepository;
import com.dragon.study.spring.boot.hibernate.utils.EncryptUtils;
import com.dragon.study.spring.boot.jersey.Application;
import com.dragon.study.spring.boot.jersey.module.CommonResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    ResponseEntity<CommonResponse> response = template
        .exchange("/jersey/spring-boot/register", HttpMethod.POST, httpEntity,
            CommonResponse.class);

    HttpStatus status = response.getStatusCode();
    Assert.assertTrue(status.is2xxSuccessful());

    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);

    //test cache
    testQueryPerson("18507313226", "dragonlong1986@126.com", "longlong0", false);
  }

  private void testQueryPerson(String phone, String email, String password, boolean hasSleep)
      throws Exception {
    ResponseEntity<PersonBasicInfo> responseEntity = template
        .getForEntity("/jersey/spring-boot/queryPerson/" + phone,
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


}
