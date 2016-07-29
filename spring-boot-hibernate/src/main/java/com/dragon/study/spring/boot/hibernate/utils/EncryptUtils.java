package com.dragon.study.spring.boot.hibernate.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by dragon on 16/7/12.
 */
public class EncryptUtils {

  public static String encryptMD5(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      return new BigInteger(1, md.digest()).toString(16);
    } catch (Exception e) {
      throw new RuntimeException("MD5加密出现错误");
    }
  }


}
