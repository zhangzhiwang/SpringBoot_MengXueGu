package com.asiainfo.dao.interfaces;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.asiainfo.entity.UserJpa;

/**
 * IUserDao接口继承JpaSpecificationExecutor接口即可使用该接口提供的方法，注意IUserDao接口无需写实现类
 *
 * @author zhangzhiwang
 * @date Jun 16, 2020 3:55:18 PM
 */
@Repository
public interface IUserDaoJpa extends JpaSpecificationExecutor<Object>{

}
