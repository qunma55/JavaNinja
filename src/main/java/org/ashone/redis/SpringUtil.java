package org.ashone.redis;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  /** 根据一个bean的id获取配置文件中相应的bean */
  public static Object getBean(String beanId) throws BeansException {
    if (applicationContext.containsBean(beanId)) {
      return applicationContext.getBean(beanId);
    }
    return null;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (SpringUtil.applicationContext == null) {
      SpringUtil.applicationContext = applicationContext;
    }
  }
}
