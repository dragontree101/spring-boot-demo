package com.dragon.study.spring.boot.jersey.exception.common;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
public class MvcExceptionModel {
  @JSONField(name = "http_code")
  int httpCode;
  @JSONField(name = "error_code")
  int errorCode;
  @JSONField(name = "error_msg")
  String errorMsg;
  @JSONField(name = "display_msg")
  String detailMsg;
  @JSONField(name = "request_uri")
  String requestUri;
}
