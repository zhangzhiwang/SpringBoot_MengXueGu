package com.asiainfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 符合jap规范的实体类
 *
 * @author zhangzhiwang
 * @date Jun 16, 2020 3:45:02 PM
 */
@Entity // 标注@Entity注解的类表明是一个和数据库某张表进行映射的实体类
@Table(name = "jpa_user") // 指定和哪张表映射。该表事先可以不存在，如果在全剧配置文件中配置了spring.jpa.hibernate.ddl-auto=create，那么jpa可以自动创建该表
public class UserJpa {
	@Id // 表明改属性对应数据库的主键字段
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 点进@GeneratedValue注解类，找到strategy属性再点进GenerationType类就知道有哪些枚举值了，IDENTITY表示自动递增
	private int id;

	@Column(name = "user_name") // 表明和表的哪个字段关联
	private String userName;

	@Column // 不指定name属性则默认属性名和字段名一致
	private String password;

	// 必须写setter和getter方法，否则无法插入和读取值
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
