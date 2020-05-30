package com.asiainfo.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component// 必须标明该实体类是一个bean才能进行属性注入
@ConfigurationProperties(prefix = "person") // 用于标明读取配置文件中的属性值设置到该类对象的相应属性上，prefix位配置文件中key的前缀
// 注意：属性必须要有setter方法，映射的时候是通过调用setter方法设置值的
// 另外，和Spring一样，既然是通过setter方法注入那么配置文件的属性名必须和setter方法名去掉前面的“set”后将剩下的部分首字母小写后的名字一致，不一定和POJO的属性名一致，但一般是一致的。
// 还有，@ConfigurationProperties默认从spb的全局配置文件中读取
@PropertySource(value = {"classpath:person.properties"})
public class Person {
//	@Value("${person.name1}")// @Value注解中的${}是从配置文件中读取值，${}里面是配置文件中属性的key
	// 注意：通过@ConfigurationProperties注解注入属性值，属性必须要有setter方法，而通过@Value注解注入不需要setter方法
	private String name;
//	@Value("#{10-2}")// #{}里面是表达式，最总将表达式计算的结果赋值到属性中
	private int age;
//	@Value("true")// 里面直接可以是字面量
	private boolean isTeen;
	private IdenCard id;// IdenCard类不需要加@ConfigurationProperties和@Component就可以注入属性值
	private List<String> hobbies;
	private Map<String, String> testMap;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd hh:mm:ss")
//	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Timestamp timestamp;

//	public String getName() {
//		return name;
//	}

	public void setName1(String name) {
		this.name = name;
	}

//	public int getAge() {
//		return age;
//	}

	public void setAge(int age) {
		this.age = age;
	}

//	public boolean isTeen() {
//		return isTeen;
//	}

	public void setTeen(boolean isTeen) {
		this.isTeen = isTeen;
	}

//	public IdenCard getId() {
//		return id;
//	}

	public void setId(IdenCard id) {
		this.id = id;
	}

//	public List<String> getHobbies() {
//		return hobbies;
//	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

//	public Map<String, String> getTestMap() {
//		return testMap;
//	}

	public void setTestMap(Map<String, String> testMap) {
		this.testMap = testMap;
	}

//	public Date getDate() {
//		return date;
//	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public Timestamp getTimestamp() {
//		return timestamp;
//	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", isTeen=" + isTeen + ", id=" + id + ", hobbies=" + hobbies + ", testMap=" + testMap + ", date=" + date + ", timestamp=" + timestamp + "]";
	}

}
