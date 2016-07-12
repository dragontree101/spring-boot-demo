package com.dragon.study.spring.boot.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

  @Getter
  @Setter
  @AllArgsConstructor(staticName = "of")
  public static class Result {

    @JSONField(name = "is_success")
    private boolean isSuccess = false;
  }

}
