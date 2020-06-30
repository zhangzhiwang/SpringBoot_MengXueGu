package com.asiainfo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置类
 *
 * @author zhangzhiwang
 * @date Jun 29, 2020 10:55:25 AM
 */
public class TaskConfig {
	/**
	 * 在xml中配置定时任务的方法是：
	 * <task:annotation-driven scheduler="scheduler"/>
	   <task:scheduler id="scheduler" pool-size="5"/>
	   
	   如果不使用xml配置，使用配置类进行配置则需要定义一个加了@Configuration注解的配置类，然后用@Bean的方式定义方法来将定时任务需要配置的bean加载到IOC容器中，或者直接使用@EnableScheduling启动定时任务组件，自动装配所需要的bean而无需任何配置。
	 */
}
