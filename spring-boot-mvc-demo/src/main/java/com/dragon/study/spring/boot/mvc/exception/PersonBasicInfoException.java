package com.dragon.study.spring.boot.mvc.exception;

import com.dragon.study.spring.boot.mvc.exception.common.MvcException;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionFactor;
import com.dragon.study.spring.boot.mvc.exception.common.MvcExceptionModel;

/**
 * Created by dragon on 16/7/12.
 */
public class PersonBasicInfoException extends MvcException {

  public enum Exception implements MvcExceptionFactor {
    NO_PHONE_FAILURE(10001, "没有输入电话号码"),;

    private int index;
    private String reason;
    private String detailMsg;

    Exception(int index, String reason) {
      this.index = index;
      this.reason = reason;
      this.detailMsg = reason;
    }

    public MvcExceptionModel getExModel() {
      MvcExceptionModel model = new MvcExceptionModel();
      model.setHttpCode(404);
      model.setErrorCode(index);
      model.setErrorMsg(reason);
      model.setDetailMsg(detailMsg);
      return model;
    }
  }

  public PersonBasicInfoException(Exception error) {
    super(error);
  }
}
