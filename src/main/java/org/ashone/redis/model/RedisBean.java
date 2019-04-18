package org.ashone.redis.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisBean")
public class RedisBean {
  @Value("${spring.redis.host}")
  String host;
  @Value("${spring.redis.port}")
  String port;
  @Value("${spring.redis.password}")
  String password;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
