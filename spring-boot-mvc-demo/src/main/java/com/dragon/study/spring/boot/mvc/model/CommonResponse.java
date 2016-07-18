package com.dragon.study.spring.boot.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * Created by dragon on 16/7/12.
 */
@Data
public class CommonResponse {

  public static CommonResponse of(boolean success) {
    CommonResponse apiCommonResponse = new CommonResponse();
    apiCommonResponse.setResult(Result.of(success));
    return apiCommonResponse;
  }

  private Result result;

  @Data
  public static class Result {

    @JSONField(name = "is_success")
    private boolean isSuccess = false;

    public static Result of(boolean isSuccess) {
      Result result = new Result();
      result.setSuccess(isSuccess);
      return result;
    }
  }

}
