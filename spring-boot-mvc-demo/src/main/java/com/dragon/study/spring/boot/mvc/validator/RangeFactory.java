package com.dragon.study.spring.boot.mvc.validator;


import com.google.common.base.Strings;

public class RangeFactory {

  public static ParamRange getRangeInstance(String range) {
    if (Strings.isNullOrEmpty(range)) {
      return null;
    }
    String[] arr = range.split(":", 2);
    if (arr.length != 2) {
      throw new IllegalArgumentException("invalid range define:" + range);
    }
    ParamRange result = null;

    if (arr[0].equals("int")) {
      result = new IntRange(arr[1].trim());
    }
    return result;
  }

}
