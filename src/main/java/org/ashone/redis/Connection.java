package org.ashone.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.ashone.redis.model.RedisBean;;

public class Connection {
  /** 获取Redis连接 */
  public static StatefulRedisConnection getConnection() {
    RedisBean redisBean = (RedisBean) SpringUtil.getBean("redisBean");
    assert redisBean != null;
    RedisURI redisURI =
        RedisURI.create("redis://" + redisBean.getHost() + ":" + redisBean.getPort());
    redisURI.setPassword(redisBean.getPassword());
    RedisClient client = RedisClient.create(redisURI);
    return client.connect();
  }

  public static RedisCommands<String, String> getSyncCommands(
      StatefulRedisConnection statefulRedisConnection) {
    return statefulRedisConnection.sync();
  }

  public static RedisAsyncCommands getAsyncCommands(
      StatefulRedisConnection statefulRedisConnection) {
    return statefulRedisConnection.async();
  }
}
