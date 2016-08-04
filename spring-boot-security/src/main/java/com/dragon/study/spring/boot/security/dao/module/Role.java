package com.dragon.study.spring.boot.security.dao.module;

import com.google.common.base.MoreObjects;

/**
 * Created by dragon on 16/8/1.
 */
public enum Role {
  ADMIN("管理员", 0),
  DEVELOPER("开发者", 1),;

  private final String name;
  private final int index;

  Role(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public static Role valueByIndex(int index) {
    switch (index) {
      case 0:
        return ADMIN;
      case 1:
        return DEVELOPER;
      default:
        return null;
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).
        add("name", name).
        add("index", index).
        toString();
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }
}
