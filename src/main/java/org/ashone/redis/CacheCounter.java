package org.ashone.redis;

import org.ashone.BaseConstance;

import java.util.ArrayList;

public class CacheCounter {

  private ArrayList<Long> precisions = new ArrayList<Long>();

  CacheCounter() {
    precisions.add(BaseConstance.DAY_MILLIS);
    precisions.add(BaseConstance.HOUR_MILLIS);
    precisions.add(BaseConstance.MINUTE_MILLIS);
    precisions.add(BaseConstance.MONTH_MILLIS);
  }

  /**
   * 为计数器新增自定义时间分片单位
   * @param precision 时间分片大小，单位为毫秒
   */
  public void setPrecision(Long precision) {
    precisions.add(precision);
  }

  // 计数增加
  public static void plusCount(String name, int count) {
    long Millis = System.currentTimeMillis();

  }
}
