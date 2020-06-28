package com.asiainfo.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.asiainfo.entity.User2;

@Repository
public class UserDao {
	public static Map<Integer, User2> db = new HashMap<Integer, User2>();
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static {
		db.put(1, new User2(1, "name1"));
		db.put(2, new User2(2, "name2"));
		db.put(3, new User2(3, "name3"));
	}

	public Collection<User2> getAllUsers(String name) {
		Collection<User2> values = db.values();
		if (StringUtils.isEmpty(name)) {
			return values;
		}

		List<User2> resultList = new ArrayList<User2>();
		for (User2 user : values) {
			if (name.equals(user.getName())) {
				resultList.add(user);
			}
		}
		return resultList;
	}

	public User2 getById(int id) {
		return db.get(id);
	}

	public User2 updateById(User2 newUser) {
		User2 user = db.get(newUser.getId());
		if (user == null) {
			return null;
		}

		db.put(newUser.getId(), newUser);
		return newUser;
	}

	public User2 insert(User2 user) {
		User2 u = db.get(user.getId());
		if (u != null) {
			throw new RuntimeException("用户已存在");
		}

		db.put(user.getId(), user);
		return user;
	}
	
	public User2 deleteById(int id) {
		User2 user = db.get(id);
		if(user == null) {
			throw new RuntimeException("该用户不存在");
		}
		
		db.remove(id);
		return user;
	}
	
	public Map<String, Object> getOneUser() {
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("select * from user");
		Map<String, Object> map = queryForList.get(0);
		return map;
	}
}
