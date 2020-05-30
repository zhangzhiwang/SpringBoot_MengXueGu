package com.asiainfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asiainfo.service.UserService;

@Configuration// spring原生注解，标识该类是一个配置类
public class USConfig {
	/**
	 * 该方法相当于<bean id="us123" class="com.asiainfo.service.UserService"/>
	 * 方法要求：
	 * 1、返回值是bean标签的class属性值
	 * 2、方法名是bean标签的id值
	 * 3、该方法标注@Bean，方法的返回值会被标明是一个bean并放到IOC容器中
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date May 30, 2020 11:58:22 PM
	 */
	@Bean
	public UserService us123() {
		return new UserService();
	}
}
