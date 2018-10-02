package org.ashone.redis;

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
   * </P>
   *
   * @param principal       用户主体
   * @param actionKey       动作
   * @param period          时间分片
   * @param allowedMaxCount 允许最大操作次数
   * @return true表示允许执行动作，反之则不允许
   */
  public boolean isActionAllowed(String principal, String actionKey, int period, int allowedMaxCount) {

    return true;
  }
}
