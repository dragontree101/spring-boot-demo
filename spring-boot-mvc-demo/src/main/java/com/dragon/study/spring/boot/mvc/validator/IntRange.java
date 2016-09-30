package com.dragon.study.spring.boot.mvc.validator;


public class IntRange implements ParamRange<Integer> {

  int min = Integer.MIN_VALUE;
  int max = Integer.MAX_VALUE;

  public IntRange(String range) {
    String[] arr = range.split("~");
    this.min = Integer.parseInt(arr[0]);
    this.max = Integer.parseInt(arr[1]);
    if (this.max < this.min) {
      this.max = Integer.MAX_VALUE;
    }
  }

  public IntRange(int min, int max) {
    this.min = min;
    this.max = max;
    if (this.max < this.min) {
      this.max = Integer.MAX_VALUE;
    }
  }

  @Override
  public boolean isInRange(Integer value) {
    return (value >= min && value <= max);
  }

  @Override
  public String getBaseSample() {
    return String.valueOf(getRandomValue());
  }

  private int getRandomValue() {
    int d = rdm.nextInt(max + 1);
    while (!isInRange(d)) {
      d = rdm.nextInt(max + 1);
    }
    return d;
  }

  @Override
  public String getDesc() {
    return min + "~" + max;
  }

  @Override
  public boolean isCompatible(Class<?> type) {
    return type == int.class || type == Integer.class;
  }


}
