package com.asiainfo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.entity.Person;
import com.asiainfo.service.UserService;

// Spring Boot完全可以当作Spring MVC玩，完全可以使用Spring MVC的注解，因为Spring Boot默认依赖了Spring MVC的jar包
@Controller
@RequestMapping("helloController")
public class HelloController {
	@Autowired
	private Person person;
	@Autowired
	private UserService us;
	@Autowired
	private ApplicationContext app;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("person = " + person);
		System.out.println("us = " + us);
		
		UserService bean = (UserService) app.getBean("us123");
		System.out.println("bean = " + bean);
	}
	
	@RequestMapping("/test2")
	public String test2(Map<String, Object> map) {
		/**
		 * 默认情况下spb工程会被打包成jar包而不是war包，内嵌的tomcat不支持以jar包的形式运行jsp，所以spb官方推荐使用模版引擎thymeleaf。
		 * 和Spring MVC一样，thymeleaf负责视图的解析和与数据的渲染，将后台返回的逻辑视图名拼上前缀和后缀，前缀默认是classpath:/templates，后缀默认是html。
		 * 本例中，thymeleaf会去找classpath:/templates/success.html
		 */
		map.put("name", "zhangsan");
		System.out.println(11);
		return "success";
	}
}
