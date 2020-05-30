package com.asiainfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.entity.Person;

// Spring Boot完全可以当作Spring MVC玩，完全可以使用Spring MVC的注解，因为Spring Boot默认依赖了Spring MVC的jar包
@Controller
@RequestMapping("helloController")
public class HelloController {
	@Autowired
	private Person person;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("person = " + person);
	}
}
