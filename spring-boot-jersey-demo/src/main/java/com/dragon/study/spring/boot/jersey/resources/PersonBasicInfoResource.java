package com.dragon.study.spring.boot.jersey.resources;

import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;
import com.dragon.study.spring.boot.jersey.service.IHelloJerseyService;
import com.dragon.study.spring.boot.jersey.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/29.
 */
@Path("/spring-boot")
@Slf4j
public class PersonBasicInfoResource {

  @Autowired
  IPersonBasicInfoService personBasicInfoService;

  @Path("/register")
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public String register(
      @FormParam("phone")
      String phone,
      @FormParam("country")
      String country) {
    PersonBasicInfo personBasicInfo = new PersonBasicInfo();
    personBasicInfo.setPhone(phone);
    personBasicInfoService.registerPerson(personBasicInfo, country);
    return "success";
  }

  @Path("/queryPerson")
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public String queryPerson(
      @QueryParam("phone")
      String phone) {
    personBasicInfoService.queryPersonBasicInfo(phone);
    return "success";
  }

}
