package com.asiainfo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.entity.Bill;
import com.asiainfo.entity.User;
import com.asiainfo.entity.User2;
import com.asiainfo.entity.UserJpa;
import com.asiainfo.service.UserService;

/**
 * Restful风格的控制器
 *
 * @author zhangzhiwang
 * @date Jun 12, 2020 10:22:07 AM
 */
@RestController// = @Controller + @ResponseBody
@RequestMapping("restfulController")
public class RestfulController implements Cloneable {
	@Autowired
	private UserService userService;
	
	/**
	 * Restful风格的请求方法：
	 * 1、增Post，对应RequestMethod.POST，对应@PostMapping
	 * 2、删Delete，对应RequestMethod.DELETE，对应@DeleteMapping
	 * 3、改Put，对应RequestMethod.PUT，对应@PutMapping
	 * 4、查Get，对应RequestMethod.GET，对应@GetMapping
	 */
	@GetMapping("/queryAll")// = @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public String queryAll(@RequestParam(value = "userName", required = false) String name) {
		Collection<User2> users = userService.getAllUsers(name);
		return JSONObject.toJSONString(users);
	}
	
	@GetMapping("/queryById/{uid}")// @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值
	public String queryById(@PathVariable("uid") int id) {// @PathVariable()中的变量名称必须和路径中占位符的名称一致，无需和方法的形参名称一致
		User2 user = userService.getById(id);
		return JSONObject.toJSONString(user);
	}
	
	@PutMapping("/updateById")// = RequestMapping(value = "/updateById", method = RequestMethod.PUT)
	public String updateById(String newUserJson) {
		// 参数校验略
		User2 newUser = JSONObject.parseObject(newUserJson, User2.class);
		User2 user = userService.updateById(newUser);
		return JSONObject.toJSONString(user);
	}
	
	@PostMapping("/insert")
	public String insert(String userJson) {
		User2 newUser = JSONObject.parseObject(userJson, User2.class);
		User2 user = userService.insert(newUser);
		return JSONObject.toJSONString(user);
	}
	
	@DeleteMapping("/deleteById")
	public String deleteById(String id) {
		User2 user = userService.deleteById(Integer.parseInt(id));
		return JSONObject.toJSONString(user);
	}
	
//	public static void main(String[] args) throws CloneNotSupportedException {
//		User u1 = new User(1, "zs");
//		User u2 = u1.clone();
//		
//		u1.setName("ls");
//		System.out.println(u1);
//		System.out.println(u2);
//		
////		RestfulController r1 = new RestfulController();
////		RestfulController r2 = (RestfulController) r1.clone();
////		System.out.println(r1);
////		System.out.println(r2);
//	}
	
	@GetMapping("/testJdbcTemplate")
	public String testJdbcTemplate() {
		Map<String, Object> map = userService.getOneUser();
		return JSONObject.toJSONString(map);
	}
	
	@GetMapping("/getUserById/{id}")
	public String getUserById(@PathVariable("id") int pid) {
		User user = userService.getUserById(pid);
		return JSONObject.toJSONString(user);
	}
	
	@GetMapping("/insertUser")
	public String insert(User u) {// get请求如果后台接收的是一个对象，那么在发送get请求的url后面拼接参数的时候参数名称要和接收对象的属性名称对应上，这样会自动和对象的属性映射上
		User user = userService.insert(u);
		return JSONObject.toJSONString(user);
	}
	
	@GetMapping("/getBillById/{bid}")
	public Bill getBillById(@PathVariable("bid") int id) {
		Bill bill = userService.getBillById(id);
		return bill;
	}
	
	@GetMapping("/insertBill")
	public Bill insertBill(Bill bill) {
		return userService.insertBill(bill);
	}
	
	@GetMapping("/insertMultiUser")
	public void insertMultiUser() {
		userService.insertMultiUser();
	}
}