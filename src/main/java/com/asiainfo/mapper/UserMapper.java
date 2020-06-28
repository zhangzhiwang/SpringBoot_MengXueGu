package com.asiainfo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.asiainfo.entity.User;

@Mapper// 和MyBatis与MVC整合的配置文件一样，每写一个Mapper就要注册一下很麻烦，不如用包扫描，将指定包下的所有mapper都识别为一个bean放到容器中，这个包扫描在spb的启动类上面加上注解@MapperScan
public interface UserMapper {
	@Select("select * from user where id=#{pid}")
	public User getById(int pid);
	
	@Options(useGeneratedKeys = true, keyProperty = "id") // 相当于mapper配置文件里面的配置
	@Insert("insert into user (real_name) values (#{realName})")
	public int insert(User user);
}
