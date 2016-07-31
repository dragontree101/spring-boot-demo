package com.dragon.study.spring.boot.jersey.exception;

import com.google.common.base.Strings;

import com.alibaba.fastjson.JSON;
import com.dragon.study.spring.boot.jersey.exception.common.MvcException;
import com.dragon.study.spring.boot.jersey.exception.common.MvcExceptionFactor;
import com.dragon.study.spring.boot.jersey.exception.common.MvcExceptionModel;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/31.
 */
@Slf4j
@Provider
public class JerseyExceptionMapper implements ExceptionMapper<Throwable> {

  @Context
  private HttpServletRequest request;

  @Override
  public Response toResponse(Throwable exception) {
    MvcExceptionFactor exceptionFactor;
    if (exception instanceof MvcException) {
      exceptionFactor = ((MvcException) exception).getExceptionFactor();
    } else {
      log.error(exception.getMessage(), exception);
      exceptionFactor = new MvcException().getExceptionFactor();
    }


    return buildResponseFromExceptionFactor(exceptionFactor, exception.getMessage());
  }

  private Response buildResponseFromExceptionFactor(MvcExceptionFactor factor, String errMsg) {
    MvcExceptionModel exModel = factor.getExModel();
    if (errMsg != null) {
      exModel.setErrorMsg(errMsg);
    }
    String uri = request.getPathInfo();
    Response.ResponseBuilder responseBuilder = Response.status(exModel.getHttpCode());
    responseBuilder.type(MediaType.APPLICATION_JSON);
    exModel.setRequestUri(uri);
    String entity = JSON.toJSONString(exModel);
    responseBuilder.entity(entity);
    Response response = responseBuilder.build();
    return response;
  }
}
