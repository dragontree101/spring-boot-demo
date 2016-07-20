package com.dragon.study.spring.boot.mvc.exception.common;

import lombok.Getter;

/**
 * Created by dragon on 16/7/12.
 */
@Getter
public class MvcException extends RuntimeException {

  private MvcExceptionFactor exceptionFactor = CommonMvcExceptionFactor.INTERNAL_ERROR;

  public MvcException() {
  }

  public MvcException(MvcExceptionFactor exceptionFactor) {
    this.exceptionFactor = exceptionFactor;
  }
}
