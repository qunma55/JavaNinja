package org.ashone.redis;

import com.google.common.math.LongMath;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.ashone.BaseConstance;

import java.math.RoundingMode;
import java.util.ArrayList;

public class CacheCounter {

  private ArrayList<Long> precisions = new ArrayList<Long>();
  private RedisCommands<String, String> commands;

  CacheCounter(String host, int port) {
    commands = Connection.getSyncCommands(Connection.getConnection(host, port));

    precisions.add(BaseConstance.DAY_MILLIS);
    precisions.add(BaseConstance.HOUR_MILLIS);
    precisions.add(BaseConstance.MINUTE_MILLIS);
    precisions.add(BaseConstance.MONTH_MILLIS);
  }

  /**
   * 为计数器新增自定义时间分片单位
   *
   * @param precision 时间分片大小，单位为毫秒
   */
  public void setPrecision(Long precision) {
    precisions.add(precision);
  }

  /**
   * 计数器自增
   * @param name 计数分组名称
   * @param id 计数分组ID
   * @param count 增加数
   */
  public void plusCount(String name, String id, int count) {
    Long millis = System.currentTimeMillis();
    for (Long precision : precisions) {
      // 当前所在的时间片
      long precisionNow = LongMath.divide(millis, precision, RoundingMode.DOWN);
      String key = precision + ":" + id;
      commands.hincrby(name + ":count:" + key, precisionNow + "", count);
    }
  }
}
