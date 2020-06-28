package com.asiainfo.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.dao.UserDao;
import com.asiainfo.dao.interfaces.IUserDaoJpa;
import com.asiainfo.entity.Bill;
import com.asiainfo.entity.User;
import com.asiainfo.entity.User2;
import com.asiainfo.entity.UserJpa;
import com.asiainfo.mapper.BillMaper;
import com.asiainfo.mapper.UserMapper;

@Service
/**
 * 为什么在service层加事务？因为一个service可能会调用多个dao，这样一个dao失败则全部dao回滚，如果事务加在了dao层则无法实现此操作，另外事务如果加在了controller层会使事务的粒度变大，一般不会这么做。</p>
   另外，事务生不生效取决于数据库支不支持事务，比如mysql的myisam存储引擎就不支持事务，代码里面开启事务也不生效。</p>
   spring的事务有两个概念：</p>
 * 1、隔离级别
 * 	可以查看一下mysql的隔离级别，涉及到数据库在并发的情况下会产生哪些问题，有哪些隔离级别，这里就不说了。Spring中默认采用的是DEFAULT隔离界别，即采取数据库的隔离级别
 * 2、传播行为
 * 	传播行为就是当遇到一个已经存在的事务该怎么办的问题，spring中默认采用的是REQUIRED传播行为，即如果发现一个已存在的事务那就加入，如果没有发现事务就开启一个。
 */
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BillMaper billMaper;
//	@Autowired
//	private IUserDaoJpa userDaoJpa;
	
	public void test1() {
//		LOG.debug("UserService debug信息");
//		LOG.info("UserService info信息");
//		LOG.warn("UserService warn信息");
//		LOG.error("UserService error信息");
	}
	
	public Collection<User2> getAllUsers(String name) {
		return userDao.getAllUsers(name);
	}
	
	public User2 getById(int id) {
		return userDao.getById(id);
	}
	
	public User2 updateById(User2 newUser) {
		return userDao.updateById(newUser);
	}
	
	public User2 insert(User2 user) {
		return userDao.insert(user);
	}
	
	public User2 deleteById(int id) {
		return userDao.deleteById(id);
	}
	
	public Map<String, Object> getOneUser() {
		return userDao.getOneUser();
	}
	
	public User getUserById(int pid) {
		return userMapper.getById(pid);
	}
	
	public User insert(User user) {
		System.out.println("插入前user = " + user);
		userMapper.insert(user);
		System.out.println("插入后user = " + user);
		return user;
	}
	
	public Bill getBillById(int id) {
		Bill bill = billMaper.getBillById(id);
		return bill;
	}
	
	public Bill insertBill(Bill bill) {
		System.out.println("插入前bill = " + bill);
		billMaper.insertBill(bill);
		System.out.println("插入后bill = " + bill);
		return bill;
	}
	
//	public UserJpa getUserByIdJpa(int id) {
//		return userDaoJpa.findOne(new Specification<UserJpa>() {
//			@Override
//			public Predicate toPredicate(Root<UserJpa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				Path<Object> path = root.get("id");
//				Predicate p = cb.equal(path, id);
//				return p;
//			}
//		});
//	}
	
	/**
	 * 测试事务
	 * 
	 * @author zhangzhiwang
	 * @date Jun 16, 2020 5:15:02 PM
	 */
	public void insertMultiUser() {
		userMapper.insert(new User(1, "1", "1", "1", 1, 1));
//		int i = 1/0;
		userMapper.insert(new User(2, "2", "2", "2", 2, 2));
	}
}