package org.ashone.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * 通过lettuce操作Redis
 */
public class Connection {

  static StatefulRedisConnection getConnection(String host, int port) {
    RedisURI redisURI = RedisURI.create("redis://" + host + ":" + port + "");
    RedisClient client = RedisClient.create(redisURI);
    return client.connect();
  }

  static StatefulRedisConnection getConnection(String host, int port, String password) {
    RedisURI redisURI = RedisURI.create("redis://" + host + ":" + port + "");
    redisURI.setPassword(password);
    RedisClient client = RedisClient.create(redisURI);
    return client.connect();
  }

  public static RedisCommands<String, String> getSyncCommands(StatefulRedisConnection statefulRedisConnection) {
    return statefulRedisConnection.sync();
  }

  public static RedisAsyncCommands getAsyncCommands(StatefulRedisConnection statefulRedisConnection) {
    return statefulRedisConnection.async();
  }
}
