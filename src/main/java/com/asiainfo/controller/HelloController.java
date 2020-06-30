package com.asiainfo.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.Person;
import com.asiainfo.service.TaskService;
import com.asiainfo.service.UserService;
import com.asiainfo1.template.ZzwTemplate;

// Spring Boot完全可以当作Spring MVC玩，完全可以使用Spring MVC的注解，因为Spring Boot默认依赖了Spring MVC的jar包
@RestController// = @Controller + @ResponseBody
@RequestMapping("helloController")
public class HelloController {
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
	private static Map<String, String> userMap;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private TaskService taskService;
	/**
	 * 在mvc中整合redis需要在xml中进行整合配置，在spb中我们即没有进行手动配置xml也没有手动写配置类，下面的redisTemplate既然能够被Autowired说明该对象已存在于IOC容器中，那么这个工作是谁作的呢？</p>
	 * 答案是spb的自动装配。spb和redis的整合只在pom文件中引入有redis的starter即可，无需其他工作。
	 * */
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private SqlSessionFactory sessionFactory;
	@Autowired
	private ZzwTemplate zzwTemplate;
	
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
//		System.out.println(taskService);
//		redisTemplate.opsForValue().get("key1");// 本机事先安装好redis
//		System.out.println("redisTemplate = " + redisTemplate);
//		System.out.println("sessionFactory = " + sessionFactory);
		System.out.println("zzwTemplate = " + zzwTemplate);
		return "Hello World!";
	}
	
//	@RequestMapping("/login")
	@GetMapping("/login")
	public String login(HttpSession session, String name, String pass) {
		String pwd = userMap.get(name);
		if(StringUtils.isEmpty(pwd) || !pwd.contains(pass)) {
			return "用户名或密码错误";
		}
		
		session.setAttribute("loginName", name);
		return "登陆成功";
	}
	
	@RequestMapping("/notLogin")
	public String notLogin(HttpServletRequest request) {
		System.out.println("进入notLogin方法");
		String msg = (String) request.getAttribute("msg");
		return msg;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 1、清除session中的登陆信息
		session.removeAttribute("loginName");
		// 2、注销session
		session.invalidate();
		// 3、返回登陆页面
		return "登陆已注销";
	}
	
	@RequestMapping("/test1")
	public String test1() {
//		System.out.println("person = " + person);
//		System.out.println("us = " + us);
		
//		UserService bean = (UserService) app.getBean("us123");
//		System.out.println("bean = " + bean);
		
//		LOG.debug("HelloController debug信息");
//		LOG.info("HelloController info信息");
//		LOG.warn("HelloController warn信息");
//		LOG.error("HelloController error信息");
		
//		userService.test1();
		return "test1";
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "aaa/success";// 注意目录aaa前面不能加“/”，因为他默认查找的路径是“classpath:/templates/”，后面已经写了“/”
	}
	
	@RequestMapping("/test3")// @PostMapping = @RequestMapping(method = RequestMethod.POST)
	public String test3() {
		System.out.println("test3");
		/**
		 * 转发和重定向的区别：</p>
		 * 1、请求次数。转发是浏览器向服务器发两次请求，第二次请求的URL和第一次是不一样的；转发浏览器只向服务器发一次请求，转发是服务器的内部行为，浏览器是感知不到的。</p>
		 * 2、是否丢失请求数据。转发是不会丢失request的请求的数据的，不仅不会丢失，在servlet1转向servlet2之前可以添加新的请求参数，servlet2不仅可以接收原浏览器发送的请求参数也可以接收servlet1添加的参数；</p>
		 * 	  而重定向由于是向服务器发送两次请求，每一次都会发送request数据，所以第二次请求服务器的时候是新的request，第一次的request失效了。
		 */
		return "redirect:/helloController/test5";// 浏览器访问http://localhost:8080/spb/helloController/test3之后url会自动更改为http://localhost:8080/spb/helloController/test5
	}
	
	@RequestMapping("/test4")
	public String test4() {
		return "test4";
	}
	
	@RequestMapping("/test5")
	@ResponseBody
	public String test5(HttpServletRequest request) {
		String str = (String) request.getAttribute("key1");
		System.out.println("str = " + str);
		return "test5";
	}
	
	@RequestMapping("/test6")
	public void test6(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("key1", "value1");// 转发前可以往request里面添加参数，转发后从另一个servlet里面可以取出参数。
			request.getRequestDispatcher("/helloController/test5").forward(request, response);// 转发到test5，注意helloController前面要写上“/”。浏览器的访问url不会变
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/test7")
	public void test7() {
		String s = null;
		s.length();
	}
	
	@GetMapping("/testDataSource")
	public void testDataSource() {
		/**
		 * mvc中如果需要使用spring jdbc则需要在配置文件对JdbcTemplate进行配置，spb已经配置好了：数据源的配置在org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.PooledDataSourceConfiguration，
		 * JdbcTemplate的自动配置在org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration里面。
		 */
		System.out.println("spb默认使用的数据源是：" + dataSource.getClass());
	}
}
