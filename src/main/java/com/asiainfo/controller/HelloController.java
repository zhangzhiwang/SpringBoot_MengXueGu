package com.asiainfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.Person;
import com.asiainfo.service.UserService;

// Spring Boot完全可以当作Spring MVC玩，完全可以使用Spring MVC的注解，因为Spring Boot默认依赖了Spring MVC的jar包
@RestController// = @Controller + @ResponseBody
@RequestMapping("helloController")
public class HelloController {
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
	private static Map<String, String> userMap;
	
	static {
		userMap = new HashMap();
		userMap.put("zs", "1");
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Person person;
	@Autowired
	private UserService us;
//	@Autowired
//	private ApplicationContext app;
	
	@RequestMapping("/hello")
//	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session, String name, String pass) {
		String pwd = userMap.get(name);
		if(StringUtils.isEmpty(pwd) || !pwd.contains(pass)) {
			return "用户名或密码错误";
		}
		
		session.setAttribute("loginName", name);
		return "登陆成功";
	}
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("person = " + person);
		System.out.println("us = " + us);
		
//		UserService bean = (UserService) app.getBean("us123");
//		System.out.println("bean = " + bean);
		
		LOG.debug("HelloController debug信息");
		LOG.info("HelloController info信息");
		LOG.warn("HelloController warn信息");
		LOG.error("HelloController error信息");
		
		userService.test1();
	}
	
	@RequestMapping("/test2")// @PostMapping = @RequestMapping(method = RequestMethod.POST)
	public String test2() {
		System.out.println("test2");
		return "redirect:helloController/test3";
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "test3";
	}
}
