package org.ashone.redis;

import com.lambdaworks.redis.Range;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * <p>
 * 限流器：1. 避免垃圾请求 2.当系统压力过大阻止继续施压
 * </P>
 */
public class FlowLimitation {
  private RedisCommands<String, String> commands;

  public FlowLimitation(String host, int port) {
    commands = Connection.getSyncCommands(Connection.getConnection(host, port));
  }

  public FlowLimitation(String host, int port, String password) {
    commands = Connection.getSyncCommands(Connection.getConnection(host, port, password));
  }

  /**
   * <p>
   * 判断用户能否执行某项操作
   * 适用于较小流量的判断工作，否则会大量消耗内存空间
   * </P>
   *
   * @param principal       用户主体
   * @param actionKey       动作，每个操作对应唯一的动作Str
   * @param period          时间分片，单位为秒
   * @param allowedMaxCount 允许最大操作次数
   * @return true表示允许执行动作，反之则不允许
   */
  public boolean isActionAllowed(String principal, String actionKey, int period, int allowedMaxCount) {
    String key = String.format("hist:%s:%s", principal, actionKey);
    long now = System.currentTimeMillis();
    // 添加一条动作
    commands.zadd(key, now, now + "");
    // 清除时间窗口外的动作记录
    commands.zremrangebyscore(key, Range.create(0, now - period * 1000));
    // 获取窗口内的记录数量
    Long count = commands.zcard(key);
    commands.expire(key, period + 1);
    return count <= allowedMaxCount;
  }
}
