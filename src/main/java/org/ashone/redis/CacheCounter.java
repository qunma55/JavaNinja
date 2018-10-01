package org.ashone.redis;

import com.google.common.math.LongMath;
import com.lambdaworks.redis.RedisCommandExecutionException;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.ashone.BaseConstance;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CacheCounter {

  private ArrayList<Long> precisions = new ArrayList<>();
  private RedisCommands<String, String> commands;

  public CacheCounter(String host, int port) {
    commands = Connection.getSyncCommands(Connection.getConnection(host, port));
    initPrecisions();
  }

  public CacheCounter(String host, int port, String password) {
    commands = Connection.getSyncCommands(Connection.getConnection(host, port, password));
    initPrecisions();
  }

  private void initPrecisions() {
    precisions.add(BaseConstance.DAY_MILLIS);
    precisions.add(BaseConstance.HOUR_MILLIS);
    precisions.add(BaseConstance.MINUTE_MILLIS);
    precisions.add(BaseConstance.MONTH_MILLIS);
  }

  /**
   * <p>
   * 为计数器新增自定义时间分片单位
   * </p>
   *
   * @param precision 时间分片大小，单位为秒
   */
  public void setPrecision(Long precision) {
    precisions.add(precision);
  }

  /**
   * <p>
   * 计数器自增
   * </P>
   *
   * @param group 计数分组名称
   * @param tag   计数分组ID
   * @param count 增加数
   */
  public void plusCount(String group, String tag, int count) throws RedisCommandExecutionException {
    Long millis = System.currentTimeMillis();
    for (Long precision : precisions) {
      // 当前所在的时间片
      long precisionNow = LongMath.divide(millis, precision, RoundingMode.DOWN) * precision;
      String key = precision + ":" + tag;
      commands.hincrby(group + ":count:" + key, precisionNow + "", count);
      // 将计数器的引用信息放入有序集合中，将其分值设置为0
      commands.zadd("know:", 0, key);
    }
  }

  /**
   * <p>
   * 得到计数器的值
   * </P>
   *
   * @param group     计数器分组名称
   * @param tag       计数器分组ID
   * @param precision 时间分片
   * @param timestamp 时间戳
   * @return 当前计数器的值
   */
  public long getCounter(String group, String tag, String precision, String timestamp) {
    String key = group + ":count:" + precision + ":" + tag;
    String count = commands.hget(key, timestamp);
    return count != null ? Long.parseLong(count) : 0;
  }

  /**
   * <p>
   * 获取所有计数器的值
   * </P>
   *
   * @param group     计数器分组名称
   * @param tag       计数器分组ID
   * @param precision 时间分片
   * @return 所有计数器的值
   */
  public Map<String, String> getAllCounter(String group, String tag, String precision) {
    String key = group + ":count:" + precision + ":" + tag;
    return commands.hgetall(key);
  }

  public void cleanCounter() {
    
  }
}
