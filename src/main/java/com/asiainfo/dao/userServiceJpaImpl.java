package com.asiainfo.dao;

import java.util.List;
import java.util.Optional;

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
	public Optional<Object> findOne(Specification<Object> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findAll(Specification<Object> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Object> findAll(Specification<Object> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findAll(Specification<Object> spec, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<Object> spec) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
