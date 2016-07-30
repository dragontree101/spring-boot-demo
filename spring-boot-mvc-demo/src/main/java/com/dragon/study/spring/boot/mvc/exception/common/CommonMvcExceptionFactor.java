package com.dragon.study.spring.boot.mvc.exception.common;

/**
 * Created by dragon on 16/7/12.
 */
public enum CommonMvcExceptionFactor implements MvcExceptionFactor {
  PARAM_ERROR(400, 4000001, "Parameter error!", ""),
  PARAM_MISSING(400, 4000002, "Parameter missing!", ""),
  URL_NOT_FOUND(404, 4040000, "Url not found!", ""),
  HTTP_METHOD_NOT_ALLOWED(405, 4050001, "Http method not allow!", ""),
  INTERNAL_ERROR(500, 5000001, "Service internal error!", ""),;

  int httpCode;
  int errCode;
  String errorMsg;
  String detailMsg;

  CommonMvcExceptionFactor(int httpCode, int errCode, String errorMsg, String detailMsg) {
    this.httpCode = httpCode;
    this.errCode = errCode;
    this.errorMsg = errorMsg;
    this.detailMsg = detailMsg;
  }

  public MvcExceptionModel getExModel() {
    MvcExceptionModel model = new MvcExceptionModel();
    model.setHttpCode(this.httpCode);
    model.setErrorCode(this.errCode);
    model.setErrorMsg(this.errorMsg);
    model.setDetailMsg(this.detailMsg);
    return model;
  }

}
