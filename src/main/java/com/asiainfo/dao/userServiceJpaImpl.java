package com.asiainfo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.asiainfo.dao.interfaces.IUserDaoJpa;
import com.asiainfo.entity.UserJpa;

public class userServiceJpaImpl implements IUserDaoJpa {

	@Override
	public UserJpa findOne(Specification<UserJpa> spec) {
		return null;
	}

	@Override
	public List<UserJpa> findAll(Specification<UserJpa> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserJpa> findAll(Specification<UserJpa> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserJpa> findAll(Specification<UserJpa> spec, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<UserJpa> spec) {
		// TODO Auto-generated method stub
		return 0;
	}

}
