package com.asiainfo.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.entity.Person;
import com.asiainfo.service.TaskService;
import com.asiainfo.service.UserService;
//import com.asiainfo1.template.ZzwTemplate;

// Spring Boot完全可以当作Spring MVC玩，完全可以使用Spring MVC的注解，因为Spring Boot默认依赖了Spring MVC的jar包
@RestController// = @Controller + @ResponseBody
@RequestMapping("helloController")
public class HelloController {
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
	private static Map<String, String> userMap;
	@Autowired
	private DataSource dataSource;
	/**
	 * @Resource：
	 * 1、@Resource后面不添加任何属性则默认按照名称匹配，且名称为属性名，如果名称匹配成功则按照配型匹配
	 * 2、@Resource如果只添加name属性，如：@Resource(name = "taskService123")，则只按照名称来匹配，如果名称匹配不成功就抛异常
	 * 3、@Resource如果只添加type属性，测试结果有点出乎意料，并不是只按类型进行匹配，而还是先按照名称进行匹配如果匹配成功了就注入，否则按照配型匹配，和第一种情况是一样的
	 * 4、@Resource如果既添加name属性又添加type属性，测试结果也有点出乎意料，这种情况和第二种情况一样，如果name匹配成功则注入，如果匹配不成功直接报错，不会再按照类型进行匹配。（这样看来貌似type属性没有什么用）
	 *	
	 * @Autowired：
	 * @Autowired注解没有name和type属性，它只按照类型进行匹配，如果找不到则直接报错，如果找到多个则会按照名称进行匹配（默认将变量名作为bean的名称去匹配），如果匹配成功则注入，否则报错。
	 * 上面说的是@Autowired的默认情况，当按照配型匹配发现多个bean而且被@Autowired的属性名和任何一个bean的名称都不匹配时，这时可以使用@Qualifier注解来告诉Spring哪个bean需要被注入
	 * 
	 * */
//	@Resource(name = "taskService", type = TaskService.class)
	@Autowired
	/**
	 * @Qualifier("taskService2")告诉Spring名称为“taskService2”的bean需要被注入。
	 * 和下面注入集合元素的情况不一样，@Qualifier注解加在非集合元素上面的时候是用来告诉Spring要注入名称为taskService1的bean，而该bean的定义上面并不需要加@Qualifier注解，只是bean的名称是taskService1即可；
	 * 而@Qualifier注解加在集合元素上面的时候是用来告诉Spring该集合只有符合标记的bean才能被注入并装进集合中，这个标记就是@Qualifier注解后面的value值，如下面的@Qualifier("zzw")，即将被注入并添加到集合中的bean在定义时必须加上同样的注解标记@Qualifier("zzw")。
	 * */
	@Qualifier("taskService1")
	private TaskService taskService;// 该bean的定义在com.asiainfo.config.USConfig类中
	
	@Autowired// @Autowired可以注入List
	@Qualifier("zzw")// 可以使用@Qualifier注解来声明哪些符合标记的注解才能被注入，本例中只用加了@Qualifier("zzw")标记的bean才能被注入
	private List<TaskService> taskServiceList = new ArrayList<TaskService>();
	
	@Autowired// @Autowired可以注入Map，但需注意Map的key必须是String类型，Spring会将bean的名称作为key装入map中。
	@Qualifier("zhangsan")// 本例中只用加了@Qualifier("zhangsan")标记的bean才能被注入
	private Map<String, TaskService> taskServiceMap = new HashMap<String, TaskService>();
	/**
	 * 在mvc中整合redis需要在xml中进行整合配置，在spb中我们即没有进行手动配置xml也没有手动写配置类，下面的redisTemplate既然能够被Autowired说明该对象已存在于IOC容器中，那么这个工作是谁作的呢？</p>
	 * 答案是spb的自动装配。spb和redis的整合只在pom文件中引入有redis的starter即可，无需其他工作。
	 * */
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private SqlSessionFactory sessionFactory;
//	@Autowired
//	private ZzwTemplate zzwTemplate;
//	@Autowired
//	@Qualifier
//	private List<TaskService> list = new ArrayList<TaskService>();
	
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
//		System.out.println("zzwTemplate = " + zzwTemplate);
//		String name = zzwTemplate.getName();
//		System.out.println(name);
		System.out.println(taskService);
//		System.out.println("list = " + list);
		System.out.println("taskServiceList = " + taskServiceList);
		System.out.println("taskServiceMap = " + taskServiceMap);
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
	
	@GetMapping("/testActuatorLogger")
	public void testActuatorLogger() throws InterruptedException {
		while(true) {
			LOG.debug("debug...");
			LOG.info("info...");
			LOG.error("error...");
			System.out.println("------------------");
			Thread.sleep(1000);
		}
	}
}
