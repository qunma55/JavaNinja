package org.ashone.redis;

public class Funnel {
  private int capacity;// 容量
  private double leakingRate; // 流量速率
  private int leftQuota;  // 剩余空间
  long leakingTs; // 上一次漏水时间
}
