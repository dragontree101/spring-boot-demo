package com.dragon.study.spring.boot.jersey.provider;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * 在Json序列化的时候,将CamelCase命名的变量转换成underscore类型的json属性名
 */
public class UnderlineNameFilter implements NameFilter {

  private static final char UNDERLINE = '_';

  @Override
  public String process(Object object, String name, Object value) {
    StringBuilder propertyName = new StringBuilder(name.length());
    propertyName.append(Character.toLowerCase(name.charAt(0)));
    for (int i = 1; i < name.length(); i++) {
      char c = name.charAt(i);
      if (Character.isUpperCase(c)) {
        propertyName.append(UNDERLINE);
      }
      propertyName.append(Character.toLowerCase(c));
    }
    return propertyName.toString();
  }
}
